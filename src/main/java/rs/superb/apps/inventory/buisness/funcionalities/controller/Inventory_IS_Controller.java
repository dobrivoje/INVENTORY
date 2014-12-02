package rs.superb.apps.inventory.buisness.funcionalities.controller;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import rs.superb.apps.inventory.buisness.funcionalities.IInventory_NAV;
import rs.superb.apps.inventory.database.dbhandler.clients.mol.DBHandler;
import rs.superb.apps.inventory.database.entities.clients.mol.GodPopis;
import rs.superb.apps.inventory.database.entities.clients.mol.NavGen;

public class Inventory_IS_Controller implements IInventory_NAV {

    //<editor-fold defaultstate="collapsed" desc="Database Handler">
    private final DBHandler dBHandler;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Bean Items Containers">
    private final BeanItemContainer<GodPopis> beanItemContainer_AnnualList;
    private final BeanItemContainer<NavGen> beanItemContainer_NavGen;
    //</editor-fold>

    public Inventory_IS_Controller() {
        dBHandler = DBHandler.getDefault();
        beanItemContainer_AnnualList = new BeanItemContainer<>(GodPopis.class);
        beanItemContainer_NavGen = new BeanItemContainer<>(NavGen.class);
    }

    @Override
    public List<GodPopis> getAnnualList(NavGen navGen) {
        return dBHandler.getAnnualList(navGen);
    }

    @Override
    public List<NavGen> getAllNAV_Items() {
        return dBHandler.getAllNAV_Items();
    }

    public List<String> getAllNAV_Items_Str() {
        List<String> ll = new ArrayList<>();

        for (NavGen ng : dBHandler.getAllNAV_Items()) {
            ll.add(ng.getDatumGenerisanja());
        }

        return ll;
    }

    @Override
    public Container getBeanItemContainer_AnnualList() {
        return beanItemContainer_AnnualList;
    }

    @Override
    public Container getBeanItemContainer_InventoryLocation() {
        return beanItemContainer_NavGen;
    }

    @Override
    public void setBeanItemContainer_AnnualList(NavGen navGen) {
        this.beanItemContainer_AnnualList.addAll(getAnnualList(navGen));
    }

    @Override
    public void setBeanItemContainer_NavGen() {
        this.beanItemContainer_NavGen.addAll(getAllNAV_Items());
    }

}
