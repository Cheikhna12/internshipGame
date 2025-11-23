#!/bin/bash

# Script de création du package de distribution
# Ce script génère un package complet prêt à être distribué

echo "=========================================="
echo "  Creation du Package de Distribution"
echo "=========================================="
echo ""

# Couleurs
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Nom du package
PACKAGE_NAME="InternshipQuestGame-v1.0"
DIST_DIR="distribution"

echo -e "${YELLOW}Nettoyage des anciens packages...${NC}"
rm -rf "$DIST_DIR"
mkdir -p "$DIST_DIR/$PACKAGE_NAME"

echo ""
echo -e "${YELLOW}Compilation du projet...${NC}"
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo -e "${RED}[ERREUR] Erreur lors de la compilation${NC}"
    exit 1
fi

echo ""
echo -e "${YELLOW}Copie des fichiers...${NC}"

# Copier le JAR
cp target/internship-quest-game-1.0-SNAPSHOT.jar "$DIST_DIR/$PACKAGE_NAME/"
echo "  [OK] JAR copie"

# Copier les scripts de lancement
cp lancer-jeu.sh "$DIST_DIR/$PACKAGE_NAME/"
cp lancer-jeu.bat "$DIST_DIR/$PACKAGE_NAME/"
chmod +x "$DIST_DIR/$PACKAGE_NAME/lancer-jeu.sh"
echo "  [OK] Scripts de lancement copies"

# Copier le README de distribution
cp DISTRIBUTION_README.md "$DIST_DIR/$PACKAGE_NAME/README.md"
echo "  [OK] README copie"

# Copier les assets si nécessaire (déjà inclus dans le JAR)
echo "  [OK] Assets inclus dans le JAR"

echo ""
echo -e "${YELLOW}Informations du package :${NC}"
JAR_SIZE=$(du -h "$DIST_DIR/$PACKAGE_NAME/internship-quest-game-1.0-SNAPSHOT.jar" | cut -f1)
echo "  - Taille du JAR: $JAR_SIZE"
echo "  - Fichiers inclus:"
ls -lh "$DIST_DIR/$PACKAGE_NAME/" | tail -n +2 | awk '{print "    - " $9 " (" $5 ")"}'

echo ""
echo -e "${YELLOW}Creation de l'archive ZIP...${NC}"
cd "$DIST_DIR"
zip -r "${PACKAGE_NAME}.zip" "$PACKAGE_NAME" > /dev/null 2>&1

if [ $? -eq 0 ]; then
    ZIP_SIZE=$(du -h "${PACKAGE_NAME}.zip" | cut -f1)
    echo -e "${GREEN}  [OK] Archive creee: ${PACKAGE_NAME}.zip ($ZIP_SIZE)${NC}"
else
    echo -e "${RED}  [ERREUR] Erreur lors de la creation de l'archive${NC}"
fi

cd ..

echo ""
echo -e "${YELLOW}Creation de l'archive TAR.GZ...${NC}"
cd "$DIST_DIR"
tar -czf "${PACKAGE_NAME}.tar.gz" "$PACKAGE_NAME" 2>/dev/null

if [ $? -eq 0 ]; then
    TAR_SIZE=$(du -h "${PACKAGE_NAME}.tar.gz" | cut -f1)
    echo -e "${GREEN}  [OK] Archive creee: ${PACKAGE_NAME}.tar.gz ($TAR_SIZE)${NC}"
else
    echo -e "${RED}  [ERREUR] Erreur lors de la creation de l'archive${NC}"
fi

cd ..

echo ""
echo -e "${GREEN}=========================================="
echo -e "  [SUCCES] Package cree avec succes !"
echo -e "==========================================${NC}"
echo ""
echo "Emplacement: $DIST_DIR/"
echo ""
echo "Fichiers disponibles:"
echo "  - $DIST_DIR/$PACKAGE_NAME/ (dossier)"
echo "  - $DIST_DIR/${PACKAGE_NAME}.zip (Windows/Mac/Linux)"
echo "  - $DIST_DIR/${PACKAGE_NAME}.tar.gz (Linux/Mac)"
echo ""
echo "Pour distribuer le jeu:"
echo "  1. Envoyez le fichier ZIP ou TAR.GZ"
echo "  2. Le destinataire doit extraire l'archive"
echo "  3. Lancer avec lancer-jeu.sh (Linux/Mac) ou lancer-jeu.bat (Windows)"
echo ""
echo "Prerequis pour jouer: Java 17 ou superieur"
echo ""
