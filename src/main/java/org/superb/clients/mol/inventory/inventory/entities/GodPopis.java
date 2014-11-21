/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superb.clients.mol.inventory.inventory.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "GOD_POPIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GodPopis.findAll", query = "SELECT g FROM GodPopis g"),
    @NamedQuery(name = "GodPopis.findByIdgp", query = "SELECT g FROM GodPopis g WHERE g.idgp = :idgp"),
    @NamedQuery(name = "GodPopis.findByDatum", query = "SELECT g FROM GodPopis g WHERE g.datum = :datum"),
    @NamedQuery(name = "GodPopis.findByRedovan", query = "SELECT g FROM GodPopis g WHERE g.redovan = :redovan"),
    @NamedQuery(name = "GodPopis.findByPopiszavrsen", query = "SELECT g FROM GodPopis g WHERE g.popiszavrsen = :popiszavrsen")})
public class GodPopis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idgp;
    @Size(max = 10)
    private String datum;
    private Boolean redovan;
    @Column(name = "Popis_zavrsen")
    private Boolean popiszavrsen;
    @Lob
    private String napomena;
    @OneToMany(mappedBy = "fkIdgp")
    private List<Popis> popisList;
    @JoinColumn(name = "FK_IDN", referencedColumnName = "IDN")
    @ManyToOne
    private NavGen fkIdn;

    public GodPopis() {
    }

    public GodPopis(Long idgp) {
        this.idgp = idgp;
    }

    public Long getIdgp() {
        return idgp;
    }

    public void setIdgp(Long idgp) {
        this.idgp = idgp;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Boolean getRedovan() {
        return redovan;
    }

    public void setRedovan(Boolean redovan) {
        this.redovan = redovan;
    }

    public Boolean getPopiszavrsen() {
        return popiszavrsen;
    }

    public void setPopiszavrsen(Boolean popiszavrsen) {
        this.popiszavrsen = popiszavrsen;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @XmlTransient
    public List<Popis> getPopisList() {
        return popisList;
    }

    public void setPopisList(List<Popis> popisList) {
        this.popisList = popisList;
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
        hash += (idgp != null ? idgp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GodPopis)) {
            return false;
        }
        GodPopis other = (GodPopis) object;
        return !((this.idgp == null && other.idgp != null) || (this.idgp != null && !this.idgp.equals(other.idgp)));
    }

    @Override
    public String toString() {
        return "org.superb.clients.mol.inventory.inventory.DB.GodPopis[ idgp=" + idgp + " ]";
    }

}
