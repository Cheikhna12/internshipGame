#!/bin/bash

# Script de lancement pour Internship Quest Game
# Ce script lance le jeu avec les paramètres optimaux

echo "=========================================="
echo "  Internship Quest Game"
echo "=========================================="
echo ""

# Vérifier que Java est installé
if ! command -v java &> /dev/null
then
    echo "[ERREUR] Java n'est pas installe."
    echo "Veuillez installer Java 17 ou supérieur pour jouer."
    exit 1
fi

# Vérifier la version de Java
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "[ATTENTION] Java $JAVA_VERSION detecte. Java 17 ou superieur est recommande."
    echo "Le jeu pourrait ne pas fonctionner correctement."
    echo ""
fi

echo "Lancement du jeu..."
echo ""

# Lancer le jeu avec allocation mémoire optimale
java -Xmx2G -jar internship-quest-game-1.0-SNAPSHOT.jar

# Vérifier le code de sortie
if [ $? -eq 0 ]; then
    echo ""
    echo "Merci d'avoir joue !"
else
    echo ""
    echo "[ERREUR] Le jeu s'est termine avec une erreur."
    echo "Vérifiez que tous les fichiers assets sont présents."
fi
