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
@Table(name = "tmp_OSN_SRED_NAVISION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmpOSNSREDNAVISION.findAll", query = "SELECT t FROM TmpOSNSREDNAVISION t"),
    @NamedQuery(name = "TmpOSNSREDNAVISION.findByBroj", query = "SELECT t FROM TmpOSNSREDNAVISION t WHERE t.broj = :broj"),
    @NamedQuery(name = "TmpOSNSREDNAVISION.findByBarkod", query = "SELECT t FROM TmpOSNSREDNAVISION t WHERE t.barkod = :barkod"),
    @NamedQuery(name = "TmpOSNSREDNAVISION.findByOpis", query = "SELECT t FROM TmpOSNSREDNAVISION t WHERE t.opis = :opis"),
    @NamedQuery(name = "TmpOSNSREDNAVISION.findBySifraCC", query = "SELECT t FROM TmpOSNSREDNAVISION t WHERE t.sifraCC = :sifraCC")})
public class TmpOSNSREDNAVISION implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "Broj")
    private String broj;
    @Size(max = 20)
    @Column(name = "Barkod")
    private String barkod;
    @Size(max = 255)
    @Column(name = "Opis")
    private String opis;
    @Column(name = "Sifra_CC")
    private Integer sifraCC;
    @Size(max = 255)
    @Column(name = "Sifra_lokacije")
    private String sifralokacije;
    @Size(max = 255)
    @Column(name = "Odgovorni_zaposleni")
    private String odgovornizaposleni;
    @Column(name = "Kolicina")
    private Integer kolicina;
    @Size(max = 255)
    @Column(name = "Napomena_za_OS")
    private String napomenazaOS;
    @Size(max = 10)
    @Column(name = "Datum_Uvoza")
    private String datumUvoza;
    @Size(max = 12)
    @Column(name = "Vreme_Uvoza")
    private String vremeUvoza;

    public TmpOSNSREDNAVISION() {
    }

    public TmpOSNSREDNAVISION(Long id) {
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

    public Integer getSifraCC() {
        return sifraCC;
    }

    public void setSifraCC(Integer sifraCC) {
        this.sifraCC = sifraCC;
    }

    public String getSifralokacije() {
        return sifralokacije;
    }

    public void setSifralokacije(String sifralokacije) {
        this.sifralokacije = sifralokacije;
    }

    public String getOdgovornizaposleni() {
        return odgovornizaposleni;
    }

    public void setOdgovornizaposleni(String odgovornizaposleni) {
        this.odgovornizaposleni = odgovornizaposleni;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getNapomenazaOS() {
        return napomenazaOS;
    }

    public void setNapomenazaOS(String napomenazaOS) {
        this.napomenazaOS = napomenazaOS;
    }

    public String getDatumUvoza() {
        return datumUvoza;
    }

    public void setDatumUvoza(String datumUvoza) {
        this.datumUvoza = datumUvoza;
    }

    public String getVremeUvoza() {
        return vremeUvoza;
    }

    public void setVremeUvoza(String vremeUvoza) {
        this.vremeUvoza = vremeUvoza;
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
        if (!(object instanceof TmpOSNSREDNAVISION)) {
            return false;
        }
        TmpOSNSREDNAVISION other = (TmpOSNSREDNAVISION) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "org.superb.clients.mol.inventory.inventory.entities.TmpOSNSREDNAVISION[ id=" + id + " ]";
    }

}
