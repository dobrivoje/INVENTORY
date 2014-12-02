/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.database.entities.clients.mol;

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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "NAV_GEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NavGen.findAll", query = "SELECT n FROM NavGen n"),
    @NamedQuery(name = "NavGen.findByIdn", query = "SELECT n FROM NavGen n WHERE n.idn = :idn"),
    @NamedQuery(name = "NavGen.findByDatumGenerisanja", query = "SELECT n FROM NavGen n WHERE n.datumGenerisanja = :datumGenerisanja"),
    @NamedQuery(name = "NavGen.findByNapomena", query = "SELECT n FROM NavGen n WHERE n.napomena = :napomena")})
public class NavGen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long idn;
    @Size(max = 10)
    private String datumGenerisanja;
    @Size(max = 255)
    private String napomena;
    @OneToMany(mappedBy = "fkIdn")
    private List<GodPopis> godPopisList;
    @OneToMany(mappedBy = "fkIdn")
    private List<OsnSredNavision> osnSredNavisionList;

    public NavGen() {
    }

    public NavGen(Long idn) {
        this.idn = idn;
    }

    public Long getIdn() {
        return idn;
    }

    public void setIdn(Long idn) {
        this.idn = idn;
    }

    public String getDatumGenerisanja() {
        return datumGenerisanja;
    }

    public void setDatumGenerisanja(String datumGenerisanja) {
        this.datumGenerisanja = datumGenerisanja;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @XmlTransient
    public List<GodPopis> getGodPopisList() {
        return godPopisList;
    }

    public void setGodPopisList(List<GodPopis> godPopisList) {
        this.godPopisList = godPopisList;
    }

    @XmlTransient
    public List<OsnSredNavision> getOsnSredNavisionList() {
        return osnSredNavisionList;
    }

    public void setOsnSredNavisionList(List<OsnSredNavision> osnSredNavisionList) {
        this.osnSredNavisionList = osnSredNavisionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idn != null ? idn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NavGen)) {
            return false;
        }
        NavGen other = (NavGen) object;
        return !((this.idn == null && other.idn != null) || (this.idn != null && !this.idn.equals(other.idn)));
    }

    @Override
    public String toString() {
        return "[" + datumGenerisanja + "]";
    }

}
