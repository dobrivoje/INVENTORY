/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.components.UI.Layouts.clients.mol;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import rs.superb.apps.inventory.buisness.funcionalities.IInventoryController;
import rs.superb.apps.inventory.components.UI.clients.mol.AccordionMenu;
import rs.superb.apps.inventory.controller.clients.mol.InventoryController;
import rs.superb.apps.inventory.database.entities.clients.mol.PopisLokacija;
import rs.superb.apps.inventory.database.entities.clients.mol.Radnik;

public class MainUI extends HorizontalSplitPanel {

    private static final int MAIN_UI_SPLIT_RATIO_RATIO = 33;
    private static final int TABLE_PAGE_INIT = 7;

    private final VerticalLayout mainMenuPanel = new VerticalLayout();

    private final TextField textField_RADNIK = new TextField("Delimično ime: ");
    private final Table table_RADNIK = new Table("Radnici");
    private final Button button_RADNIK;

    private final IInventoryController controller;

    public MainUI() {
        setSplitPosition(MAIN_UI_SPLIT_RATIO_RATIO, Unit.PERCENTAGE);
        setSizeFull();

        mainMenuPanel.setMargin(true);
        mainMenuPanel.setSpacing(true);
        setFirstComponent(mainMenuPanel);
        setSecondComponent(new AccordionMenu());

        this.controller = new InventoryController();

        // sve staviti u MAINMENU !!!
        //<editor-fold defaultstate="collapsed" desc="TextFieldRadnik">
        textField_RADNIK.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.LAZY);
        textField_RADNIK.setDescription("Enter za potvrdu,..");
        textField_RADNIK.addShortcutListener(new ShortcutListener(null, ShortcutAction.KeyCode.ENTER, null) {

            @Override
            public void handleAction(Object sender, Object target) {
                button_RADNIK.click();
            }
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Table Spisak Artikala">
        table_RADNIK.setStyleName("mojaTabelaRadnici");
        table_RADNIK.setSizeFull();
        table_RADNIK.setImmediate(false);
        table_RADNIK.setSelectable(true);
        table_RADNIK.setEditable(false);
        table_RADNIK.setNullSelectionAllowed(false);
        table_RADNIK.setPageLength(TABLE_PAGE_INIT);

        table_RADNIK.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    Radnik odabraniRadnikIzTabele = ((Radnik) event.getItemId());

                    Window window = new Window("Spisak svih artikala radnika ".concat(odabraniRadnikIzTabele.getIme()));
                    VerticalLayout winVLayout = new VerticalLayout();
                    window.setModal(true);
                    window.setWidth(70, Unit.PERCENTAGE);
                    window.setHeight(50, Unit.PERCENTAGE);
                    window.setContent(winVLayout);

                    for (PopisLokacija pl : odabraniRadnikIzTabele.getPopisLokacijaList()) {
                        winVLayout.addComponent(new Label(pl.toString()));
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
                    public void buttonClick(Button.ClickEvent event) {
                        Radnik odabraniRadnikIzTabele = ((Radnik) tableRow);

                        Window window = new Window("Spisak svih artikala radnika ".concat(odabraniRadnikIzTabele.getIme()));
                        window.setStyleName(Reindeer.LAYOUT_BLACK);

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
        //</editor-fold>

        button_RADNIK = new Button("Radnik", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                controller.setBeanItemController_InventoryWorker(textField_RADNIK.getValue());

                table_RADNIK.setContainerDataSource(controller.getBeanItemController_InventoryWorker());
                table_RADNIK.setPageLength(Math.min(TABLE_PAGE_INIT, controller.getBeanItemController_InventoryWorker().size()));
            }
        });

        final TextField textFieldTheme = new TextField("Theme Selector");
        textFieldTheme.addShortcutListener(new ShortcutListener(null, ShortcutAction.KeyCode.ENTER, null) {

            @Override
            public void handleAction(Object sender, Object target) {
            }
        });

        mainMenuPanel.addComponent(textField_RADNIK);
        mainMenuPanel.addComponent(table_RADNIK);
        mainMenuPanel.addComponent(button_RADNIK);
    }
}
