package ma.enset.ikram.tp3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ressources_materiel")
public class RessourceMateriel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_inventaire", nullable = false, unique = true, length = 40)
    private String codeInventaire;

    @Column(name = "nom_materiel", nullable = false)
    private String nomMateriel;

    @Column(name = "etat_fonctionnel")
    private String etatFonctionnel = "OPERATIONNEL";

    @ManyToMany(mappedBy = "ressources")
    private Set<SalleConference> salles = new HashSet<>();

    public RessourceMateriel() {
    }

    public RessourceMateriel(String codeInventaire, String nomMateriel, String etatFonctionnel) {
        this.codeInventaire = codeInventaire;
        this.nomMateriel = nomMateriel;
        this.etatFonctionnel = etatFonctionnel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodeInventaire() { return codeInventaire; }
    public void setCodeInventaire(String codeInventaire) { this.codeInventaire = codeInventaire; }

    public String getNomMateriel() { return nomMateriel; }
    public void setNomMateriel(String nomMateriel) { this.nomMateriel = nomMateriel; }

    public String getEtatFonctionnel() { return etatFonctionnel; }
    public void setEtatFonctionnel(String etatFonctionnel) { this.etatFonctionnel = etatFonctionnel; }

    public Set<SalleConference> getSalles() { return salles; }
    public void setSalles(Set<SalleConference> salles) { this.salles = salles; }
}