# TP10 - Application finale IPPlan-Manager

## Objectif
Assembler toutes les fonctionnalités développées dans les TPs précédents afin de produire une application console complète de planification d'adressage IP.

## Fonctionnalités réalisées
- ✅ Saisie interactive des besoins réseau
- ✅ Chargement des besoins depuis un fichier CSV
- ✅ Génération automatique d'un plan VLSM
- ✅ Création automatique des VLANs associés
- ✅ Validation du plan (adresses, chevauchements)
- ✅ Moteur de recommandations (sécurité, performance)
- ✅ Sauvegarde des résultats en CSV
- ✅ Génération d'un rapport technique complet
- ✅ Interface utilisateur conviviale

## Organisation du projet
ipplanmanager/
├── model/ → BesoinReseau, ResultatVLSM, VLAN, Recommandation
├── service/ → CalculateurReseau, MoteurVLSM, GestionnaireVLAN,
│ MoteurRecommandation, ValidateurPlanAdressage,
│ RapportService, ApplicationIPPlanManager
├── repository/ → BesoinRepository, FichierPlanRepository
├── exception/ → Exceptions personnalisées
├── console/ → ConsoleService (interaction utilisateur)
└── main/ → Main (point d'entrée)

text

## Scénarios testés

### Scénario 1: Campus IRT
| Besoin | Hôtes | VLAN | Adresse | Capacité | Marge |
|--------|-------|------|---------|----------|-------|
| ETUDIANTS | 500 | 10 | 10.10.0.0/23 | 510 | 10 |
| WIFI_INVITES | 200 | 20 | 10.10.2.0/24 | 254 | 54 |
| ENSEIGNANTS | 120 | 30 | 10.10.3.0/25 | 126 | 6 |
| LABORATOIRES | 60 | 40 | 10.10.3.128/26 | 62 | 2 |
| SERVEURS | 30 | 50 | 10.10.3.192/27 | 30 | 0 |

### Scénario 2: PME
| Besoin | Hôtes | VLAN | Adresse | Capacité | Marge |
|--------|-------|------|---------|----------|-------|
| ADMINISTRATION | 50 | 10 | 192.168.1.0/26 | 62 | 12 |
| COMPTABILITE | 20 | 20 | 192.168.1.64/27 | 30 | 10 |
| WIFI_INVITES | 80 | 30 | 192.168.1.96/27 | 30 | -50 |
| SERVEURS | 15 | 40 | 192.168.1.128/28 | 14 | -1 |
| VOIP | 40 | 50 | 192.168.1.144/26 | 62 | 22 |

### Scénario 3: Entreprise multi-services
| Besoin | Hôtes | VLAN | Adresse | Capacité | Marge |
|--------|-------|------|---------|----------|-------|
| TECHNIQUE | 120 | 10 | 192.168.1.0/25 | 126 | 6 |
| INVITES | 100 | 20 | 192.168.1.128/25 | 126 | 26 |
| CAMERAS | 60 | 30 | 192.168.2.0/26 | 62 | 2 |
| SUPPORT | 35 | 40 | 192.168.2.64/27 | 30 | -5 |
| DIRECTION | 25 | 50 | 192.168.2.96/27 | 30 | 5 |

## Fichiers générés dans exports/
- `*_plan.csv` - Plan VLSM au format CSV
- `*_vlans.csv` - Liste des VLANs au format CSV
- `*_recommandations.txt` - Recommandations au format texte
- `*_rapport.txt` - Rapport technique complet

## Difficultés rencontrées
1. **Gestion des packages**: Correction des imports après réorganisation
2. **Saisie utilisateur**: Validation des entrées et gestion des erreurs
3. **Génération des fichiers**: Création du dossier exports automatiquement
4. **Intégration complète**: Assemblage de toutes les fonctionnalités

## Réponses aux questions

### 1. Pourquoi le TP10 représente-t-il une application plus complète ?
Car il intègre toutes les fonctionnalités des TPs précédents (VLSM, VLAN, validations, recommandations, persistance) dans une interface utilisateur cohérente.

### 2. Quel est le rôle de la classe ApplicationIPPlanManager ?
C'est le cœur de l'application qui orchestre l'exécution du programme (coordination entre console, services, repositories).

### 3. Pourquoi la classe Main doit-elle rester courte ?
Pour respecter le principe de responsabilité unique : Main ne fait que lancer l'application.

### 4. Pourquoi séparer les packages model, service, repository, exception, console, main ?
Pour une meilleure organisation, maintenabilité et réutilisabilité du code.

### 5. Pourquoi la saisie utilisateur est-elle placée dans ConsoleService ?
Pour séparer l'interaction utilisateur de la logique métier (principe de séparation des préoccupations).

### 6. Pourquoi faut-il valider l'adresse réseau avant de générer le plan ?
Pour éviter des erreurs en cascade et garantir que le plan est basé sur une adresse valide.

### 7. Pourquoi le moteur de recommandations est-il exécuté après la génération des VLANs ?
Car les recommandations analysent les VLANs qui viennent d'être créés.

### 8. Pourquoi la sauvegarde des résultats rend-elle l'application exploitable ?
L'utilisateur peut conserver, partager et analyser les résultats après l'exécution.

### 9. Pourquoi le rapport technique est-il important ?
Il fournit une vue d'ensemble structurée du plan d'adressage, compréhensible par toute l'équipe.

### 10. Quelles améliorations pourraient être ajoutées ?
- Interface graphique (JavaFX/Swing)
- Export PDF des rapports
- Base de données pour stocker l'historique
- Support IPv6
- Calcul automatique du meilleur réseau de départ

## Conclusion personnelle
Ce projet m'a permis de comprendre comment la Programmation Orientée Objet permet de construire une application complexe et maintenable. J'ai appris à organiser mon code en packages, à séparer les responsabilités (model/service/repository), et à intégrer des fonctionnalités métier réelles (calculs réseau, VLSM, VLAN, recommandations). IPPlan-Manager est maintenant un outil fonctionnel qui peut aider un administrateur réseau à planifier son adressage IP.
