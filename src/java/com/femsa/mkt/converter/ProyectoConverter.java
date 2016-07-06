package com.femsa.mkt.converter;

import com.femsa.mkt.dao.MktAdmin_CatProyectoDAO;
import com.femsa.mkt.pojos.MktAdmin_CatProyecto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author TMXIDSJPINAM
 */
@FacesConverter("proyectoConverter")
public class ProyectoConverter implements Converter {

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
                MktAdmin_CatProyectoDAO proyectoDAO = new MktAdmin_CatProyectoDAO();
                MktAdmin_CatProyecto proyecto = proyectoDAO.getProyecto(new Integer(value));
                if (proyecto != null) {
                    return proyecto;
                }
            } catch (NumberFormatException e) {
                return null;
            }
            return null;
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
            if (value instanceof MktAdmin_CatProyecto) {
                MktAdmin_CatProyecto proyecto = (MktAdmin_CatProyecto) value;
                return proyecto.getIdProyecto() + "";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
