# TP4 - Calculs réseau automatiques

## Objectif
Introduction des calculs réseau automatiques dans IPPlan-Manager pour :
- Calculer le nombre d'hôtes à partir du CIDR
- Déterminer la classe réseau
- Convertir un CIDR en masque décimal
- Vérifier si une adresse est privée

## Notions étudiées
- **Méthodes statiques**: Appartenant à la classe, sans besoin d'instanciation
- **Calculs réseau**: Formules mathématiques pour les réseaux IP
- **CIDR**: Classless Inter-Domain Routing
- **Logique algorithmique**: Traitements automatiques
- **Classes utilitaires**: Centralisation des calculs

## Tests réalisés

### Test 1: Calcul du nombre d'hôtes
| CIDR | Nombre d'hôtes | Résultat |
|------|----------------|----------|
| /24  | 254            | ✅ Validé |
| /25  | 126            | ✅ Validé |
| /26  | 62             | ✅ Validé |
| /27  | 30             | ✅ Validé |
| /28  | 14             | ✅ Validé |
| /29  | 6              | ✅ Validé |
| /30  | 2              | ✅ Validé |

### Test 2: Détection des classes réseau
| Adresse IP | Classe | Résultat |
|------------|--------|----------|
| 192.168.1.0 | Classe C | ✅ Validé |
| 172.16.0.0 | Classe B | ✅ Validé |
| 10.0.0.0 | Classe A | ✅ Validé |
| 224.0.0.1 | Classe D | ✅ Validé |

### Test 3: Masques décimaux
| CIDR | Masque | Résultat |
|------|--------|----------|
| /24  | 255.255.255.0 | ✅ |
| /25  | 255.255.255.128 | ✅ |
| /26  | 255.255.255.192 | ✅ |

### Test 4: Détection des adresses privées
| Adresse | Statut | Résultat |
|---------|--------|----------|
| 192.168.1.0 | Privée | ✅ |
| 10.0.0.0 | Privée | ✅ |
| 172.20.0.0 | Privée | ✅ |
| 8.8.8.8 | Publique | ✅ |

## Difficultés rencontrées

1. **Compréhension du CIDR**: Il a fallu bien comprendre la relation entre le CIDR et le nombre d'hôtes

2. **Calcul des puissances**: Utilisation de Math.pow() qui retourne un double, nécessité de caster en int

3. **Masques personnalisés**:/31 et /32 sont des cas particuliers (0 et -1 hôtes)

4. **Découpage des adresses IP**: L'utilisation de split("\\.") nécessite l'échappement du point

5. **Plage d'adresses utilisables**: Le calcul des adresses réseau +1 et broadcast -1

## Réponses aux questions

### 1. Pourquoi a-t-on créé une classe utilitaire ?
Pour centraliser tous les calculs réseau dans un endroit unique, éviter la duplication de code, faciliter la maintenance et respecter le principe de séparation des préoccupations.

### 2. Quel est le rôle du mot-clé static ?
static permet d'appeler une méthode sans créer d'objet. On utilise CalculateurReseau.calculerNombreHotes(24) directement.

### 3. Pourquoi les calculs réseau sont-ils importants dans un outil IPAM ?
Ils automatisent les tâches répétitives, évitent les erreurs humaines, accélèrent les déploiements et aident les techniciens moins expérimentés.

### 4. Quelle est l'utilité du CIDR ?
Le CIDR permet de spécifier la taille du masque réseau de manière compacte (/24 au lieu de 255.255.255.0) et supporte la subnetting variable.

### 5. Pourquoi le nombre d'hôtes dépend-il du masque réseau ?
Plus le masque est grand (CIDR élevé), moins il reste de bits pour les hôtes, donc moins d'hôtes possibles.

### 6. Pourquoi certaines adresses IP sont-elles privées ?
Pour être utilisées en interne sans consommer d'adresses IPv4 publiques (rares). Elles ne sont pas routables sur Internet.

### 7. Pourquoi la séparation entre logique métier et logique de calcul améliore-t-elle le projet ?
Cela rend le code plus modulaire, plus facile à tester, à maintenir et à faire évoluer.

### 8. Pourquoi les outils de planification réseau doivent-ils automatiser les calculs ?
Pour gérer de grandes infrastructures sans erreurs, gagner du temps et standardiser les configurations.

## Conclusion
Ce TP a permis d'enrichir IPPlan-Manager avec des capacités de calcul réseau automatiques, rendant l'application plus utile et professionnelle.