package com.femsa.mkt.converter;

import com.femsa.mkt.pojos.MktAdmin_CatPais;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;

/**
 *
 * @author TMXIDSJPINAM
 */
@FacesConverter("countryConverter")
public class CountryConverter implements Converter {

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
                MktAdmin_CatPais countryT = new MktAdmin_CatPais();                
                countryT.setPkPais(new Integer(value));                
                ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                List<MktAdmin_CatPais> countries = (List<MktAdmin_CatPais>) sc.getAttribute("countries_catalog");                
                for (MktAdmin_CatPais country : countries) {
                    if (country.equals(countryT)) {
                        return country;
                    }
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
            if (value instanceof MktAdmin_CatPais) {
                MktAdmin_CatPais country = (MktAdmin_CatPais) value;
                return country.getPkPais() + "";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
