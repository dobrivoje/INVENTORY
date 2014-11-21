package org.superb.clients.mol.inventory.inventory;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.superb.clients.mol.inventory.inventory.entities.PopisLokacija;
import org.superb.clients.mol.inventory.inventory.entities.Radnik;
import org.superb.clients.mol.inventory.inventory.entities.TmpOSNSREDNAVISION;
import org.superb.clients.mol.inventory.inventory.entities.dbhandler.DBHandler;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    private static final DBHandler dbh = DBHandler.getDefault();
    private final BeanItemContainer<TmpOSNSREDNAVISION> bean_IC_TMP_OS_NAV = new BeanItemContainer<>(TmpOSNSREDNAVISION.class);
    private final BeanItemContainer<Radnik> bean_RADNIK = new BeanItemContainer<>(Radnik.class);

    final HorizontalSplitPanel hSplitPanel = new HorizontalSplitPanel();

    private final Table table_RADNIK = new Table("Radnici");

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "org.superb.clients.mol.inventory.inventory.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    public MyVaadinUI() {
        hSplitPanel.setSplitPosition(33, Unit.PERCENTAGE);
        hSplitPanel.setSizeFull();        
        setContent(hSplitPanel);

        table_RADNIK.setStyleName("mojaTabelaRadnici");
        table_RADNIK.setSizeFull();
        table_RADNIK.setPageLength(10);
        table_RADNIK.setImmediate(false);
        table_RADNIK.setSelectable(true);
        table_RADNIK.setEditable(false);
        table_RADNIK.setNullSelectionAllowed(false);

        table_RADNIK.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    Radnik odabraniRadnikIzTabele = ((Radnik) event.getItemId());

                    Window window = new Window("Spisak svih artikala radnika ".concat(odabraniRadnikIzTabele.getIme()));

                    VerticalLayout vl = new VerticalLayout();
                    window.setModal(true);
                    window.setWidth(70, Unit.PERCENTAGE);
                    window.setHeight(50, Unit.PERCENTAGE);
                    window.setContent(vl);

                    for (PopisLokacija pl : odabraniRadnikIzTabele.getPopisLokacijaList()) {
                        vl.addComponent(new Label(pl.toString()));
                    }

                    UI.getCurrent().addWindow(window);
                }
            }
        });

        table_RADNIK.addGeneratedColumn("Spisak Popisa Osobe", new Table.ColumnGenerator() {

            @Override
            public Object generateCell(Table source, final Object tableRow, Object tableColumn) {
                Button b = new Button(" ? ", new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        Radnik odabraniRadnikIzTabele = ((Radnik) tableRow);

                        Window window = new Window("Spisak svih artikala radnika ".concat(odabraniRadnikIzTabele.getIme()));

                        VerticalLayout vl = new VerticalLayout();
                        window.setModal(true);
                        window.setWidth(70, Unit.PERCENTAGE);
                        window.setHeight(50, Unit.PERCENTAGE);
                        window.setContent(vl);

                        for (PopisLokacija pl : odabraniRadnikIzTabele.getPopisLokacijaList()) {
                            vl.addComponent(new Label(pl.toString()));
                        }

                        UI.getCurrent().addWindow(window);
                    }
                });

                return b;
            }
        });
    }

    @Override
    protected void init(VaadinRequest request) {
        //<editor-fold defaultstate="collapsed" desc="VLayout -First Component in HSplitPanel">
        final VerticalLayout vLeftLayout = new VerticalLayout();
        vLeftLayout.setMargin(true);
        vLeftLayout.setSpacing(true);
        hSplitPanel.setFirstComponent(vLeftLayout);
        hSplitPanel.setSecondComponent(new VerticalLayout());

        final TextField textField_RADNIK = new TextField("Delimično ime: ");
        textField_RADNIK.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.LAZY);
        textField_RADNIK.setDescription("Enter za potvrdu,..");

        final Button button_RADNIK = new Button("Radnik", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                bean_RADNIK.removeAllItems();
                bean_RADNIK.addAll(dbh.getRadnikPoImenu(textField_RADNIK.getValue()));
                table_RADNIK.setContainerDataSource(bean_RADNIK);
            }
        });

        textField_RADNIK.addShortcutListener(new ShortcutListener(null, ShortcutAction.KeyCode.ENTER, null) {

            @Override
            public void handleAction(Object sender, Object target) {
                button_RADNIK.click();
            }
        });

        vLeftLayout.addComponent(textField_RADNIK);
        vLeftLayout.addComponent(button_RADNIK);
        vLeftLayout.addComponent(table_RADNIK);
        //</editor-fold>
    }

}
