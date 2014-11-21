/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superb.clients.mol.inventory.inventory.entities.dbhandler;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.superb.clients.mol.inventory.inventory.entities.Radnik;

/**
 *
 * @author root
 */
public class DBHandler {

    private static DBHandler instance;

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("corp.sys.intermol.database.KPI_PU");
    private static final EntityManager em = emf.createEntityManager();

    public static synchronized EntityManager getEm() throws Exception {
        return em;
    }

    private DBHandler() {
    }

    public static DBHandler getDefault() {
        return instance == null ? instance = new DBHandler() : instance;
    }

    public List<Radnik> getAllRadnik() {
        try {
            return (List<Radnik>)getEm().createNamedQuery("Radnik.findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<Radnik> getRadnik(String Naziv) {
        try {
            return getEm().createNamedQuery("Radnik.DelimicnoIme")
                    .setParameter("ime", Naziv)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
}
