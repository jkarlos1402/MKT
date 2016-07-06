/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author TMXIDSCPEREZ
 */
@Entity
@Table(name = "MKT_COBH_TMP_HIS_DATOS")
public class MktCobhTmpHisDatos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "FECHA")
    private String fecha;
    
    @Column(name = "CANAL")
    private String canal;
    
    @Column(name = "ESTADO")
    private String estado;
    
    @Column(name = "REGION")
    private String region;
    
    @Column(name = "UO")
    private String uo;
    
    @Column(name = "GEC")
    private String gec;
    
    @Column(name = "SEGMENTO")
    private String segmento;
    
    @Column(name = "MARCA_CAT")
    private String marcaCat;
    
    @Column(name = "EMPAQUE")
    private String empaque;
    
    @Column(name = "INCLUIR")
    private String incluir;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COBERTURA_OBJETIVO")
    private double coberturaObjetivo;
    
    @Column(name = "CLIENTES_OBJETIVO")
    private double clientesObjetivo;
    
    @Column(name = "CLIENTES_ACTIVOS")
    private double clientesActivos;
    
    @Column(name = "CLIENTES_VENTA")
    private double clientesVenta;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_COBH_TMP_DATOS")
    @SequenceGenerator(name = "MKT_SEQ_COBH_TMP_DATOS", sequenceName = "MKT_SEQ_COBH_TMP_DATOS", allocationSize = 1)
    @Column(name = "PK_DATOS")
    private BigDecimal pkDatos;

    public MktCobhTmpHisDatos() {
    }

    public MktCobhTmpHisDatos(BigDecimal pkDatos) {
        this.pkDatos = pkDatos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUo() {
        return uo;
    }

    public void setUo(String uo) {
        this.uo = uo;
    }

    public String getGec() {
        return gec;
    }

    public void setGec(String gec) {
        this.gec = gec;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getMarcaCat() {
        return marcaCat;
    }

    public void setMarcaCat(String marcaCat) {
        this.marcaCat = marcaCat;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }

    public String getIncluir() {
        return incluir;
    }

    public void setIncluir(String incluir) {
        this.incluir = incluir;
    }

    public double getCoberturaObjetivo() {
        return coberturaObjetivo;
    }

    public void setCoberturaObjetivo(double coberturaObjetivo) {
        this.coberturaObjetivo = coberturaObjetivo;
    }

    public double getClientesObjetivo() {
        return clientesObjetivo;
    }

    public void setClientesObjetivo(double clientesObjetivo) {
        this.clientesObjetivo = clientesObjetivo;
    }

    public double getClientesActivos() {
        return clientesActivos;
    }

    public void setClientesActivos(double clientesActivos) {
        this.clientesActivos = clientesActivos;
    }

    public double getClientesVenta() {
        return clientesVenta;
    }

    public void setClientesVenta(double clientesVenta) {
        this.clientesVenta = clientesVenta;
    }

    public BigDecimal getPkDatos() {
        return pkDatos;
    }

    public void setPkDatos(BigDecimal pkDatos) {
        this.pkDatos = pkDatos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkDatos != null ? pkDatos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktCobhTmpHisDatos)) {
            return false;
        }
        MktCobhTmpHisDatos other = (MktCobhTmpHisDatos) object;
        if ((this.pkDatos == null && other.pkDatos != null) || (this.pkDatos != null && !this.pkDatos.equals(other.pkDatos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhTmpHisDatos{" + "fecha=" + fecha + ", canal=" + canal + ", estado=" + estado + ", region=" + region + ", uo=" + uo + ", gec=" + gec + ", segmento=" + segmento + ", marcaCat=" + marcaCat + ", empaque=" + empaque + ", incluir=" + incluir + ", coberturaObjetivo=" + coberturaObjetivo + ", clientesObjetivo=" + clientesObjetivo + ", clientesActivos=" + clientesActivos + ", clientesVenta=" + clientesVenta + ", pkDatos=" + pkDatos + '}';
    }

    
    
}
