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
    @NamedQuery(name = "Lokacija.allLocations", query = "SELECT l FROM Lokacija l"),
    @NamedQuery(name = "Lokacija.findByIDLokacije", query = "SELECT l FROM Lokacija l WHERE l.iDLokacije = :IDLokacije"),
    @NamedQuery(name = "Lokacija.findByNaziv", query = "SELECT l FROM Lokacija l WHERE l.naziv LIKE :Naziv"),
    @NamedQuery(name = "Lokacija.findByTip", query = "SELECT l FROM Lokacija l WHERE l.tip = :tip")})
public class Lokacija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Size(max = 10)
    private String iDLokacije;
    @Size(max = 40)
    private String naziv;
    @Size(max = 6)
    private String tip;
    @OneToMany(mappedBy = "fKIDLokacija")
    private List<Popis> popisList;

    public Lokacija() {
    }

    public Lokacija(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIDLokacije() {
        return iDLokacije;
    }

    public void setIDLokacije(String iDLokacije) {
        this.iDLokacije = iDLokacije;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @XmlTransient
    public List<Popis> getPopisList() {
        return popisList;
    }

    public void setPopisList(List<Popis> popisList) {
        this.popisList = popisList;
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
        if (!(object instanceof Lokacija)) {
            return false;
        }
        Lokacija other = (Lokacija) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "LOKACIJA [id=" + id + "], "
                + "[Naziv=" + naziv + "], "
                + "[Tip=" + tip + "]";
    }
}
