@echo off
REM Script de lancement pour Internship Quest Game (Windows)

echo ==========================================
echo   Internship Quest Game
echo ==========================================
echo.

REM Vérifier que Java est installé
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERREUR] Java n'est pas installe.
    echo Veuillez installer Java 17 ou supérieur pour jouer.
    echo.
    echo Téléchargez Java sur : https://adoptium.net/
    pause
    exit /b 1
)

echo Lancement du jeu...
echo.

REM Lancer le jeu avec allocation mémoire optimale
java -Xmx2G -jar internship-quest-game-1.0-SNAPSHOT.jar

if %errorlevel% equ 0 (
    echo.
    echo Merci d'avoir joue !
) else (
    echo.
    echo [ERREUR] Le jeu s'est termine avec une erreur.
    echo Vérifiez que tous les fichiers assets sont présents.
)

pause
