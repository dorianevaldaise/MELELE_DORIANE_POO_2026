# TP5 - Moteur VLSM (Variable Length Subnet Mask)

## Objectif
Développer un moteur VLSM capable de proposer automatiquement un plan d'adressage IP à partir des besoins exprimés par l'utilisateur.

## Notions étudiées
- **VLSM**: Variable Length Subnet Mask - découpage en sous-réseaux de tailles variables
- **Tri de collections**: Utilisation de Comparator pour trier les besoins
- **Classe de service métier**: MoteurVLSM qui encapsule la logique
- **Calcul CIDR**: Détermination automatique du masque adapté
- **Conversion IP-Entier**: Pour faciliter les calculs d'adressage
- **Génération automatique**: Production d'un plan d'adressage complet

## Scénarios testés

### Scénario 1: Entreprise standard
| Besoin | Hôtes | Résultat | Capacité |
|--------|-------|----------|----------|
| TECHNIQUE | 120 | 192.168.1.0/25 | 126 |
| WIFI | 80 | 192.168.1.128/25 | 126 |
| ADMINISTRATION | 50 | 192.168.2.0/26 | 62 |
| SERVEURS | 20 | 192.168.2.64/27 | 30 |
| DIRECTION | 10 | 192.168.2.96/28 | 14 |

### Scénario 2: Petite entreprise
| Besoin | Hôtes | Résultat | Capacité |
|--------|-------|----------|----------|
| COMMERCIAL | 30 | 10.0.0.0/27 | 30 |
| COMPTABILITE | 15 | 10.0.0.32/28 | 14 |
| RH | 10 | 10.0.0.48/28 | 14 |
| DIRECTION | 5 | 10.0.0.64/29 | 6 |

### Scénario 3: Grande infrastructure
| Besoin | Hôtes | Résultat | Capacité |
|--------|-------|----------|----------|
| DATACENTER | 500 | 172.16.0.0/23 | 510 |
| BACKUP | 200 | 172.16.2.0/24 | 254 |
| DMZ | 100 | 172.16.3.0/25 | 126 |
| DEVELOPPEMENT | 60 | 172.16.3.128/26 | 62 |
| TEST | 30 | 172.16.3.192/27 | 30 |
| MANAGEMENT | 10 | 172.16.3.224/28 | 14 |

## Résultats obtenus

✅ Triage automatique des besoins par ordre décroissant
✅ Calcul automatique du CIDR optimal pour chaque besoin
✅ Génération non superposée des sous-réseaux
✅ Affichage des plages d'adresses utilisables
✅ Calcul des adresses de début et fin de plage

## Difficultés rencontrées

1. **Algorithme de recherche du CIDR**: Parcourir de /32 à /0 pour trouver le plus petit masque satisfaisant le besoin

2. **Conversion IP-Entier**: Manipulation des bits avec décalages (<<, >>>) et masques (& 0xFF)

3. **Tri personnalisé**: Utilisation de Comparator avec ordre décroissant (b2 - b1)

4. **Calcul des adresses utilisables**: Attention à ne pas dépasser l'adresse de broadcast

5. **Cas particuliers**: /31 (1 hôte) et /32 (0 hôte utilisable)

## Réponses aux questions

### 1. Pourquoi le VLSM permet-il d'économiser les adresses IP ?
Le VLSM attribue à chaque sous-réseau la taille strictement nécessaire. On évite de gaspiller des adresses en utilisant des masques trop grands.

### 2. Pourquoi faut-il traiter les plus grands besoins en premier ?
Pour éviter la fragmentation. Si on commence par les petits, on risque de bloquer l'espace pour les grands besoins.

### 3. Quelle est la différence entre un besoin réseau et un résultat VLSM ?
Le besoin est une exigence utilisateur (nom + nombre d'hôtes). Le résultat est la solution technique proposée (adresse, CIDR, plage, capacité).

### 4. Pourquoi la classe MoteurVLSM est-elle une classe de service métier ?
Elle ne représente pas un objet réel mais une logique métier (découpage VLSM). Elle applique des règles et produit des résultats.

### 5. Pourquoi transforme-t-on une adresse IP en entier pour certains calculs ?
Pour faciliter l'addition des blocs d'adresses. Les calculs binaires sont beaucoup plus simples en entier qu'en format pointé.

### 6. Quel est le rôle de la méthode calculerCidrPourHotes() ?
Elle détermine le plus petit CIDR capable d'accueillir un nombre donné d'hôtes, en testant les différents masques.

### 7. Pourquoi une adresse de réseau et une adresse de broadcast ne sont-elles pas attribuées aux machines?
L'adresse réseau identifie le sous-réseau lui-même. L'adresse broadcast est utilisée pour envoyer à toutes les machines. Ces adresses sont réservées.

### 8. Pourquoi le moteur VLSM représente-t-il une étape importante dans le projet IPPlan-Manager?
C'est le cœur métier de l'application. Sans VLSM, l'outil ne serait qu'un calculateur passif. Avec VLSM, il devient un assistant actif pour l'administrateur.

## Conclusion
Ce TP a permis d'implémenter le moteur VLSM, composant essentiel d'un outil IPAM professionnel. Il automatise le découpage réseau et évite les erreurs manuelles.
