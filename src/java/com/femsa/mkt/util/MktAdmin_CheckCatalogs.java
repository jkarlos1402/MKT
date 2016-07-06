package com.femsa.mkt.util;

import com.femsa.mkt.pojos.MktAdmin_Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TMXIDSJPINAM
 */
public class MktAdmin_CheckCatalogs {
    
    private MktAdmin_CheckCatalogs(){
        //para que no instancien objetos de este tipo
    }

    public static void checkAllCatalogs() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        MktAdmin_Usuario usuario = (MktAdmin_Usuario)session.getAttribute("session_user");
        List<String> notifications = new ArrayList<String>();
        session.setAttribute("notifications_user", notifications);        
    }
}
