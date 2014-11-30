/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.components.UI.clients.mol;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccordionMenu extends Accordion {

    private final String[] mainMenuCaptions = {"Podešavanja", "Popis", "Izveštaji"};

    private static final String[] images = {
        "Chrysanthemum.jpg",
        "Desert.jpg",
        "Hydrangeas.jpg",
        "Jellyfish.jpg",
        "Koala.jpg",
        "Lighthouse.jpg",
        "Penguins.jpg",
        "Tulips.jpg"
    };

    private final List<String> subMenuItems0 = new ArrayList<>(
            Arrays.asList(new String[]{"Upravljanje Grupnim Popisom", "Upravljanje Popisom na BS", "Unos Radnika", "Uvoz NAV BS"}));
    private final List<String> subMenuItems1 = new ArrayList<>(
            Arrays.asList(new String[]{"Popis OS", "Transfer OS"}));
    private final List<String> subMenuItems2 = new ArrayList<>(
            Arrays.asList(new String[]{"Izveštaj Popisa na BS", "Izveštaj Upoređenje NAV-BS", "Rezultat Popisa Trabsfera na BS"}));

    private final Map<String, List<String>> mainMenu = new HashMap<>();
    private final Map<String, ClassResource> mainMenuIcons = new HashMap<>();
    private final String ACCORDION_STYLE1 = "mojAccordion";
    private final String BUTTON_STYLE1 = "mojiDugmici_GlavniMeni";

    public AccordionMenu() {
        setStyleName(ACCORDION_STYLE1);

        mainMenu.put(mainMenuCaptions[0], subMenuItems0);
        mainMenu.put(mainMenuCaptions[1], subMenuItems1);
        mainMenu.put(mainMenuCaptions[2], subMenuItems2);

        setCaption("INVENTORY Application Menu");
        setSizeFull();
        createTabs(mainMenuCaptions);
    }

    private void createTabs(String[] mainMenuItems) {
        int ind = 0;
        for (String mainMenuItem : mainMenuItems) {
            VerticalLayout vl = new VerticalLayout();
            vl.setMargin(true);
            vl.setSpacing(true);
            vl.setCaption(mainMenuItem);
            addComponent(vl);

            for (String subMenu : mainMenu.get(mainMenuItem)) {
                Button commandButton = new Button(subMenu);
                commandButton.setStyleName(BUTTON_STYLE1);
                commandButton.setWidth("80%");
                commandButton.setHeight("80px");

                ClassResource iconResource = new ClassResource("./" + images[(ind++) % (images.length)]);
                // image.setHeight("64px");
                // image.setWidth("64px");

                mainMenuIcons.put(mainMenuItem, iconResource);

                vl.addComponent(commandButton);
            }
        }
    }
}
