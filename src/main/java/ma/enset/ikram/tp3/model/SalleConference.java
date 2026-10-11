package ma.enset.ikram.tp3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "salles_conference")
public class SalleConference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_salle", nullable = false, unique = true, length = 30)
    private String codeSalle;

    @Column(name = "nom_salle", nullable = false)
    private String nomSalle;

    @Column(name = "capacite_max", nullable = false)
    private int capaciteMax;

    @Column(name = "etage_batiment")
    private Integer etageBatiment;

    @Column(name = "climatisation_active")
    private Boolean climatisation = true;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReservationSalle> reservations = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "salle_ressource_liaison",
            joinColumns = @JoinColumn(name = "salle_id"),
            inverseJoinColumns = @JoinColumn(name = "ressource_id")
    )
    private Set<RessourceMateriel> ressources = new HashSet<>();

    public SalleConference() {
    }

    public SalleConference(String codeSalle, String nomSalle, int capaciteMax, Integer etageBatiment) {
        this.codeSalle = codeSalle;
        this.nomSalle = nomSalle;
        this.capaciteMax = capaciteMax;
        this.etageBatiment = etageBatiment;
        this.climatisation = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodeSalle() { return codeSalle; }
    public void setCodeSalle(String codeSalle) { this.codeSalle = codeSalle; }

    public String getNomSalle() { return nomSalle; }
    public void setNomSalle(String nomSalle) { this.nomSalle = nomSalle; }

    public int getCapaciteMax() { return capaciteMax; }
    public void setCapaciteMax(int capaciteMax) { this.capaciteMax = capaciteMax; }

    public Integer getEtageBatiment() { return etageBatiment; }
    public void setEtageBatiment(Integer etageBatiment) { this.etageBatiment = etageBatiment; }

    public Boolean getClimatisation() { return climatisation; }
    public void setClimatisation(Boolean climatisation) { this.climatisation = climatisation; }

    public Set<ReservationSalle> getReservations() { return reservations; }
    public void setReservations(Set<ReservationSalle> reservations) { this.reservations = reservations; }

    public Set<RessourceMateriel> getRessources() { return ressources; }
    public void setRessources(Set<RessourceMateriel> ressources) { this.ressources = ressources; }

    public void ajouterRessource(RessourceMateriel r) {
        ressources.add(r);
        r.getSalles().add(this);
    }
}