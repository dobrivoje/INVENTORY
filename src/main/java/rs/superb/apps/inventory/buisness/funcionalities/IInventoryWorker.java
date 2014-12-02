/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.buisness.funcionalities;

import com.vaadin.data.Container;
import java.util.List;
import rs.superb.apps.inventory.database.entities.clients.mol.PopisLokacija;
import rs.superb.apps.inventory.database.entities.clients.mol.Radnik;

/**
 *
 * @author root
 */
public interface IInventoryWorker {

    /**
     * Get Vaadin BeanItemContainer for the POJO Invetory Worker bean
     *
     * @return
     */
    Container getBeanItemController_InventoryWorker();

    /**
     * Get Vaadin BeanItemContainer for the POJO Invetory Worker bean
     *
     * @return
     */
    Container getBeanItemController_InventoryWorker_ItemsByUser();

    /**
     * Set Vaadin BeanItemContainer for the POJO
     *
     * @param inventoryWorker Set list of Inventories read by the worker, by
     * supplying worker object
     */
    void setBeanItemController_InventoryWorker_AllItems(Radnik inventoryWorker);

    /**
     * Set Vaadin BeanItemContainer for the POJO
     *
     * @param inventoryWorkerFirstName Set list of Inventory workers, by
     * supplying partial or whole first name
     */
    void setBeanItemController_InventoryWorker(String inventoryWorkerFirstName);

    /**
     * @param inventoryWorkerFirstName Get list of inventory workers, by
     * supplying partial or whole first name
     * @return
     */
    List<Radnik> geInventoryWorkers(String inventoryWorkerFirstName);

    /**
     * @param inventoryWorker Get list of all inventory items read by the
     * worker, by supplying worker object
     * @return
     */
    List<PopisLokacija> geInventoryWorker_AllItems(Radnik inventoryWorker);

}
