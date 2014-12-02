package rs.superb.apps.inventory.components.UI.Layouts.clients.mol;

import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rs.superb.apps.inventory.buisness.funcionalities.IInventoryWorker;
import rs.superb.apps.inventory.components.UI.clients.mol.AccordionMenu;
import rs.superb.apps.inventory.buisness.funcionalities.controller.InventoryWorkerController;
import rs.superb.apps.inventory.database.entities.clients.mol.PopisLokacija;
import rs.superb.apps.inventory.database.entities.clients.mol.Radnik;

public class MainUI extends HorizontalSplitPanel implements View {

    //<editor-fold defaultstate="collapsed" desc="final defs">
    private static final int MAIN_UI_SPLIT_RATIO_RATIO = 26;
    private static final int TABLE_PAGE_INIT = 7;
    private static final float WINDOW_WIDTH_PERCENT = 70;
    private static final float WINDOW_HEIGHT_PERCENT = 66;
    private static final String TABLE_STYLE = "mojaTabelaRadnici";

    public static final String VIEW_ID = "MainUI_View";

    private int selectAllWorkers = 0;
    //</editor-fold>

    private final VerticalLayout mainMenuPanel = new VerticalLayout();
    private final FormLayout mainMenuPanelHeader = new FormLayout();
    private final VerticalLayout mainMenuPanelCenter = new VerticalLayout();
    private final HorizontalLayout mainMenuPanelFooter = new HorizontalLayout();

    private final TextField textField_RADNIK = new TextField("Ime radnika");
    private final Table table_RADNIK = new Table("Radnici");

    private final Button button_RADNIK;
    private final Button excelExportButton;
    private final Button orderingButton;
    private final Button selectAllButton;

    private final IInventoryWorker controller;

    private final Set<Radnik> narucivanjeRadnika = new HashSet<>();
    private final Set<CheckBox> narucivanjeRadnikaCheckBox = new HashSet<>();

    private Navigator navigator;

    public MainUI() {
        setSplitPosition(MAIN_UI_SPLIT_RATIO_RATIO, Unit.PERCENTAGE);
        setSizeFull();

        mainMenuPanel.setMargin(true);
        mainMenuPanel.setSpacing(true);
        setFirstComponent(new AccordionMenu());
        setSecondComponent(mainMenuPanel);

        mainMenuPanelHeader.setSpacing(true);
        mainMenuPanel.addComponent(mainMenuPanelHeader);

        mainMenuPanelCenter.setSpacing(true);
        mainMenuPanel.addComponent(mainMenuPanelCenter);

        mainMenuPanelFooter.setSpacing(true);
        mainMenuPanel.addComponent(mainMenuPanelFooter);

        this.controller = new InventoryWorkerController();

        // JAKO BITNA LINIJA !
        // ako se ne definiše, onda dugme Sellect All NEĆE selektovati sve checkBox 
        // već samo prvih 20-22 item-a iz tebele !!!
        table_RADNIK.setCacheRate(10);

        // sve staviti u MAINMENU !!!
        //<editor-fold defaultstate="collapsed" desc="TextFieldRadnik">
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
        table_RADNIK.setSelectable(true);
        table_RADNIK.setEditable(false);
        table_RADNIK.setNullSelectionAllowed(false);
        table_RADNIK.setPageLength(TABLE_PAGE_INIT);

        //<editor-fold defaultstate="collapsed" desc="Table Items By Worker_ClickListener">
        ItemClickEvent.ItemClickListener table_RADNIK_ClickListener = new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    showWindow_WorkerItems((Radnik) event.getItemId());
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
                        showWindow_WorkerItems((Radnik) tableRow);
                    }

                });

                return b;
            }
        });

        Table.ColumnGenerator genColumn_Orders = new Table.ColumnGenerator() {

            @Override
            public Object generateCell(Table source, final Object tableRow, Object tableColumn) {
                final CheckBox orderCheckBox = new CheckBox();

                narucivanjeRadnikaCheckBox.add(orderCheckBox);

                orderCheckBox.addValueChangeListener(new ValueChangeListener() {
                    @Override
                    public void valueChange(ValueChangeEvent event) {
                        if (orderCheckBox.getValue()) {
                            narucivanjeRadnika.add((Radnik) tableRow);
                        } else {
                            narucivanjeRadnika.remove((Radnik) tableRow);
                        }
                    }
                });

                return orderCheckBox;
            }
        };
        table_RADNIK.addGeneratedColumn("Naručiti ?", genColumn_Orders);
        //</editor-fold>

        button_RADNIK = new Button("Radnik", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                controller.setBeanItemController_InventoryWorker(textField_RADNIK.getValue());
                table_RADNIK.setContainerDataSource(controller.getBeanItemController_InventoryWorker());
                table_RADNIK.setPageLength(Math.min(TABLE_PAGE_INIT, controller.getBeanItemController_InventoryWorker().size()));
            }
        });
        excelExportButton = new Button("Excel export tabele", new Button.ClickListener() {
            private ExcelExport excelExport;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                excelExport = new ExcelExport(table_RADNIK);
                excelExport.excludeCollapsedColumns();
                excelExport.setReportTitle("Izvoz mojih radnika");
                excelExport.export();
            }
        });
        orderingButton = new Button("Naručivanje", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                showWindow_WorkerOrderList();
            }
        });
        selectAllButton = new Button("Select all", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if ((selectAllWorkers++) % 2 == 0) {
                    setCaption("Unselect all");
                    for (CheckBox cb : narucivanjeRadnikaCheckBox) {
                        cb.setValue(true);
                    }
                } else {
                    setCaption("Select all");
                    for (CheckBox cb : narucivanjeRadnikaCheckBox) {
                        cb.setValue(false);
                    }
                }
            }
        });

        mainMenuPanelHeader.addComponent(textField_RADNIK);
        mainMenuPanelHeader.addComponent(button_RADNIK);

        mainMenuPanelCenter.addComponent(table_RADNIK);

        mainMenuPanelFooter.addComponent(excelExportButton);
        mainMenuPanelFooter.addComponent(orderingButton);
        mainMenuPanelFooter.addComponent(selectAllButton);
    }

    private void showWindow_WorkerItems(Radnik selectedWorker) {
        Window window = new Window("Spisak svih artikala radnika ".concat(selectedWorker.getIme()));
        window.setStyleName(Reindeer.LAYOUT_BLACK);

        VerticalLayout vl = new VerticalLayout();
        window.setModal(true);
        window.setWidth(WINDOW_WIDTH_PERCENT, Unit.PERCENTAGE);
        window.setHeight(WINDOW_HEIGHT_PERCENT, Unit.PERCENTAGE);
        window.setContent(vl);

        List<PopisLokacija> stavkeRadnika = controller.geInventoryWorker_AllItems(selectedWorker);

        for (PopisLokacija pl : stavkeRadnika) {
            vl.addComponent(new Label(pl.toString()));
        }

        UI.getCurrent().addWindow(window);
    }

    private void showWindow_WorkerOrderList() {
        Window window = new Window("Spisak naručenih radnika.");
        VerticalLayout vl = new VerticalLayout();

        window.center();
        window.setWidth(WINDOW_WIDTH_PERCENT, Unit.PERCENTAGE);
        window.setHeight(WINDOW_HEIGHT_PERCENT, Unit.PERCENTAGE);
        window.setContent(vl);

        for (Radnik r : narucivanjeRadnika) {
            vl.addComponent(new Label(r.toString()));
        }

        UI.getCurrent().addWindow(window);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
