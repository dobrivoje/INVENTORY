package rs.superb.apps.inventory.components.UI.clients.mol.views;

import com.vaadin.data.Property;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import rs.superb.apps.inventory.buisness.funcionalities.controller.Inventory_IS_Controller;
import rs.superb.apps.inventory.components.UI.Layouts.clients.mol.MainUI;
import rs.superb.apps.inventory.database.entities.clients.mol.NavGen;

/**
 *
 * @author root
 */
public class GodPopis_View extends FormLayout implements View {
    
    public static final String VIEW_ID = "AnnualInventory_View";
    private final Inventory_IS_Controller controller = new Inventory_IS_Controller();
    
    public GodPopis_View() {
        setCaption("Godišnji Popis");
        setSizeFull();
        setMargin(true);
        setSpacing(true);
        
        addComponent(new Label("Grupni popis - Unapred definisani skup popisa na jednoj ili više BS."));
        addComponent(new Label("_____________________________________________________________________"));
        
        final TextField datumTextField = new TextField("Datum");
        datumTextField.addShortcutListener(new ShortcutListener("777", ShortcutAction.KeyCode.ENTER, null) {
            
            @Override
            public void handleAction(Object sender, Object target) {
                UI.getCurrent().getNavigator().navigateTo(MainUI.VIEW_ID);
            }
        });
        
        ComboBox navDatumGenerisanja = new ComboBox("NAV - Datum generisanja", controller.getAllNAV_Items());
        navDatumGenerisanja.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
        navDatumGenerisanja.addValueChangeListener(new Property.ValueChangeListener() {
            
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                datumTextField.setValue(((NavGen) event.getProperty().getValue()).getDatumGenerisanja());
            }
        });
        
        addComponents(datumTextField, navDatumGenerisanja);
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
    
}
