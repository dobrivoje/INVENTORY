package rs.superb.apps.inventory.clients.mol;

import com.vaadin.annotations.Theme;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import rs.superb.apps.inventory.components.UI.Layouts.clients.mol.MainUI;

@SuppressWarnings("serial")
@Theme("mytheme")
public class MyVaadinUI extends UI {

    private static final String DARK_THEME = "mytheme";

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "rs.superb.apps.inventory.clients.mol.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        // setTheme(DARK_THEME);
        setContent(new MainUI());
    }
}
