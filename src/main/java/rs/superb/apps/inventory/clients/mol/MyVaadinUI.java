package rs.superb.apps.inventory.clients.mol;

import com.vaadin.annotations.Theme;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import rs.superb.apps.inventory.components.UI.Layouts.clients.mol.MainUI;
import rs.superb.apps.inventory.components.UI.clients.mol.views.GodPopis_View;

@SuppressWarnings("serial")
@Theme("mytheme")
public class MyVaadinUI extends UI {

    Navigator navigator = new Navigator(this, this);

    private static final String DARK_THEME = "mytheme";

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "rs.superb.apps.inventory.clients.mol.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        // setTheme(DARK_THEME);
        // setContent(new MainUI());
        navigator.addView(GodPopis_View.VIEW_ID, GodPopis_View.class);
        navigator.addView(MainUI.VIEW_ID, MainUI.class);
        
        navigator.navigateTo(MainUI.VIEW_ID);
    }
}
