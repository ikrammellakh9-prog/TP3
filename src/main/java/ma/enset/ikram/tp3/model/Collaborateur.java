package ma.enset.ikram.tp3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "collaborateurs")
public class Collaborateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String matricule;

    @Column(name = "nom_famille", nullable = false)
    private String nomFamille;

    @Column(nullable = false)
    private String prenom;

    @Column(name = "email_entreprise", nullable = false, unique = true)
    private String emailEntreprise;

    @Column(name = "departement")
    private String departement;

    @OneToMany(mappedBy = "collaborateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReservationSalle> reservations = new HashSet<>();

    public Collaborateur() {
    }

    public Collaborateur(String matricule, String nomFamille, String prenom, String emailEntreprise, String departement) {
        this.matricule = matricule;
        this.nomFamille = nomFamille;
        this.prenom = prenom;
        this.emailEntreprise = emailEntreprise;
        this.departement = departement;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getNomFamille() { return nomFamille; }
    public void setNomFamille(String nomFamille) { this.nomFamille = nomFamille; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmailEntreprise() { return emailEntreprise; }
    public void setEmailEntreprise(String emailEntreprise) { this.emailEntreprise = emailEntreprise; }

    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }

    public Set<ReservationSalle> getReservations() { return reservations; }
    public void setReservations(Set<ReservationSalle> reservations) { this.reservations = reservations; }

    public void addReservation(ReservationSalle res) {
        reservations.add(res);
        res.setCollaborateur(this);
    }
}