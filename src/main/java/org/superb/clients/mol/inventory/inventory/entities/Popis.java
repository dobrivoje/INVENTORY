/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superb.clients.mol.inventory.inventory.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Popis.findAll", query = "SELECT p FROM Popis p"),
    @NamedQuery(name = "Popis.findByIdp", query = "SELECT p FROM Popis p WHERE p.idp = :idp"),
    @NamedQuery(name = "Popis.findByDatum", query = "SELECT p FROM Popis p WHERE p.datum = :datum"),
    @NamedQuery(name = "Popis.findByNapomena", query = "SELECT p FROM Popis p WHERE p.napomena = :napomena"),
    @NamedQuery(name = "Popis.findByPopisZavrsen", query = "SELECT p FROM Popis p WHERE p.popisZavrsen = :popisZavrsen"),
    @NamedQuery(name = "Popis.findByDatumZavrsetkaPopisa", query = "SELECT p FROM Popis p WHERE p.datumZavrsetkaPopisa = :datumZavrsetkaPopisa"),
    @NamedQuery(name = "Popis.findByVremeZavrsetkaPopisa", query = "SELECT p FROM Popis p WHERE p.vremeZavrsetkaPopisa = :vremeZavrsetkaPopisa"),
    @NamedQuery(name = "Popis.findByPredsednikPK", query = "SELECT p FROM Popis p WHERE p.predsednikPK = :predsednikPK"),
    @NamedQuery(name = "Popis.findByClan1PK", query = "SELECT p FROM Popis p WHERE p.clan1PK = :clan1PK"),
    @NamedQuery(name = "Popis.findByClan2PK", query = "SELECT p FROM Popis p WHERE p.clan2PK = :clan2PK")})
public class Popis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idp;
    @Size(max = 10)
    private String datum;
    @Size(max = 255)
    private String napomena;
    private Boolean popisZavrsen;
    @Size(max = 10)
    private String datumZavrsetkaPopisa;
    @Size(max = 8)
    private String vremeZavrsetkaPopisa;
    @Column(name = "Predsednik_PK")
    private BigInteger predsednikPK;
    @Column(name = "Clan1_PK")
    private BigInteger clan1PK;
    @Column(name = "Clan2_PK")
    private BigInteger clan2PK;
    @Lob
    private String komentar;
    @JoinColumn(name = "FK_ID_Lokacija", referencedColumnName = "ID")
    @ManyToOne
    private Lokacija fKIDLokacija;
    @JoinColumn(name = "FK_IDGP", referencedColumnName = "IDGP")
    @ManyToOne
    private GodPopis fkIdgp;
    @OneToMany(mappedBy = "fkIdPopis")
    private List<PopisLokacija> popisLokacijaList;

    public Popis() {
    }

    public Popis(Long idp) {
        this.idp = idp;
    }

    public Long getIdp() {
        return idp;
    }

    public void setIdp(Long idp) {
        this.idp = idp;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Boolean getPopisZavrsen() {
        return popisZavrsen;
    }

    public void setPopisZavrsen(Boolean popisZavrsen) {
        this.popisZavrsen = popisZavrsen;
    }

    public String getDatumZavrsetkaPopisa() {
        return datumZavrsetkaPopisa;
    }

    public void setDatumZavrsetkaPopisa(String datumZavrsetkaPopisa) {
        this.datumZavrsetkaPopisa = datumZavrsetkaPopisa;
    }

    public String getVremeZavrsetkaPopisa() {
        return vremeZavrsetkaPopisa;
    }

    public void setVremeZavrsetkaPopisa(String vremeZavrsetkaPopisa) {
        this.vremeZavrsetkaPopisa = vremeZavrsetkaPopisa;
    }

    public BigInteger getPredsednikPK() {
        return predsednikPK;
    }

    public void setPredsednikPK(BigInteger predsednikPK) {
        this.predsednikPK = predsednikPK;
    }

    public BigInteger getClan1PK() {
        return clan1PK;
    }

    public void setClan1PK(BigInteger clan1PK) {
        this.clan1PK = clan1PK;
    }

    public BigInteger getClan2PK() {
        return clan2PK;
    }

    public void setClan2PK(BigInteger clan2PK) {
        this.clan2PK = clan2PK;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Lokacija getFKIDLokacija() {
        return fKIDLokacija;
    }

    public void setFKIDLokacija(Lokacija fKIDLokacija) {
        this.fKIDLokacija = fKIDLokacija;
    }

    public GodPopis getFkIdgp() {
        return fkIdgp;
    }

    public void setFkIdgp(GodPopis fkIdgp) {
        this.fkIdgp = fkIdgp;
    }

    @XmlTransient
    public List<PopisLokacija> getPopisLokacijaList() {
        return popisLokacijaList;
    }

    public void setPopisLokacijaList(List<PopisLokacija> popisLokacijaList) {
        this.popisLokacijaList = popisLokacijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idp != null ? idp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Popis)) {
            return false;
        }
        Popis other = (Popis) object;
        if ((this.idp == null && other.idp != null) || (this.idp != null && !this.idp.equals(other.idp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.superb.clients.mol.inventory.inventory.DB.Popis[ idp=" + idp + " ]";
    }
    
}
