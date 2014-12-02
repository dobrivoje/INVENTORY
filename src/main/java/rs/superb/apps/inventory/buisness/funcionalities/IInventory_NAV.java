/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.buisness.funcionalities;

import com.vaadin.data.Container;
import java.util.List;
import rs.superb.apps.inventory.database.entities.clients.mol.GodPopis;
import rs.superb.apps.inventory.database.entities.clients.mol.NavGen;

/**
 *
 * @author root
 */
public interface IInventory_NAV {

    /**
     * @param navGen Lista svih godišnjih popisa za postojeću generisanu Nav
     * listu
     * @return
     */
    List<GodPopis> getAnnualList(NavGen navGen);

    /**
     * Lista svih Navision (IS) lista
     *
     * @return
     */
    List<NavGen> getAllNAV_Items();

    public Container getBeanItemContainer_AnnualList();

    public Container getBeanItemContainer_InventoryLocation();

    public void setBeanItemContainer_AnnualList(NavGen navGen);

    public void setBeanItemContainer_NavGen();

}
