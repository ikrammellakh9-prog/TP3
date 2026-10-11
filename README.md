# TP 3 : Associations et Relations JPA / Hibernate

Projet d'implementation des associations objet-relationnelles complexes avec **JPA 2.2** et **Hibernate ORM 5.6** sur base de donnees **H2 Database**.

Realise par : **Ikram Mellakh**

---

## 1. Modeles & Relations ImplÃ©mentÃ©es

- **One-to-Many / Many-to-One** : Relation bidirectionnelle entre Collaborateur et ReservationSalle.
- **Many-to-One** : Association entre ReservationSalle et SalleConference.
- **Many-to-Many** : Table de jointure salle_ressource_liaison liant SalleConference et RessourceMateriel.

### Entite Collaborateur
![Entite Collaborateur](docs/images/01-entite-collaborateur.png)

### Entite SalleConference
![Entite SalleConference](docs/images/02-entite-salle.png)

---

## 2. Trace d'Execution et Validation des Requetes

La console ci-dessous illustre la generation automatique des cles etrangeres par Hibernate et les requetes JOIN FETCH :

![Resultats dans le Terminal](docs/images/03-execution-cmd.png)

---

## 3. Lancement

``bash
mvn clean compile
mvn exec:java "-Dexec.mainClass=ma.enset.ikram.tp3.App"
``