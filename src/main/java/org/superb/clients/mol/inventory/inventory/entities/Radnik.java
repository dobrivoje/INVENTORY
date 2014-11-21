/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superb.clients.mol.inventory.inventory.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Radnik.sviRadnici", query = "SELECT r FROM Radnik r"),
    @NamedQuery(name = "Radnik.poImenu", query = "SELECT r FROM Radnik r WHERE r.ime LIKE :Ime"),
    @NamedQuery(name = "Radnik.poPrezimenu", query = "SELECT r FROM Radnik r WHERE r.ime LIKE :Prezime")})
public class Radnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idr;
    @Size(max = 255)
    private String ime;
    @Size(max = 255)
    private String prezime;
    @OneToMany(mappedBy = "fKIDrADNIK")
    private List<PopisLokacija> popisLokacijaList;

    public Radnik() {
    }

    public Radnik(Long idr) {
        this.idr = idr;
    }

    public Long getIdr() {
        return idr;
    }

    public void setIdr(Long idr) {
        this.idr = idr;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
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
        hash += (idr != null ? idr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Radnik)) {
            return false;
        }
        Radnik other = (Radnik) object;
        return !((this.idr == null && other.idr != null) || (this.idr != null && !this.idr.equals(other.idr)));
    }

    @Override
    public String toString() {
        return "RADNIK [id=" + idr + "], "
                + "[Ime=" + ime + "], "
                + "[Prezime=" + prezime + "]";
    }

}
