/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author TMXIDSJPINAM
 */
public class DetalleCarga {
   
    private BigDecimal NUMREGISTROS;    
    private String CLAVEESTADO;
    private String ESTADO;

    public DetalleCarga() {
    }

    public DetalleCarga(BigDecimal NUMREGISTROS, String CLAVEESTADO, String ESTADO) {
        this.NUMREGISTROS = NUMREGISTROS;
        this.CLAVEESTADO = CLAVEESTADO;
        this.ESTADO = ESTADO;
    }

    public BigDecimal getNumRegistros() {
        return NUMREGISTROS;
    }

    public void setNumRegistros(BigDecimal NUMREGISTROS) {
        this.NUMREGISTROS = NUMREGISTROS;
    }

    public String getClaveEstado() {
        return CLAVEESTADO;
    }

    public void setClaveEstado(String CLAVEESTADO) {
        this.CLAVEESTADO = CLAVEESTADO;
    }

    public String getEstado() {
        return ESTADO;
    }

    public void setEstado(String estado) {
        this.ESTADO = estado;
    }

    @Override
    public String toString() {
        return "DetalleCarga{" + "NUMREGISTROS=" + NUMREGISTROS + ", CLAVEESTADO=" + CLAVEESTADO + ", ESTADO=" + ESTADO + '}';
    }
        
}
