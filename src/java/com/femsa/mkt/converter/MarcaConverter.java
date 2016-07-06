package com.femsa.mkt.converter;

import com.femsa.mkt.dao.MktCobhDimMarcaDao;
import com.femsa.mkt.dao.MktCobhDimSegmentoDao;
import com.femsa.mkt.pojos.MktCobhDimMarca;
import com.femsa.mkt.pojos.MktCobhDimSegmento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Clase que permite la utilización de la entidad RvvdCatCategoria, la cual mapea la tabla RVVD_CAT_CATEGORIA, en una
 * interfaz gráfica como elemento de componentes que requieran de un convertidor
 * como por ejemplo: una lista seleccionable.
 *
 * @author TMXIDSJPINAM
 */
@FacesConverter("marcaConverter")
public class MarcaConverter implements Converter {

    /**
     * Permite la conversión de un elemento seleccionado en una interfaz gráfica 
     * del tipo texto al tipo objeto correspondiente.
     * @param context Contexto JSF actual
     * @param component Componente que origina la petición de conversión
     * @param value Valor a ser convertido a objeto.
     * @return Objeto obtenido, si no existe se regresa nulo
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        System.out.println("value:"+value);
        if (value != null && value.trim().length() > 0) {
            try {
                MktCobhDimMarcaDao marcaDao = new MktCobhDimMarcaDao();
                
                MktCobhDimMarca marca = marcaDao.getMarca(new Integer(value));
                if (marca != null) {
                    return marca;
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
     * Permite obtener el equivalente en texto del objeto seleccionado en una interfaz
     * gráfica.
     * @param context Contexto JSF actual
     * @param component Componente que origina la petición de conversión
     * @param value Objeto seleccionado por el componente a ser convertido a texto
     * @return Valor tipo texto obtenido del objeto seleccionado, si el objeto value no es correcto se regresa nulo.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            if (value instanceof MktCobhDimMarca) {
                MktCobhDimMarca segmento = (MktCobhDimMarca) value;
                return segmento.getPkMarca()+ "";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
