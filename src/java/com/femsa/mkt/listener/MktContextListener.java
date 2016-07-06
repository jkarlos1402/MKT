package com.femsa.mkt.listener;

import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.util.MktAdmin_CatalogLoader;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class MktContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {        
        sce.getServletContext().setAttribute("BanderaDatos", false);
        sce.getServletContext().setAttribute("BanderaDatosLanzamientosObjetivo", false);
        sce.getServletContext().setAttribute("BanderaDatosLanzamientosIdCliente", false);
        sce.getServletContext().setAttribute("BanderaDatosPromObjetivo", false);
        sce.getServletContext().setAttribute("BanderaDatosPromocionesIdCliente", false);
        sce.getServletContext().setAttribute("BanderaDatosPromocionesCanal", false);
        try {
            MktAdmin_CatalogLoader.loadCatalogs("MARKETING");
            MktAdmin_CatalogLoader.loadCatalogsDinamicos("com.femsa.mkt.pojos","MARKETING");
        } catch (MKTException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
