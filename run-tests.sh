#!/bin/bash

# Script d'automatisation des tests pour Internship Quest Game
# Ce script exécute tous les tests unitaires et génère un rapport de couverture

echo ""
echo "  Internship Quest - Test Automation"
echo ""

# Couleurs pour l'affichage
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Vérifier que Maven est installé
if ! command -v mvn &> /dev/null
then
    echo -e "${RED}Maven n'est pas installé. Veuillez installer Maven pour continuer.${NC}"
    exit 1
fi

echo -e "${YELLOW}Nettoyage des anciens builds...${NC}"
mvn clean

echo ""
echo -e "${YELLOW}Exécution des tests unitaires...${NC}"
echo ""

# Exécuter les tests avec couverture de code
mvn test

# Vérifier le code de sortie
if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}Tous les tests sont passés avec succès!${NC}"
    echo ""
    
    # Générer le rapport de couverture
    echo -e "${YELLOW}Génération du rapport de couverture...${NC}"
    
    # Le rapport JaCoCo est généré automatiquement par Maven
    if [ -f "target/site/jacoco/index.html" ]; then
        echo -e "${GREEN}Rapport de couverture généré avec succès!${NC}"
        echo ""
        echo "Rapport disponible à: target/site/jacoco/index.html"
        echo ""
        
        # Afficher un résumé de la couverture si possible
        if [ -f "target/site/jacoco/jacoco.csv" ]; then
            echo "Résumé de la couverture:"
            echo "-------------------------------------------"
            # Extraire les statistiques du fichier CSV
            tail -n 1 target/site/jacoco/jacoco.csv | awk -F',' '{
                instructions_covered=$5
                instructions_total=$4+$5
                branches_covered=$7
                branches_total=$6+$7
                if (instructions_total > 0) {
                    instruction_coverage = (instructions_covered / instructions_total) * 100
                    printf "Instructions couvertes: %.2f%%\n", instruction_coverage
                }
                if (branches_total > 0) {
                    branch_coverage = (branches_covered / branches_total) * 100
                    printf "Branches couvertes: %.2f%%\n", branch_coverage
                }
            }'
            echo "-------------------------------------------"
        fi
        
        echo ""
        echo -e "Pour voir le rapport détaillé, ouvrez:"
        echo "   file://$(pwd)/target/site/jacoco/index.html"
        echo ""
    else
        echo -e "Rapport de couverture non trouvé"
    fi
    
    # Afficher le résumé des tests
    echo -e "${YELLOW}Résumé des tests:${NC}"
    if [ -f "target/surefire-reports/TEST-*.xml" ]; then
        total_tests=$(grep -h "tests=" target/surefire-reports/TEST-*.xml | sed 's/.*tests="\([0-9]*\)".*/\1/' | awk '{s+=$1} END {print s}')
        total_failures=$(grep -h "failures=" target/surefire-reports/TEST-*.xml | sed 's/.*failures="\([0-9]*\)".*/\1/' | awk '{s+=$1} END {print s}')
        total_errors=$(grep -h "errors=" target/surefire-reports/TEST-*.xml | sed 's/.*errors="\([0-9]*\)".*/\1/' | awk '{s+=$1} END {print s}')
        
        echo "   Total tests: $total_tests"
        echo "   Réussis: $((total_tests - total_failures - total_errors))"
        echo "   Échecs: $total_failures"
        echo "   Erreurs: $total_errors"
    fi
    
    echo ""
    echo -e "Tests terminés avec succès!"
    exit 0
else
    echo ""
    echo -e "Certains tests ont échoué!"
    echo ""
    echo -e "Consultez les rapports dans: target/surefire-reports/"
    echo ""
    echo -e "Tests échoués"
    echo ""
    exit 1
fi
