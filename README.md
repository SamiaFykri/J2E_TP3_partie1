# TP J2EE - Partie 1 : Gestion des Patients avec Spring Boot

Ce projet fait partie d’un TP sur Java EE / Spring Boot, avec pour objectif de construire une mini-application de gestion des patients. Il s'agit d'une interface web permettant de lister, rechercher et supprimer des patients à partir d'une base de données MySQL, avec Spring Data JPA et Thymeleaf.

---

## Structure du projet

Voici un aperçu des dossiers/fichiers principaux :

```
src/
├── main/
│   ├── java/org/example/tp3/
│   │   ├── entities/Patient.java         # L'entité JPA représentant un patient
│   │   ├── repository/PatientRepository  # Interface Spring Data JPA
│   │   └── web/PatientController.java    # Contrôleur principal (GET /index, /delete)
│   └── resources/
│       ├── templates/patients.html       # Vue principale (Thymeleaf)
│       └── application.properties        # Configuration BDD & JPA
```

---

## Fonctionnalités principales

- Affichage paginé de la liste des patients
- Recherche de patients par mot-clé (nom)
- Suppression d’un patient
- Interface responsive avec Bootstrap
- Connexion à une base de données MySQL

---

##  Contrôleur `PatientController.java`

Ce contrôleur gère la logique côté serveur. Il possède deux endpoints :

- `@GetMapping("/index")` : affiche la liste des patients, avec pagination et recherche.
- `@GetMapping("/delete")` : supprime un patient via son ID, puis redirige vers la page principale.

Pagination et recherche sont faites avec Spring Data via la méthode :
```java
patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
```

---

##  Vue `patients.html` (Thymeleaf)

Il s'agit de la **page principale de l’application**, dans laquelle on retrouve :

- Un formulaire de recherche par nom (`keyword`)
- Un tableau affichant les patients : ID, nom, date de naissance, score, malade
- Un bouton pour supprimer chaque patient
- Un système de pagination dynamique (avec boucle sur les pages)

Exemple de bloc de pagination :
```html
<li th:each="page,status:${pages}">
  <a th:href="@{/index(page=${status.index}, keyword=${keyword})}"
     th:class="${(currentPage==status.index)?'btn btn-info ms-1':'btn btn-outline-info ms-1'}"
     th:text="${status.index}"></a>
</li>
```

---

## Fichier `application.properties`

Ce fichier configure la base de données, Hibernate et le serveur :

```properties
spring.application.name=tp3
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/tp3-Patient?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect

debug=true
```

---

## Dépendances utilisées

Ce projet utilise notamment :

- `spring-boot-starter-data-jpa` : accès aux données via JPA
- `spring-boot-starter-thymeleaf` : moteur de template Thymeleaf
- `spring-boot-starter-web` : développement web MVC
- `mysql-connector-j` : pilote JDBC pour MySQL
- `webjars/bootstrap` : pour le style via Bootstrap
- `lombok` : annotations pour simplifier le code Java 

---

## Lancement de l’application

1. MySQL tourne en local sur le port 3306.
2. Le schéma de BDD sera automatiquement créé à la première exécution grâce à `spring.jpa.hibernate.ddl-auto=update`.
3. Accédez à l’URL : [http://localhost:8080/index](http://localhost:8080/index)

## Schema de la table patient
![Capture d'écran 2025-04-14 151141](https://github.com/user-attachments/assets/833a78de-f074-4158-b3f3-fba147639dc4)

## Execution
<img width="947" alt="tp3" src="https://github.com/user-attachments/assets/2714c957-3f75-4378-8e31-34d4becf4ec5" />
