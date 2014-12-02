/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.components.UI.clients.mol;

import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rs.superb.apps.inventory.components.UI.clients.mol.views.GodPopis_View;

public class AccordionMenu extends Accordion {
    
    private final String[] mainMenuCaptions = {"Podešavanja", "Popis", "Izveštaji"};
    private final String[] sMB0C = {"Upravljanje Grupnim Popisom", "Upravljanje Popisom na BS", "Unos Radnika", "Uvoz NAV - BS"};
    private final String[] sMB1C = {"Popis OS", "Unos Radnika"};
    private final String[] sMB2C = {"Izveštaj Popisa na BS", "Izveštaj Upoređenje NAV-BS", "Rezultat Popisa Trabsfera na BS"};
    
    private final List<Button> subMenuButtons0 = new ArrayList<>(
            Arrays.asList(new Button[]{new Button(sMB0C[0]), new Button(sMB0C[1]), new Button(sMB0C[2]), new Button(sMB0C[3])}));
    
    private final List<Button> subMenuButtons1 = new ArrayList<>(
            Arrays.asList(new Button[]{new Button(sMB1C[0]), new Button(sMB1C[1])}));
    
    private final List<Button> subMenuButtons2 = new ArrayList<>(
            Arrays.asList(new Button[]{new Button(sMB2C[0]), new Button(sMB2C[1]), new Button(sMB2C[2])}));
    
    private final Map<String, List<Button>> mainMenu = new HashMap<>();
    private final String ACCORDION_STYLE1 = "mojAccordion";
    private final String BUTTON_STYLE1 = "mojiDugmici_GlavniMeni";
    
    public AccordionMenu() {
        setStyleName(ACCORDION_STYLE1);
        
        mainMenu.put(mainMenuCaptions[0], subMenuButtons0);
        mainMenu.put(mainMenuCaptions[1], subMenuButtons1);
        mainMenu.put(mainMenuCaptions[2], subMenuButtons2);
        
        setCaption("INVENTORY Application Menu");
        setSizeFull();
        createTabs(mainMenuCaptions);
    }
    
    private void createTabs(String[] mainMenuItems) {
        for (String mainMenuItem : mainMenuItems) {
            VerticalLayout vl = new VerticalLayout();
            vl.setMargin(true);
            vl.setSpacing(true);
            vl.setCaption(mainMenuItem);
            addComponent(vl);
            
            for (Button subMenuButton : mainMenu.get(mainMenuItem)) {
                subMenuButton.setStyleName(BUTTON_STYLE1);
                subMenuButton.setWidth("80%");
                subMenuButton.setHeight("90px");
                
                vl.addComponent(subMenuButton);
                vl.setComponentAlignment(subMenuButton, Alignment.MIDDLE_CENTER);
            }
            
            subMenuButtons0.get(0).addClickListener(new Button.ClickListener() {
                
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Window w = new Window("Click" + subMenuButtons0.get(0).getCaption());
                    w.setWidth(66, Unit.PERCENTAGE);
                    w.setHeight(66, Unit.PERCENTAGE);
                    w.center();
                    UI.getCurrent().addWindow(w);
                }
            });
            subMenuButtons0.get(1).addClickListener(new Button.ClickListener() {
                
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    UI.getCurrent().getNavigator().navigateTo(GodPopis_View.VIEW_ID);
                }
            });
        }
    }
}
