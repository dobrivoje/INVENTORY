/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.components.UI.Layouts.clients.mol;

import com.vaadin.addon.tableexport.ExcelExport;
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
import java.util.List;
import rs.superb.apps.inventory.buisness.funcionalities.IInventoryController;
import rs.superb.apps.inventory.components.UI.clients.mol.AccordionMenu;
import rs.superb.apps.inventory.controller.clients.mol.InventoryController;
import rs.superb.apps.inventory.database.entities.clients.mol.PopisLokacija;
import rs.superb.apps.inventory.database.entities.clients.mol.Radnik;

public class MainUI extends HorizontalSplitPanel {

    //<editor-fold defaultstate="collapsed" desc="final defs">
    private static final int MAIN_UI_SPLIT_RATIO_RATIO = 26;
    private static final int TABLE_PAGE_INIT = 7;
    private static final float WINDOW_WIDTH_PERCENT = 70;
    private static final float WINDOW_HEIGHT_PERCENT = 66;
    private static final String TABLE_STYLE = "mojaTabelaRadnici";
    //</editor-fold>

    private final VerticalLayout mainMenuPanel = new VerticalLayout();

    private final TextField textField_RADNIK = new TextField("Ime radnika");
    private final Table table_RADNIK = new Table("Radnici");
    private final Button button_RADNIK;

    private final Button excelExportButton = new Button("Excel export tabele");

    private final IInventoryController controller;

    public MainUI() {
        setSplitPosition(MAIN_UI_SPLIT_RATIO_RATIO, Unit.PERCENTAGE);
        setSizeFull();

        mainMenuPanel.setMargin(true);
        mainMenuPanel.setSpacing(true);
        setFirstComponent(new AccordionMenu());
        setSecondComponent(mainMenuPanel);

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
        table_RADNIK.setStyleName(TABLE_STYLE);
        table_RADNIK.setSizeFull();
        table_RADNIK.setImmediate(false);
        table_RADNIK.setSelectable(true);
        table_RADNIK.setEditable(false);
        table_RADNIK.setNullSelectionAllowed(false);
        table_RADNIK.setPageLength(TABLE_PAGE_INIT);

        //<editor-fold defaultstate="collapsed" desc="Table Items By Worker_ClickListener">
        ItemClickEvent.ItemClickListener table_RADNIK_ClickListener = new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    Radnik odabraniRadnikIzTabele = ((Radnik) event.getItemId());

                    Window window = new Window("Spisak svih artikala radnika ".concat(odabraniRadnikIzTabele.getIme()));
                    VerticalLayout winVLayout = new VerticalLayout();
                    window.setModal(true);
                    window.setWidth(WINDOW_WIDTH_PERCENT, Unit.PERCENTAGE);
                    window.setHeight(WINDOW_HEIGHT_PERCENT, Unit.PERCENTAGE);
                    window.setContent(winVLayout);

                    controller.setBeanItemController_InventoryWorker_AllItems(odabraniRadnikIzTabele);
                    Table tableWorkerINV = new Table("Sve stavke radnika", controller.getBeanItemController_InventoryWorker_ItemsByUser());
                    tableWorkerINV.setStyleName(TABLE_STYLE);
                    tableWorkerINV.setSizeFull();

                    winVLayout.addComponent(tableWorkerINV);
                    UI.getCurrent().addWindow(window);
                }
            }
        };
        //</editor-fold>

        table_RADNIK.addItemClickListener(table_RADNIK_ClickListener);

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

                        List<PopisLokacija> stavkeRadnika = controller.geInventoryWorker_AllItems(odabraniRadnikIzTabele);

                        for (PopisLokacija pl : stavkeRadnika) {
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

        excelExportButton.addClickListener(new Button.ClickListener() {
            private ExcelExport excelExport;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                excelExport = new ExcelExport(table_RADNIK);
                excelExport.excludeCollapsedColumns();
                excelExport.setReportTitle("Izvoz mojih radnika");
                excelExport.export();
            }
        });

        mainMenuPanel.addComponent(textField_RADNIK);
        mainMenuPanel.addComponent(button_RADNIK);
        mainMenuPanel.addComponent(table_RADNIK);

        mainMenuPanel.addComponent(excelExportButton);
    }
}
