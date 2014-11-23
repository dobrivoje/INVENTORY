/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.database.dbhandler.clients.mol;

import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import rs.superb.apps.inventory.database.entities.clients.mol.Lokacija;
import rs.superb.apps.inventory.database.entities.clients.mol.Radnik;
import rs.superb.apps.inventory.database.entities.clients.mol.TmpOSNSREDNAVISION;

/**
 *
 * @author root
 */
public class DBHandler {

    //<editor-fold defaultstate="collapsed" desc="System definitions">
    private static DBHandler instance;
    private static final String PERSISTENCE_UNIT_ID = "INVENTORY_PU";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_ID);
    private static final EntityManager em = emf.createEntityManager();

    public static synchronized EntityManager getEm() throws Exception {
        return em;
    }

    private DBHandler() {
    }

    public static synchronized DBHandler getDefault() {
        return instance == null ? instance = new DBHandler() : instance;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Inventory Worker Definitions">
    public List<Radnik> getAllRadnik() {
        try {
            return (List<Radnik>) getEm().createNamedQuery("Radnik.sviRadnici").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Radnik> getRadnikPoImenu(String DelimicnoIme) {
        try {
            return getEm().createNamedQuery("Radnik.poImenu")
                    .setParameter("Ime", DelimicnoIme.concat("%"))
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Radnik> getRadnikPoPrezimenu(String DelimicnoPrezime) {
        try {
            return getEm().createNamedQuery("Radnik.poPrezimenu")
                    .setParameter("Prezime", DelimicnoPrezime.concat("%"))
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Inventory Locations Definitions">
    public List<Lokacija> getAllLokacija() {
        try {
            return (List<Lokacija>) getEm().createNamedQuery("Lokacija.allLocations").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public Lokacija getLokacijaPoIDu(String IDLokacije) {
        try {
            return (Lokacija) getEm().createNamedQuery("Lokacija.findByIDLokacije").
                    setParameter("IDLokacije", IDLokacije).
                    getSingleResult();

        } catch (Exception ex) {
            return null;
        }
    }

    public Lokacija getLokacijaPoNazivu(String NazivLokacije) {
        try {
            return (Lokacija) getEm().createNamedQuery("Lokacija.findByNaziv").
                    setParameter("Naziv", NazivLokacije.concat("%")).
                    getSingleResult();

        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>

    public List<TmpOSNSREDNAVISION> getAllTmp_OS_NAV() {
        try {
            return (List<TmpOSNSREDNAVISION>) getEm().createNamedQuery("TmpOSNSREDNAVISION.findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public static synchronized void insertNew_TMP_OS_NAV(TmpOSNSREDNAVISION TMP_OS_NAV) throws Exception {
        TmpOSNSREDNAVISION tmp_os_nav;

        if (!getEm().getTransaction().isActive()) {
            getEm().getTransaction().begin();
        }

        tmp_os_nav = new TmpOSNSREDNAVISION();

        tmp_os_nav.setBarkod("" + Integer.toString(new Random(98000).nextInt()));
        tmp_os_nav.setKolicina(1 + new Random(99).nextInt());
        tmp_os_nav.setOpis("Opis-".concat(Integer.toString(1 + new Random(99).nextInt())));

        getEm().persist(tmp_os_nav);
        getEm().getTransaction().commit();
        getEm().close();
    }
}
