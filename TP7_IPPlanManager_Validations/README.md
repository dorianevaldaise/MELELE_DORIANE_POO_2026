# TP7 - Validations avancées et détection des conflits

## Objectif
Ajouter des validations avancées au projet IPPlan-Manager pour détecter les incohérences potentielles dans un plan d'adressage réseau.

## Notions étudiées
- **Exceptions personnalisées**: Création de classes d'exception héritant de Exception
- **try/catch**: Blocs pour capturer et traiter les exceptions
- **throw**: Déclenchement volontaire d'une exception
- **Validation réseau**: Vérification d'adresses IP, détection de chevauchements
- **Conflit VLAN**: Détection d'identifiants VLAN dupliqués
- **Robustesse logicielle**: Capacité à gérer les erreurs sans crash

## Scénarios testés

### Scénario 1: Cas normal - Plan valide
- Besoins: ADMIN(50), TECH(120), WIFI(80), SERVEURS(20)
- Résultat: Plan généré, validation réussie ✅

### Scénario 2: Conflit VLAN
- Tentative d'ajout de deux VLANs avec ID=10
- Résultat: Exception ConflitVLANException détectée ✅

### Scénario 3: Adresse IP invalide
- Tests avec "192.168.300.0", "256.168.1.0", "192.168.1.0.1"
- Résultat: Exception AdresseIPInvalideException détectée ✅

### Scénario 4: Chevauchement de réseaux
- Test avec 192.168.1.0/25 et 192.168.1.64/26
- Résultat: Exception ChevauchementReseauException détectée ✅

### Scénario 5: Réseau insuffisant (supplémentaire)
- Besoins très grands (500+300+200 hôtes) sur /24
- Résultat: Détection de capacité insuffisante

## Résultats obtenus

| Test | Type | Résultat |
|------|------|----------|
| 1 | Validation normale | ✅ Succès |
| 2 | Conflit VLAN | ❌ Exception capturée |
| 3 | Adresse invalide | ❌ Exception capturée |
| 4 | Chevauchement | ❌ Exception capturée |
| 5 | Capacité insuffisante | ⚠️ Avertissement |

## Difficultés rencontrées

1. **Compréhension des exceptions**: Différence entre checked et unchecked exceptions

2. **Détection de chevauchement**: Formule correcte pour comparer deux plages d'adresses

3. **Double boucle de comparaison**: Éviter les doublons (i=0..n, j=i+1..n)

4. **Propagation des exceptions**: Les méthodes appelantes doivent déclarer throws

5. **Validation d'adresse IP**: Vérifier exactement 4 octets entre 0 et 255

## Réponses aux questions

### 1. Pourquoi les validations avancées sont-elles indispensables dans un outil IPAM ?
Pour éviter les erreurs de configuration qui pourraient causer des pannes réseau, des conflits d'adressage ou des problèmes de routage en production.

### 2. Quelle est la différence entre une erreur simple et une exception en Java ?
Une erreur simple est souvent gérée par une condition if/else. Une exception est un mécanisme structuré pour signaler et propager des problèmes entre différentes couches de l'application.

### 3. Pourquoi crée-t-on des exceptions personnalisées ?
Pour rendre les erreurs plus explicites et adaptées au domaine métier (AdresseIPInvalideException plutôt qu'une Exception générique).

### 4. Quel est le rôle du bloc try/catch ?
try exécute du code potentiellement dangereux, catch récupère et traite l'exception si elle se produit, évitant que le programme ne plante.

### 5. Pourquoi deux VLANs ne doivent-ils pas avoir le même identifiant dans une même infrastructure ?
Car l'ID VLAN est l'identifiant unique utilisé par les commutateurs pour différencier les domaines de broadcast. Un doublon causerait des interférences.

### 6. Pourquoi deux sous-réseaux ne doivent-ils pas se chevaucher ?
Car une adresse IP ne peut appartenir qu'à un seul réseau. Un chevauchement créerait une ambiguïté de routage.

### 7. Pourquoi transforme-t-on les adresses IP en entiers pour comparer des plages réseau ?
Car les opérations arithmétiques sur les adresses sont beaucoup plus simples et performantes en format entier.

### 8. Pourquoi la classe ValidateurPlanAdressage doit-elle être séparée du moteur VLSM ?
Pour respecter le principe de responsabilité unique (SRP) : le moteur génère le plan, le validateur le vérifie.

## Conclusion
Ce TP a permis d'enrichir IPPlan-Manager avec un système complet de validation. L'application devient plus robuste et professionnelle, capable de signaler clairement les erreurs à l'utilisateur.
