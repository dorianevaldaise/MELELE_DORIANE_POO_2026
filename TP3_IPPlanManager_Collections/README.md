# TP3 - Collections et Composition

## Objectif
Introduction des collections (ArrayList) et des relations entre objets (composition) dans le projet IPPlan-Manager.

## Notions étudiées
- **Composition**: Relation où un objet contient d'autres objets (ex: Infrastructure contient des équipements)
- **ArrayList**: Collection dynamique pour stocker des objets
- **Collections**: Structures de données pour gérer des ensembles d'objets
- **Parcours de listes**: Boucle for-each pour itérer sur les collections
- **Relations entre objets**: Liens entre équipements, interfaces, sous-réseaux

## Tests réalisés

### Test 1: Création des sous-réseaux
- ✅ Sous-réseau ADMIN: 192.168.1.0/24
- ✅ Sous-réseau TECH: 192.168.2.0/24
- ✅ Sous-réseau WIFI: 192.168.3.0/24

### Test 2: Création des équipements
- ✅ Routeur R1_EDGE avec 3 interfaces
- ✅ Switch SW_CORE avec 3 interfaces
- ✅ Serveur SRV_ADMIN avec 2 interfaces
- ✅ Serveur SRV_TECH avec 2 interfaces
- ✅ Point d'accès AP_LOBBY avec 2 interfaces
- ✅ Pare-feu FW_EDGE avec 2 interfaces
- ✅ Poste client PC_ADMIN avec 1 interface

### Test 3: Méthode de recherche
- ✅ Recherche d'équipement existant: "R1_EDGE" → trouvé
- ✅ Recherche d'équipement inexistant: message approprié

### Test 4: Suppression
- ✅ Suppression d'un équipement
- ✅ Suppression d'un sous-réseau

## Difficultés rencontrées

1. **Gestion des imports**: Penser à importer `java.util.ArrayList` dans chaque classe utilisant des collections

2. **Initialisation des collections**: Ne pas oublier d'initialiser l'ArrayList dans le constructeur avec `new ArrayList<>()`

3. **Parcours de collection**: La boucle for-each est pratique mais ne permet pas de modifier la collection pendant l'itération

4. **Relations entre objets**: Bien différencier la composition (l'objet contient) de l'agrégation (l'objet référence)

## Réponses aux questions

### 1. Qu'est-ce qu'une composition en Programmation Orientée Objet ?
La composition est une relation où une classe contient une ou plusieurs instances d'autres classes. L'objet conteneur est responsable du cycle de vie des objets contenus. Dans notre TP, `InfrastructureReseau` contient une collection d'`Equipement` et de `SousReseau`.

### 2. Pourquoi utilise-t-on ArrayList dans ce TP ?
ArrayList permet de stocker dynamiquement des objets, contrairement aux tableaux classiques. Elle offre des méthodes comme `add()`, `remove()`, `size()` et permet d'ajouter/supprimer des éléments sans connaître la taille à l'avance.

### 3. Quelle différence existe entre une variable simple et une collection ?
- **Variable simple**: Stocke une seule valeur ou référence d'objet
- **Collection**: Stocke plusieurs objets et fournit des méthodes pour les manipuler (ajout, suppression, recherche)

### 4. Pourquoi un équipement possède-t-il plusieurs interfaces ?
Dans un réseau réel, un équipement (routeur, switch) doit se connecter à différents réseaux. Chaque interface permet une connexion à un sous-réseau différent.

### 5. Pourquoi une infrastructure réseau contient-elle plusieurs sous-réseaux ?
Pour organiser le réseau par départements (Administration, Technique, WiFi), améliorer la sécurité en isolant les flux, et optimiser les performances en réduisant le domaine de broadcast.

### 6. Quel est le rôle de la boucle for-each ?
La boucle for-each (`for(Element e : collection)`) permet de parcourir tous les éléments d'une collection de manière simple et lisible, sans utiliser d'indice.

### 7. Pourquoi la classe InfrastructureReseau devient-elle importante dans le projet ?
Elle centralise la gestion de tous les objets réseau (équipements, sous-réseaux). C'est le "cœur" de l'application qui coordonne toutes les opérations.

### 8. Pourquoi les collections sont-elles indispensables dans les applications professionnelles ?
Les applications professionnelles manipulent des milliers d'objets. Les collections permettent d'ajouter, supprimer, rechercher et trier efficacement ces objets sans limitations de taille.

## Conclusion
Ce TP a permis de comprendre l'importance des collections et de la composition pour construire des applications Java réalistes et évolutives dans le contexte de la gestion d'infrastructure réseau.
