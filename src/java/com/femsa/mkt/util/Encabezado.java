/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.util;

/**
 *
 * @author TMXIDSJPINAM
 */
public class Encabezado {

    private String nombreEncabezado;
    private int index;

    public Encabezado() {
    }

    public Encabezado(String nombreEncabezado, int index) {
        this.nombreEncabezado = nombreEncabezado;
        this.index = index;
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
        final Encabezado other = (Encabezado) obj;
        if ((this.nombreEncabezado == null) ? (other.nombreEncabezado != null) : !this.nombreEncabezado.equals(other.nombreEncabezado)) {
            return false;
        }
        return true;
    }

}
