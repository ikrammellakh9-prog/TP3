package ma.enset.ikram.tp3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations_salles")
public class ReservationSalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_debut", nullable = false)
    private LocalDateTime dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDateTime dateFin;

    @Column(name = "objet_reunion", nullable = false)
    private String objetReunion;

    @Column(name = "nb_invites")
    private Integer nbInvites = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_demande", nullable = false)
    private StatutDemande statutDemande = StatutDemande.CONFIRMEE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborateur_id", nullable = false)
    private Collaborateur collaborateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salle_id", nullable = false)
    private SalleConference salle;

    public ReservationSalle() {
    }

    public ReservationSalle(LocalDateTime dateDebut, LocalDateTime dateFin, String objetReunion, Integer nbInvites) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.objetReunion = objetReunion;
        this.nbInvites = nbInvites;
        this.statutDemande = StatutDemande.CONFIRMEE;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }

    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }

    public String getObjetReunion() { return objetReunion; }
    public void setObjetReunion(String objetReunion) { this.objetReunion = objetReunion; }

    public Integer getNbInvites() { return nbInvites; }
    public void setNbInvites(Integer nbInvites) { this.nbInvites = nbInvites; }

    public StatutDemande getStatutDemande() { return statutDemande; }
    public void setStatutDemande(StatutDemande statutDemande) { this.statutDemande = statutDemande; }

    public Collaborateur getCollaborateur() { return collaborateur; }
    public void setCollaborateur(Collaborateur collaborateur) { this.collaborateur = collaborateur; }

    public SalleConference getSalle() { return salle; }
    public void setSalle(SalleConference salle) { this.salle = salle; }
}