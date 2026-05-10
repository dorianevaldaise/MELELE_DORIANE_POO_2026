# TP6 - VLAN et segmentation logique

## Objectif
Mettre en place la gestion des VLANs (Virtual Local Area Networks) et associer automatiquement les sous-réseaux générés par le moteur VLSM à des VLANs cohérents.

## Notions étudiées
- **VLAN**: Virtual Local Area Network - segmentation logique du réseau
- **Segmentation logique**: Découpage indépendant de la topologie physique
- **Gestionnaire métier**: Classe spécialisée dans la gestion des VLANs
- **Collections**: ArrayList pour stocker et manipuler les VLANs
- **Associations entre objets**: Lien VLAN ↔ ResultatVLSM
- **Architecture métier**: Séparation claire des responsabilités

## Scénarios testés

### Scénario 1: Entreprise standard
| VLAN ID | Service | Adresse | Capacité |
|---------|---------|---------|----------|
| 10 | TECHNIQUE | 192.168.1.0/25 | 126 |
| 20 | WIFI | 192.168.1.128/25 | 126 |
| 30 | ADMINISTRATION | 192.168.2.0/26 | 62 |
| 40 | SERVEURS | 192.168.2.64/27 | 30 |
| 50 | DIRECTION | 192.168.2.96/28 | 14 |

### Scénario 2: Université (Travail demandé)
| VLAN ID | Service | Adresse | Capacité |
|---------|---------|---------|----------|
| 100 | ETUDIANTS | 10.0.0.0/23 | 510 |
| 110 | ENSEIGNANTS | 10.0.2.0/25 | 126 |
| 120 | LABORATOIRES | 10.0.2.128/26 | 62 |
| 130 | WIFI_PUBLIC | 10.0.2.192/25 | 126 |
| 140 | SERVEURS | 10.0.3.0/27 | 30 |

### Scénario 3: Petite entreprise
| VLAN ID | Service | Adresse | Capacité |
|---------|---------|---------|----------|
| 200 | COMMERCIAL | 172.16.0.0/27 | 30 |
| 210 | COMPTABILITE | 172.16.0.32/28 | 14 |
| 220 | RH | 172.16.0.48/28 | 14 |
| 230 | DIRECTION | 172.16.0.64/29 | 6 |

## Résultats obtenus

✅ Association automatique VLAN ↔ Sous-réseau
✅ Génération automatique des VLANs à partir des besoins
✅ Recherche de VLAN par ID
✅ Détection des VLANs critiques (capacité > 100 hôtes)
✅ Identification du VLAN avec la plus grande capacité
✅ Statistiques complètes des VLANs

## Difficultés rencontrées

1. **Association VLAN-Réseau**: Comprendre que chaque VLAN doit être associé à un sous-réseau unique

2. **Numérotation des VLANs**: Choix d'une plage cohérente (10,20,30... et 100,110,120...)

3. **Gestionnaire VLAN**: Implémentation des méthodes de recherche, affichage et statistiques

4. **Méthodes supplémentaires**: Implémentation des fonctionnalités demandées (VLANs critiques, capacité max)

## Réponses aux questions

### 1. Pourquoi les VLANs sont-ils importants dans les réseaux modernes ?
Les VLANs permettent de segmenter logiquement le réseau indépendamment de la topologie physique, améliorant ainsi la sécurité, réduisant les domaines de broadcast, et facilitant la gestion.

### 2. Pourquoi un VLAN est-il souvent associé à un sous-réseau spécifique ?
Pour assurer l'isolation logique : chaque VLAN a sa propre plage d'adresses IP, ce qui permet un routage et un filtrage efficaces entre les services.

### 3. Pourquoi la séparation logique améliore-t-elle la sécurité ?
Les VLANs isolent le trafic : un utilisateur d'un VLAN ne peut pas accéder directement au trafic d'un autre VLAN sans passer par un routeur/pare-feu.

### 4. Quel est le rôle de la classe GestionnaireVLAN ?
C'est un service métier qui centralise toutes les opérations liées aux VLANs : ajout, recherche, affichage, statistiques.

### 5. Pourquoi la classe VLAN contient-elle un objet ResultatVLSM ?
Par composition : un VLAN possède un sous-réseau associé. ResultatVLSM contient toutes les informations réseau (adresse, CIDR, capacité, plage).

### 6. Pourquoi utilise-t-on encore ArrayList dans ce TP ?
Pour gérer dynamiquement un nombre variable de VLANs, permettant d'ajouter, supprimer ou rechercher des éléments facilement.

### 7. Pourquoi les responsabilités des classes doivent-elles être séparées ?
Pour respecter le principe de responsabilité unique (SRP) : chaque classe a un rôle spécifique (BesoinReseau, MoteurVLSM, ResultatVLSM, VLAN, GestionnaireVLAN).

### 8. Pourquoi le projet commence-t-il maintenant à ressembler à une véritable application professionnelle ?
Car on distingue clairement :
- Les objets métier (VLAN, ResultatVLSM)
- Les services (MoteurVLSM, GestionnaireVLAN)
- La séparation des préoccupations

## Conclusion
Ce TP a permis d'enrichir IPPlan-Manager avec une gestion complète des VLANs. L'application devient plus réaliste et utile pour un ingénieur réseau.
