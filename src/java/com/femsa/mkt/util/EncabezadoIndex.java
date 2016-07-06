/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.util;

import com.femsa.mkt.pojos.MktCobhRelarchivotabla;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author TMXIDSJPINAM
 */
public class EncabezadoIndex implements Comparable<EncabezadoIndex>{

    private String nombreEncabezado;
    private int index;
    private List<Object> catalogo;
    private Class claseCatalogo;
    private MktCobhRelarchivotabla campoTabla;

    public EncabezadoIndex() {
    }

    public EncabezadoIndex(String nombreEncabezado, int index, List<Object> catalogo,Class claseCatalogo,MktCobhRelarchivotabla campoTabla) {
        this.nombreEncabezado = nombreEncabezado;
        this.index = index;
        this.catalogo = catalogo;
        this.claseCatalogo = claseCatalogo;
        this.campoTabla = campoTabla;
    }

    public MktCobhRelarchivotabla getCampoTabla() {
        return campoTabla;
    }

    public void setCampoTabla(MktCobhRelarchivotabla campoTabla) {
        this.campoTabla = campoTabla;
    }

    public Class getClaseCatalogo() {
        return claseCatalogo;
    }

    public void setClaseCatalogo(Class claseCatalogo) {
        this.claseCatalogo = claseCatalogo;
    }

    public List<Object> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<Object> catalogo) {
        this.catalogo = catalogo;
    }

    public String getNombreEncabezado() {
        return nombreEncabezado;
    }

    public void setNombreEncabezado(String nombreEncabezado) {
        this.nombreEncabezado = nombreEncabezado;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Encabezado{" + "nombreEncabezado=" + nombreEncabezado + ", index=" + index + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.nombreEncabezado != null ? this.nombreEncabezado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EncabezadoIndex other = (EncabezadoIndex) obj;
        if (this.index != other.index) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(EncabezadoIndex o) {
        Comparator com = new Comparator<EncabezadoIndex>(){
            @Override
            public int compare(EncabezadoIndex o1, EncabezadoIndex o2) {
                return o1.getCampoTabla().getOrdenEval().compareTo(o2.getCampoTabla().getOrdenEval());
            }
        };
        return com.compare(this, o);
    }

}
