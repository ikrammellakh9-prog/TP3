package ma.enset.ikram.tp3;

import ma.enset.ikram.tp3.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("  TP3 JPA - ASSOCIATIONS ET RELATIONS BIDIRECTIONNELLES   ");
        System.out.println("               (IKRAM MELLAKH - ENSET)                   ");
        System.out.println("==========================================================");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ikramTP3_PU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            System.out.println("\n[1] Creation des Ressources Materiel (Many-to-Many)");
            RessourceMateriel r1 = new RessourceMateriel("MAT-VISIO-01", "Borne Visioconference 4K", "OPERATIONNEL");
            RessourceMateriel r2 = new RessourceMateriel("MAT-MICRO-02", "Systeme Micros Sans Fil Jabra", "OPERATIONNEL");
            RessourceMateriel r3 = new RessourceMateriel("MAT-TBI-03", "Ecran Tactile Collaboratif 75 pouces", "OPERATIONNEL");

            System.out.println("\n[2] Creation des Salles de Conference avec Equipements");
            SalleConference s1 = new SalleConference("CONF-AL-KHAWARIZMI", "Amphitheatre Al Khawarizmi", 80, 1);
            s1.ajouterRessource(r1);
            s1.ajouterRessource(r2);

            SalleConference s2 = new SalleConference("CONF-ADA-LOVELACE", "Espace d'Innovation Ada Lovelace", 25, 2);
            s2.ajouterRessource(r1);
            s2.ajouterRessource(r3);

            em.persist(s1);
            em.persist(s2);

            System.out.println("\n[3] Creation du Collaborateur et Reservations (One-to-Many)");
            Collaborateur col1 = new Collaborateur("MAT-9881", "Mellakh", "Ikram", "ikram.mellakh@enset-media.ac.ma", "Genie Informatique");
            Collaborateur col2 = new Collaborateur("MAT-4421", "Zahraoui", "Salma", "salma.zahraoui@tech-corp.com", "Data Science");

            ReservationSalle res1 = new ReservationSalle(
                    LocalDateTime.now().plusDays(1).withHour(9).withMinute(0),
                    LocalDateTime.now().plusDays(1).withHour(11).withMinute(30),
                    "Atelier Architecture Microservices & JPA Hibernate",
                    40
            );
            res1.setSalle(s1);
            s1.getReservations().add(res1);
            col1.addReservation(res1);

            ReservationSalle res2 = new ReservationSalle(
                    LocalDateTime.now().plusDays(2).withHour(14).withMinute(0),
                    LocalDateTime.now().plusDays(2).withHour(16).withMinute(0),
                    "Revue Technique Sprint Machine Learning",
                    18
            );
            res2.setSalle(s2);
            s2.getReservations().add(res2);
            col1.addReservation(res2);

            em.persist(col1);
            em.persist(col2);

            em.getTransaction().commit();
            System.out.println("-> Entites et relations persistees avec succes.");

            System.out.println("\n[4] Requetes de Verification des Associations");
            List<ReservationSalle> listeReservations = em.createQuery(
                    "SELECT r FROM ReservationSalle r JOIN FETCH r.salle JOIN FETCH r.collaborateur ORDER BY r.dateDebut",
                    ReservationSalle.class
            ).getResultList();

            System.out.println("-> Nombre de reservations enregistrees : " + listeReservations.size());
            for (ReservationSalle res : listeReservations) {
                System.out.println("   * Reservation: " + res.getObjetReunion() +
                        " | Salle: " + res.getSalle().getNomSalle() +
                        " | Reserve par: " + res.getCollaborateur().getPrenom() + " " + res.getCollaborateur().getNomFamille() +
                        " (" + res.getNbInvites() + " invites)");
            }

            System.out.println("\n[5] Verification Many-to-Many : Ressources de la salle " + s1.getNomSalle());
            SalleConference salleVerif = em.find(SalleConference.class, s1.getId());
            for (RessourceMateriel mat : salleVerif.getRessources()) {
                System.out.println("   -> Ressource associee : " + mat.getNomMateriel() + " [" + mat.getCodeInventaire() + "]");
            }

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
            System.out.println("\n==========================================================");
            System.out.println("  EXECUTION DU TP3 TERMINEE AVEC SUCCES (BUILD SUCCESS)");
            System.out.println("==========================================================");
        }
    }
}