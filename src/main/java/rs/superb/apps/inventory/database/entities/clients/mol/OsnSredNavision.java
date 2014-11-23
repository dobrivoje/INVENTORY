/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.database.entities.clients.mol;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "OSN_SRED_NAVISION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OsnSredNavision.findAll", query = "SELECT o FROM OsnSredNavision o"),
    @NamedQuery(name = "OsnSredNavision.findById", query = "SELECT o FROM OsnSredNavision o WHERE o.id = :id"),
    @NamedQuery(name = "OsnSredNavision.findByBroj", query = "SELECT o FROM OsnSredNavision o WHERE o.broj = :broj"),
    @NamedQuery(name = "OsnSredNavision.findByBarkod", query = "SELECT o FROM OsnSredNavision o WHERE o.barkod = :barkod"),
    @NamedQuery(name = "OsnSredNavision.findByOpis", query = "SELECT o FROM OsnSredNavision o WHERE o.opis = :opis"),
    @NamedQuery(name = "OsnSredNavision.findBySifraCC", query = "SELECT o FROM OsnSredNavision o WHERE o.sifraCC = :sifraCC"),
    @NamedQuery(name = "OsnSredNavision.findBySifraLokacije", query = "SELECT o FROM OsnSredNavision o WHERE o.sifraLokacije = :sifraLokacije"),
    @NamedQuery(name = "OsnSredNavision.findByOdgovornizaposleni", query = "SELECT o FROM OsnSredNavision o WHERE o.odgovornizaposleni = :odgovornizaposleni")})
public class OsnSredNavision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Size(max = 255)
    private String broj;
    @Size(max = 20)
    private String barkod;
    @Size(max = 255)
    private String opis;
    @Size(max = 10)
    @Column(name = "Sifra_CC")
    private String sifraCC;
    @Size(max = 255)
    @Column(name = "Sifra_Lokacije")
    private String sifraLokacije;
    @Size(max = 255)
    @Column(name = "Odgovorni_zaposleni")
    private String odgovornizaposleni;
    @JoinColumn(name = "FK_IDN", referencedColumnName = "IDN")
    @ManyToOne
    private NavGen fkIdn;

    public OsnSredNavision() {
    }

    public OsnSredNavision(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSifraCC() {
        return sifraCC;
    }

    public void setSifraCC(String sifraCC) {
        this.sifraCC = sifraCC;
    }

    public String getSifraLokacije() {
        return sifraLokacije;
    }

    public void setSifraLokacije(String sifraLokacije) {
        this.sifraLokacije = sifraLokacije;
    }

    public String getOdgovornizaposleni() {
        return odgovornizaposleni;
    }

    public void setOdgovornizaposleni(String odgovornizaposleni) {
        this.odgovornizaposleni = odgovornizaposleni;
    }

    public NavGen getFkIdn() {
        return fkIdn;
    }

    public void setFkIdn(NavGen fkIdn) {
        this.fkIdn = fkIdn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OsnSredNavision)) {
            return false;
        }
        OsnSredNavision other = (OsnSredNavision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.superb.clients.mol.inventory.inventory.DB.OsnSredNavision[ id=" + id + " ]";
    }
    
}
