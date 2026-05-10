# TP2 - Encapsulation

## Objectif

Introduction de l'encapsulation et des validations dans le projet IPPlan-Manager. L'objectif est de protéger les données des objets et d'empêcher les incohérences (adresses vides, masques invalides, noms null, etc.).

## Notions étudiées

- **private** : modificateur d'accès qui rend les attributs inaccessibles depuis l'extérieur de la classe
- **getters** : méthodes publiques permettant de lire la valeur d'un attribut privé
- **setters** : méthodes publiques permettant de modifier la valeur d'un attribut privé avec validation
- **validation** : contrôle des données avant modification pour garantir leur intégrité
- **this** : mot-clé faisant référence à l'objet courant, utilisé pour distinguer les attributs des paramètres

## Tests réalisés

Les validations suivantes ont été testées :

1. **AdresseIP** :
   - Adresse IP vide → remplacée par "0.0.0.0"
   - Adresse IP null → remplacée par "0.0.0.0"
   - Méthode `estAdresseLocale()` : true pour "192.168.1.1", false pour "10.0.0.1"

2. **ReseauIP** :
   - Adresse réseau vide/null → remplacée par "0.0.0.0"
   - Masque CIDR invalide (45) → remplacé par 24
   - Description vide/null → remplacée par "Aucune description"

3. **InterfaceReseau** :
   - Nom vide/null → remplacé par "interface_inconnue"
   - État par défaut : false (inactive)
   - Méthodes `activer()` et `desactiver()`

4. **Equipement** :
   - Nom vide/null → remplacé par "equipement_inconnu"
   - Type vide/null → remplacé par "Type inconnu"

5. **Modifications avec setters** :
   - Modification d'adresse IP : "192.168.1.1" → "10.10.10.1"
   - Modification de nom d'interface : "eth0" → "eth0_renamed"
   - Activation/désactivation d'interface

## Difficultés rencontrées

1. La correction d'une faute de frappe dans le code fourni : `Equipement` était écrit `Equipment` dans le constructeur du Main exemple.

2. La méthode `desactiver()` était nommée `desactivate()` dans le TP, j'ai corrigé en `desactiver()` (français correct).

3. Gestion des cas où une interface peut avoir une adresse IP null lors de l'affichage.

## Réponses aux questions

### 1. Pourquoi utilise-t-on private dans les classes ?

On utilise `private` pour protéger les attributs d'une classe. Cela empêche les autres classes d'accéder directement et de modifier ces données sans contrôle. C'est le principe fondamental de l'encapsulation.

### 2. Quelle différence existe entre un attribut public et un attribut privé ?

- **public** : accessible depuis n'importe quelle autre classe. N'importe qui peut lire et modifier sa valeur directement.
- **private** : accessible uniquement à l'intérieur de la classe elle-même. Pour y accéder de l'extérieur, il faut utiliser des getters/setters.

### 3. Pourquoi utilise-t-on des getters et setters ?

Les getters permettent un accès contrôlé en lecture aux attributs privés. Les setters permettent un accès contrôlé en écriture avec validation des données. Cela permet de garantir l'intégrité des objets.

### 4. Pourquoi les validations sont-elles importantes dans un logiciel réseau ?

Dans un logiciel réseau, des données invalides peuvent causer :
- Des configurations incorrectes
- Des erreurs de calcul d'adressage
- Des conflits réseau
- Des pannes logiques
- Des failles de sécurité

### 5. Quel est le rôle du mot-clé this ?

`this` fait référence à l'objet courant (l'instance de la classe). Il permet de :
- Distinguer un attribut d'un paramètre ayant le même nom
- Appeler un constructeur d'un autre constructeur
- Retourner l'objet courant

### 6. Pourquoi le constructeur appelle-t-il les setters ?

Le constructeur appelle les setters pour réutiliser la logique de validation déjà implémentée. Cela évite la duplication de code et garantit que même les données initiales passées au constructeur sont validées.

### 7. Pourquoi la validation du masque CIDR est-elle importante ?

Le masque CIDR doit être compris entre 0 et 32. Une valeur invalide (comme 45) produirait des calculs d'adresse réseau et de broadcast incorrects, rendant le réseau inutilisable.

### 8. Pourquoi l'encapsulation améliore-t-elle la sécurité logicielle ?

L'encapsulation améliore la sécurité en :
- Empêchant les modifications directes et incontrôlées des données
- Permettant la validation avant toute modification
- Cachant l'implémentation interne
- Réduisant les effets de bord accidentels
- Rendant le code plus robuste et prévisible

## Auteurs

MELELE FOALENG Doriane Valdaise

## Date

10/Mai/2026
