# TP8 - Moteur de recommandations

## Objectif
Ajouter un moteur de recommandations capable d'analyser un plan VLAN et de proposer des conseils techniques pour améliorer la sécurité, la performance et la maintenabilité du réseau.

## Notions étudiées
- **Interface Java**: Définition d'un contrat commun pour toutes les règles
- **Polymorphisme**: Manipulation d'objets de types différents via une interface commune
- **Règles métier**: Encapsulation de la logique de recommandation
- **Moteur de recommandations**: Application automatique de plusieurs règles
- **Extensibilité**: Possibilité d'ajouter de nouvelles règles sans modifier le moteur

## Scénarios testés

### Scénario 1: Université
| Besoin | Hôtes | VLAN ID | Capacité |
|--------|-------|---------|----------|
| ETUDIANTS | 500 | 100 | 510 |
| WIFI_INVITES | 200 | 110 | 254 |
| ENSEIGNANTS | 120 | 120 | 126 |
| LABORATOIRES | 60 | 130 | 62 |
| SERVEURS | 30 | 140 | 30 |

### Scénario 2: Entreprise avec Administration
| Besoin | Hôtes | VLAN ID | Capacité |
|--------|-------|---------|----------|
| ADMINISTRATION | 50 | 200 | 62 |
| WIFI_INVITES | 120 | 210 | 126 |
| SERVEURS | 20 | 220 | 30 |
| CAMERAS | 80 | 230 | 126 |
| VOIP | 60 | 240 | 62 |

### Scénario 3: Petite entreprise
| Besoin | Hôtes | VLAN ID | Capacité |
|--------|-------|---------|----------|
| COMMERCIAL | 30 | 300 | 30 |
| COMPTABILITE | 15 | 310 | 14 |
| RH | 10 | 320 | 14 |
| DIRECTION | 5 | 330 | 6 |

## Recommandations obtenues

### Pour le scénario Université:
- 🔴 [ÉLEVÉE] **Isolation du WiFi**: Le VLAN WIFI_INVITES doit être isolé des VLANs internes sensibles
- 🔴 [ÉLEVÉE] **Protection du VLAN Serveurs**: Le VLAN SERVEURS doit être protégé par des ACL
- 🟠 [MOYENNE] **VLAN de grande taille**: Surveillance des broadcasts pour ETUDIANTS (510 hôtes)
- 🟠 [MOYENNE] **VLAN de grande taille**: Surveillance des broadcasts pour WIFI_INVITES (254 hôtes)

### Pour le scénario Administration:
- 🔴 [ÉLEVÉE] **Isolation du WiFi**: VLAN WIFI_INVITES
- 🔴 [ÉLEVÉE] **Protection du VLAN Serveurs**: VLAN SERVEURS
- 🔴 [ÉLEVÉE] **Sécurisation du VLAN Administration**: Accès réservé aux administrateurs

## Difficultés rencontrées

1. **Compréhension des interfaces**: Distinguer entre interface (contrat) et classe concrète (implémentation)

2. **Polymorphisme**: Le moteur manipule des `RegleRecommandation` sans connaître les implémentations spécifiques

3. **Gestion des null**: Une règle peut retourner `null` si aucune recommandation n'est nécessaire

4. **Priorisation des recommandations**: Trier les recommandations par niveau de priorité (ÉLEVÉE d'abord)

## Réponses aux questions

### 1. Quel est le rôle d'un moteur de recommandations dans un outil IPAM ?
Il analyse le plan d'adressage et propose des conseils pour améliorer la sécurité, les performances et la conformité aux bonnes pratiques.

### 2. Pourquoi utilise-t-on une interface pour les règles de recommandation ?
Pour définir un contrat commun (`analyser()`) et permettre l'ajout facile de nouvelles règles sans modifier le moteur.

### 3. Quelle est la différence entre une classe concrète et une interface ?
Une classe concrète fournit une implémentation complète. Une interface définit seulement un contrat (méthodes à implémenter).

### 4. Pourquoi la méthode analyser() peut-elle retourner null ?
Pour indiquer qu'aucune recommandation n'est nécessaire pour ce VLAN. Cela évite de créer des objets inutiles.

### 5. Pourquoi le moteur de recommandations illustre-t-il le polymorphisme ?
Le moteur appelle `analyser()` sur des objets de types différents (Wifi, Serveurs, GrandVLAN) via la même interface.

### 6. Pourquoi est-il préférable de créer une classe par règle ?
Pour respecter le principe de responsabilité unique (SRP) et permettre d'ajouter/supprimer des règles indépendamment.

### 7. Pourquoi un VLAN WiFi invité doit-il être isolé des réseaux internes ?
Pour des raisons de sécurité : empêcher les utilisateurs invités d'accéder aux ressources sensibles de l'entreprise.

### 8. Pourquoi les VLANs de grande taille doivent-ils être surveillés ?
Les broadcasts peuvent saturer le réseau, dégrader les performances et compliquer la supervision.

## Conclusion
Ce TP a permis d'ajouter un moteur de recommandations extensible, démontrant l'importance des interfaces et du polymorphisme pour construire des applications évolutives.
