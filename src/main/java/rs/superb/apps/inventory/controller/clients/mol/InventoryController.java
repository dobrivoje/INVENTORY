package rs.superb.apps.inventory.controller.clients.mol;

import rs.superb.apps.inventory.buisness.funcionalities.IInventoryController;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import rs.superb.apps.inventory.database.entities.clients.mol.Radnik;
import rs.superb.apps.inventory.database.dbhandler.clients.mol.DBHandler;

public class InventoryController implements IInventoryController {

    //<editor-fold defaultstate="collapsed" desc="Database Handler">
    private final DBHandler dBHandler;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Bean Items Containers">
    private final BeanItemContainer<Radnik> beanItemContainer_inventoryWorker;

    // private final BeanItemContainer<TmpOSNSREDNAVISION> bean_IC_TMP_OS_NAV = new BeanItemContainer<>(TmpOSNSREDNAVISION.class);
    //</editor-fold>

    public InventoryController() {
        dBHandler = DBHandler.getDefault();
        beanItemContainer_inventoryWorker = new BeanItemContainer<>(Radnik.class);
    }

    @Override
    public List<Radnik> geInventoryWorkers(String inventoryWorkerFirstName) {
        return dBHandler.getRadnikPoImenu(inventoryWorkerFirstName);
    }

    @Override
    public void setBeanItemController_InventoryWorker(String inventoryWorkerFirstName) {
        beanItemContainer_inventoryWorker.removeAllItems();
        beanItemContainer_inventoryWorker.addAll(geInventoryWorkers(inventoryWorkerFirstName));
    }

    @Override
    public Container getBeanItemController_InventoryWorker() {
        return beanItemContainer_inventoryWorker;
    }

}
