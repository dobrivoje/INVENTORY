/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.superb.apps.inventory.components.UI.clients.mol;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;

/**
 *
 * @author root
 */
public class AccordionMenu extends Accordion {
    
    private final String[] sizes = {"16", "32", "64"};
    private final String[] icons = {"cancel.png", "calendar.png", "document.png",
        "email.png", "globe.png", "help.png",
        "note.png", "ok.png", "trash.png", "user.png"};
    
    public AccordionMenu() {
        setSizeFull();
        createTabs(sizes);
    }
    
    private void createTabs(String[] sizes) {
        for (String s : sizes) {
            CssLayout cssLayout = new CssLayout() {
                
                @Override
                protected String getCss(Component c) {
                    return "display: inline-block;";
                }
            };
            
            cssLayout.setCaption("Icons: " + s + ", x: " + s);
            addComponent(cssLayout);
            
            for (String icon : icons) {
                Resource imageResource = new ThemeResource("../runo/icons/" + s + "/" + icon);
                Image image = new Image(null, imageResource);
                cssLayout.addComponent(image);
            }
        }
    }
}
