# TP 3 : Relations JPA & Cascade (Ikram Mellakh)

ImplÃ©mentation des associations d'entitÃ©s avec JPA 2.2 :
- `@OneToMany` et `@ManyToOne` bidirectionnel avec `orphanRemoval = true`
- `@ManyToMany` avec table de jointure explicite `@JoinTable`
- Cycle de vie en cascade (`CascadeType.ALL`, `CascadeType.PERSIST`, `CascadeType.MERGE`)

---

## Captures d'Ã‰cran IntelliJ IDEA & Terminal CMD

### 1. EntitÃ© SalleConference avec Collections & Jointures (IntelliJ IDEA)
![EntitÃ© Salle](docs/images/01-intellij-entity-salle.png)

### 2. EntitÃ© ReservationSalle & ClÃ©s Ã‰trangÃ¨res (IntelliJ IDEA)
![EntitÃ© RÃ©servation](docs/images/02-intellij-entity-reservation.png)

### 3. RÃ©sultat des Tests et ExÃ©cution (CMD / PowerShell)
![RÃ©sultat ExÃ©cution CMD](docs/images/03-cmd-test-result.png)

---

## Lancement
```bash
mvn clean compile exec:java -Dexec.mainClass=ma.enset.ikram.tp3.App
```