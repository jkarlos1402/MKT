package com.femsa.mkt.converter;

import com.femsa.mkt.dao.MktCobhDimEstadoDao;
import com.femsa.mkt.pojos.MktCobhDimEstado;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter {

    /**
     * Permite la conversión de un elemento seleccionado en una interfaz gráfica
     * del tipo texto al tipo objeto correspondiente.
     *
     * @param context Contexto JSF actual
     * @param component Componente que origina la petición de conversión
     * @param value Valor a ser convertido a objeto.
     * @return Objeto obtenido, si no existe se regresa nulo
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {        
        if (value != null && value.trim().length() > 0) {
            try {
                MktCobhDimEstadoDao estadoDao = new MktCobhDimEstadoDao();
                MktCobhDimEstado estado = estadoDao.getEstado(new Integer(value));               
                if (estado != null) {
//                    System.out.println("entro a correcto");
                    return estado;
                }
            } catch (NumberFormatException e) {
//                System.out.println("regresa nulo 1");
                return null;
            }
//            System.out.println("regresa nulo 2");
            return null;
        } else {
//            System.out.println("regresa nulo 3");
            return null;
        }
    }

    /**
     * Permite obtener el equivalente en texto del objeto seleccionado en una
     * interfaz gráfica.
     *
     * @param context Contexto JSF actual
     * @param component Componente que origina la petición de conversión
     * @param value Objeto seleccionado por el componente a ser convertido a
     * texto
     * @return Valor tipo texto obtenido del objeto seleccionado, si el objeto
     * value no es correcto se regresa nulo.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            if (value instanceof MktCobhDimEstado) {
                MktCobhDimEstado estado = (MktCobhDimEstado) value;
                return estado.getPkEstado() + "";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
