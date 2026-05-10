# TP1 - IPPlan-Manager

## Objectif du TP
Découverte des premières classes Java du projet IPPlan-Manager.

## Classes créées
- AdresseIP
- ReseauIP
- InterfaceReseau
- Equipement
- Main

## Travail réalisé
- Création de deux réseaux IP
- Création de 7 équipements (routeur, serveur, switch, AP, deux clients, un sans IP)
- Interface inactive et interface sans adresse IP

## Réponses aux questions

**1. Pourquoi une classe AdresseIP plutôt qu'une simple String ?**  
Pour centraliser la validation, permettre l'évolution future (IPv6, masque, calculs) et éviter la duplication de code.

**2. Différence entre classe et objet ?**  
Une classe est un modèle (un plan), un objet est une instance concrète de cette classe.

**3. Rôle du constructeur ?**  
Initialiser les attributs d'un objet lors de sa création.

**4. Pourquoi InterfaceReseau contient une AdresseIP ?**  
Parce qu'une interface réseau possède une adresse IP (relation de composition).

**5. Pourquoi Equipement contient une InterfaceReseau ?**  
Un équipement réseau a au moins une interface pour communiquer sur le réseau.

**6. Limite actuelle de la classe Equipement ?**  
Elle ne gère qu'une seule interface, alors qu'un équipement peut en avoir plusieurs.

**7. Pourquoi cette version ne permet pas un plan d'adressage automatique ?**  
Absence de calcul de sous-réseaux, validation IP, masque, etc. Cela viendra dans les prochains TP.
