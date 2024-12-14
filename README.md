# Réservation Hôtel - Application

Cette application permet de gérer les réservations d'hôtel avec un backend Spring Boot, un frontend Angular, et une base de données MySQL.

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les éléments suivants :

- **Java 11+** : pour exécuter le backend Spring Boot
- **Maven** : pour gérer les dépendances du projet Spring Boot
- **Node.js et npm** : pour le frontend Angular
- **MySQL** : pour la base de données

## Installation

### 1. Backend (Spring Boot)

#### a) Créer la base de données

Assurez-vous que MySQL est installé et en cours d'exécution sur votre machine. Ensuite, créez la base de données `reservation_hotel` avec la commande suivante dans MySQL :

```sql
CREATE DATABASE reservation_hotel;
b) Insérer un utilisateur admin par défaut
Un utilisateur admin sera automatiquement inséré dans la base de données lors du démarrage du backend grâce à une classe CommandLineRunner dans Spring Boot.

L'utilisateur admin est inséré avec les informations suivantes :

Email : abdo@gmail.com
Mot de passe : 1999 (haché avec bcrypt)
Le code responsable de cette insertion se trouve dans la classe AdminUserLoader.

c) Lancer l'application Spring Boot
Dans le répertoire du backend, exécutez la commande suivante pour démarrer l'application Spring Boot :

./mvnw spring-boot:run
Cela démarrera l'application backend, accessible à l'adresse suivante : http://localhost:8080.

2. Frontend (Angular)
a) Installation des dépendances
Dans le répertoire du frontend, installez les dépendances nécessaires avec npm :

npm install
b) Lancer l'application Angular
Pour démarrer l'application Angular en mode développement, exécutez la commande suivante :

ng serve
Cela démarrera le serveur Angular, accessible à l'adresse suivante : http://localhost:4200.

3. Test de l'application
Une fois les deux applications (backend et frontend) démarrées, ouvrez votre navigateur et accédez à http://localhost:4200.

Connexion : Utilisez l'email abdo@gmail.com et le mot de passe 1999.
Vous serez authentifié en tant qu'admin et pourrez tester les fonctionnalités de gestion des réservations.
