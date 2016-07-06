package com.femsa.mkt.converter;

import com.femsa.mkt.dao.MktAdmin_CatRolDAO;
import com.femsa.mkt.pojos.MktAdmin_CatRol;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author TMXIDSJPINAM
 */
@FacesConverter("rolConverter")
public class RolConverter implements Converter {

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                MktAdmin_CatRolDAO rolDAO = new MktAdmin_CatRolDAO();
                MktAdmin_CatRol rolT = rolDAO.getRol(new Integer(value));
                return rolT;

            } catch (NumberFormatException e) {
                return null;
            }            
        } else {
            return null;
        }
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            if (value instanceof MktAdmin_CatRol) {
                MktAdmin_CatRol rol = (MktAdmin_CatRol) value;
                return rol.getPkRol()+ "";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
