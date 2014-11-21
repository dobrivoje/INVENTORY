/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superb.clients.mol.inventory.inventory.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "POPIS_LOKACIJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PopisLokacija.findAll", query = "SELECT p FROM PopisLokacija p"),
    @NamedQuery(name = "PopisLokacija.findById", query = "SELECT p FROM PopisLokacija p WHERE p.id = :id"),
    @NamedQuery(name = "PopisLokacija.findByBarkod", query = "SELECT p FROM PopisLokacija p WHERE p.barkod = :barkod"),
    @NamedQuery(name = "PopisLokacija.findByDatumUpisabarkoda", query = "SELECT p FROM PopisLokacija p WHERE p.datumUpisabarkoda = :datumUpisabarkoda"),
    @NamedQuery(name = "PopisLokacija.findByVremeUpisabarkoda", query = "SELECT p FROM PopisLokacija p WHERE p.vremeUpisabarkoda = :vremeUpisabarkoda"),
    @NamedQuery(name = "PopisLokacija.findByDatumUpisa", query = "SELECT p FROM PopisLokacija p WHERE p.datumUpisa = :datumUpisa"),
    @NamedQuery(name = "PopisLokacija.findByTransfer", query = "SELECT p FROM PopisLokacija p WHERE p.transfer = :transfer"),
    @NamedQuery(name = "PopisLokacija.findByTransferBSID", query = "SELECT p FROM PopisLokacija p WHERE p.transferBSID = :transferBSID"),
    @NamedQuery(name = "PopisLokacija.findByOtpis", query = "SELECT p FROM PopisLokacija p WHERE p.otpis = :otpis"),
    @NamedQuery(name = "PopisLokacija.findByDupliran", query = "SELECT p FROM PopisLokacija p WHERE p.dupliran = :dupliran")})
public class PopisLokacija implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;
    @Size(max = 20)
    private String barkod;
    @Size(max = 10)
    @Column(name = "Datum_Upisa_barkoda")
    private String datumUpisabarkoda;
    @Size(max = 8)
    @Column(name = "Vreme_Upisa_barkoda")
    private String vremeUpisabarkoda;
    @Size(max = 10)
    private String datumUpisa;
    private Boolean transfer;
    @Column(name = "Transfer_BS_ID")
    private BigInteger transferBSID;
    private Boolean otpis;
    @Lob
    private String napomena;
    private Boolean dupliran;
    @JoinColumn(name = "FK_ID_rADNIK", referencedColumnName = "IDR")
    @ManyToOne
    private Radnik fKIDrADNIK;
    @JoinColumn(name = "FK_ID_POPIS", referencedColumnName = "IDP")
    @ManyToOne
    private Popis fkIdPopis;

    public PopisLokacija() {
    }

    public PopisLokacija(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public String getDatumUpisabarkoda() {
        return datumUpisabarkoda;
    }

    public void setDatumUpisabarkoda(String datumUpisabarkoda) {
        this.datumUpisabarkoda = datumUpisabarkoda;
    }

    public String getVremeUpisabarkoda() {
        return vremeUpisabarkoda;
    }

    public void setVremeUpisabarkoda(String vremeUpisabarkoda) {
        this.vremeUpisabarkoda = vremeUpisabarkoda;
    }

    public String getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(String datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public Boolean getTransfer() {
        return transfer;
    }

    public void setTransfer(Boolean transfer) {
        this.transfer = transfer;
    }

    public BigInteger getTransferBSID() {
        return transferBSID;
    }

    public void setTransferBSID(BigInteger transferBSID) {
        this.transferBSID = transferBSID;
    }

    public Boolean getOtpis() {
        return otpis;
    }

    public void setOtpis(Boolean otpis) {
        this.otpis = otpis;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Boolean getDupliran() {
        return dupliran;
    }

    public void setDupliran(Boolean dupliran) {
        this.dupliran = dupliran;
    }

    public Radnik getFKIDrADNIK() {
        return fKIDrADNIK;
    }

    public void setFKIDrADNIK(Radnik fKIDrADNIK) {
        this.fKIDrADNIK = fKIDrADNIK;
    }

    public Popis getFkIdPopis() {
        return fkIdPopis;
    }

    public void setFkIdPopis(Popis fkIdPopis) {
        this.fkIdPopis = fkIdPopis;
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
        if (!(object instanceof PopisLokacija)) {
            return false;
        }
        PopisLokacija other = (PopisLokacija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.superb.clients.mol.inventory.inventory.DB.PopisLokacija[ id=" + id + " ]";
    }
    
}
