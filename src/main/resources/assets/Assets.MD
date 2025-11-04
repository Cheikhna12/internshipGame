# Asset

## tileset.png
le fichier **tileset.png** contient les différentes cases pour utiliser par la **city_map**
les images sont numeroté:
1  2  3  4  5  6  7  8
9 10 11 12 13 14 15 16.


## city_map.tmx
le fichier  **city_map.tmx** contient la matrice de chaque élément *ground*,*road*, *building* superposé. 

## Pourquoi ne pas tout mettre dans une seule matrice

Si tout était dans une seule matrice, tu perdrais :

La superposition visuelle
→ Par exemple, un bâtiment doit se dessiner au-dessus du sol, mais sous un personnage.
Avec plusieurs couches, tu contrôles cet ordre facilement.

La modularité
→ Tu peux modifier seulement la couche “road” sans toucher au “ground”.
Tu peux même activer/désactiver une couche à l’exécution (utile pour debug ou effets).

Les collisions et la logique
→ En général, les collisions sont définies sur une couche spécifique (souvent “collision” ou “objects”).
Tu peux séparer ce qui est visuel (ground, décor) de ce qui est interactif (collision, triggers).

Les performances
→ LibGDX peut rendre chaque couche séparément et optimiser le batching du rendu.
Une seule énorme matrice avec tous les tiles superposés coûterait plus cher à gérer et à redessiner.

