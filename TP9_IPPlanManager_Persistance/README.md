# TP9 - Persistance et organisation professionnelle

## Objectif
Ajouter la persistance des données (lecture/écriture de fichiers) et organiser le projet en packages pour une architecture professionnelle.

## Notions étudiées
- **Persistance**: Sauvegarde des données dans des fichiers
- **Fichiers CSV**: Format pour échanger des données
- **Repository**: Classes dédiées à l'accès aux données
- **Service**: Classes dédiées aux traitements métier
- **Model**: Classes représentant les données métier
- **Packages**: Organisation du code pour la maintenabilité

## Fichiers utilisés

### Fichiers d'entrée:
- `exports/besoins.csv` - Besoins pour le scénario Université
- `exports/besoins_pme.csv` - Besoins pour le scénario PME

### Fichiers générés:
- `exports/plan_adressage.csv` - Plan VLSM au format CSV
- `exports/vlans.csv` - Liste des VLANs au format CSV
- `exports/recommandations.txt` - Recommandations au format texte
- `exports/rapport_complet.txt` - Rapport complet formatté
- `exports/rapport_pme.txt` - Rapport pour le scénario PME

## Structure des packages
ipplanmanager/
├── model/ → Classes métier (BesoinReseau, VLAN, ResultatVLSM...)
├── service/ → Traitements (MoteurVLSM, GestionnaireVLAN...)
├── repository/ → Accès données (BesoinRepository, FichierPlanRepository)
├── exception/ → Exceptions personnalisées
└── main/ → Point d'entrée (Main)

text

## Scénarios testés

### Scénario 1: Université
| Besoin | Hôtes | VLAN | Adresse |
|--------|-------|------|---------|
| ETUDIANTS | 500 | 10 | 10.10.0.0/23 |
| WIFI_INVITES | 200 | 20 | 10.10.2.0/24 |
| ENSEIGNANTS | 120 | 30 | 10.10.3.0/25 |
| LABORATOIRES | 60 | 40 | 10.10.3.128/26 |
| SERVEURS | 30 | 50 | 10.10.3.192/27 |

### Scénario 2: PME (Travail demandé)
| Besoin | Hôtes | VLAN | Adresse |
|--------|-------|------|---------|
| ADMINISTRATION | 50 | 10 | 192.168.1.0/26 |
| COMPTABILITE | 20 | 20 | 192.168.1.64/27 |
| WIFI_INVITES | 80 | 30 | 192.168.1.96/27 |
| SERVEURS | 15 | 40 | 192.168.1.128/28 |
| VOIP | 40 | 50 | 192.168.1.144/27 |

## Difficultés rencontrées

1. **Gestion des packages**: Correction des imports après réorganisation
2. **Lecture CSV**: Gestion de l'en-tête et du séparateur point-virgule
3. **Écriture de fichiers**: Fermeture correcte des ressources (writer.close())
4. **Séparation des responsabilités**: Distinguer model, service, repository

## Réponses aux questions

### 1. Qu'est-ce que la persistance des données ?
C'est la capacité de conserver des données au-delà de l'exécution du programme, en les stockant sur un support durable (fichier, base de données).

### 2. Pourquoi une application professionnelle doit-elle sauvegarder ses résultats ?
Pour permettre à l'utilisateur de retrouver son travail plus tard, partager les résultats avec son équipe, et garder une trace des décisions prises.

### 3. Quelle est la différence entre un fichier CSV et un rapport texte ?
Le CSV est destiné à être traité par des outils (tableur, base de données). Le rapport texte est destiné à être lu directement par un humain.

### 4. Pourquoi a-t-on créé un package repository ?
Pour centraliser toutes les opérations d'accès aux données (lecture/écriture), séparant ainsi la logique métier de la logique de persistance.

### 5. Pourquoi a-t-on créé un package service ?
Pour regrouper la logique métier (calculs, validation, recommandations) indépendamment de la façon dont les données sont stockées.

### 6. Pourquoi ne faut-il pas écrire tout le code dans la classe Main ?
Pour respecter le principe de responsabilité unique, faciliter les tests, et permettre la réutilisation du code.

### 7. Pourquoi le fichier besoins.csv rend-il l'application plus flexible ?
L'utilisateur peut modifier les besoins sans recompiler le programme, simplement en éditant le fichier CSV.

### 8. Pourquoi la séparation en packages améliore-t-elle la maintenabilité ?
Chaque package a une responsabilité claire. On sait où chercher une classe et où ajouter une nouvelle fonctionnalité.

## Conclusion
Ce TP a permis d'organiser le projet comme une application professionnelle avec une architecture en couches (model, service, repository), et d'ajouter la persistance des données via des fichiers CSV et textes.
