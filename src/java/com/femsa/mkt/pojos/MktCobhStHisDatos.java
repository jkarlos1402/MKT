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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author TMXIDSCPEREZ
 */
@Entity
@Table(name = "MKT_COBH_ST_HIS_DATOS")
@NamedQueries({
    @NamedQuery(name = "MktCobhStHisDatos.findAll", query = "SELECT m FROM MktCobhStHisDatos m")})
public class MktCobhStHisDatos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDDATOS")
    private BigDecimal iddatos;
    @Column(name = "FECHA")
    private String fecha;
    @Column(name = "INCLUIR")
    private String incluir;
    @Column(name = "COBERTURA_OBJETIVO")
    private BigDecimal coberturaObjetivo;
    @Column(name = "CLIENTES_OBJETIVO")
    private BigDecimal clientesObjetivo;
    @Column(name = "CLIENTES_ACTIVOS")
    private BigDecimal clientesActivos;
    @Column(name = "CLIENTES_VENTA")
    private BigDecimal clientesVenta;
    @JoinColumn(name = "IDCANAL", referencedColumnName = "PK_CANAL")
    @ManyToOne
    private MktCobhDimCanal idcanal;
    @JoinColumn(name = "IDEMPAQUE", referencedColumnName = "PK_EMPAQUE")
    @ManyToOne
    private MktCobhDimEmpaque idempaque;
    @JoinColumn(name = "IDESTADO", referencedColumnName = "PK_ESTADO")
    @ManyToOne
    private MktCobhDimEstado idestado;
    @JoinColumn(name = "IDGEC", referencedColumnName = "PK_GEC")
    @ManyToOne
    private MktCobhDimGec idgec;
    @JoinColumn(name = "IDMARCA", referencedColumnName = "PK_MARCA")
    @ManyToOne
    private MktCobhDimMarca idmarca;
    @JoinColumn(name = "IDREGION", referencedColumnName = "PK_REGION")
    @ManyToOne
    private MktCobhDimRegion idregion;
    @JoinColumn(name = "IDSEGMENTO", referencedColumnName = "PK_SEGMENTO")
    @ManyToOne
    private MktCobhDimSegmento idsegmento;
    @JoinColumn(name = "IDUO", referencedColumnName = "PK_UO")
    @ManyToOne
    private MktCobhDimUo iduo;

    public MktCobhStHisDatos() {
    }

    public MktCobhStHisDatos(BigDecimal iddatos) {
        this.iddatos = iddatos;
    }

    public BigDecimal getIddatos() {
        return iddatos;
    }

    public void setIddatos(BigDecimal iddatos) {
        this.iddatos = iddatos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIncluir() {
        return incluir;
    }

    public void setIncluir(String incluir) {
        this.incluir = incluir;
    }

    public BigDecimal getCoberturaObjetivo() {
        return coberturaObjetivo;
    }

    public void setCoberturaObjetivo(BigDecimal coberturaObjetivo) {
        this.coberturaObjetivo = coberturaObjetivo;
    }

    public BigDecimal getClientesObjetivo() {
        return clientesObjetivo;
    }

    public void setClientesObjetivo(BigDecimal clientesObjetivo) {
        this.clientesObjetivo = clientesObjetivo;
    }

    public BigDecimal getClientesActivos() {
        return clientesActivos;
    }

    public void setClientesActivos(BigDecimal clientesActivos) {
        this.clientesActivos = clientesActivos;
    }

    public BigDecimal getClientesVenta() {
        return clientesVenta;
    }

    public void setClientesVenta(BigDecimal clientesVenta) {
        this.clientesVenta = clientesVenta;
    }

    public MktCobhDimCanal getIdcanal() {
        return idcanal;
    }

    public void setIdcanal(MktCobhDimCanal idcanal) {
        this.idcanal = idcanal;
    }

    public MktCobhDimEmpaque getIdempaque() {
        return idempaque;
    }

    public void setIdempaque(MktCobhDimEmpaque idempaque) {
        this.idempaque = idempaque;
    }

    public MktCobhDimEstado getIdestado() {
        return idestado;
    }

    public void setIdestado(MktCobhDimEstado idestado) {
        this.idestado = idestado;
    }

    public MktCobhDimGec getIdgec() {
        return idgec;
    }

    public void setIdgec(MktCobhDimGec idgec) {
        this.idgec = idgec;
    }

    public MktCobhDimMarca getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(MktCobhDimMarca idmarca) {
        this.idmarca = idmarca;
    }

    public MktCobhDimRegion getIdregion() {
        return idregion;
    }

    public void setIdregion(MktCobhDimRegion idregion) {
        this.idregion = idregion;
    }

    public MktCobhDimSegmento getIdsegmento() {
        return idsegmento;
    }

    public void setIdsegmento(MktCobhDimSegmento idsegmento) {
        this.idsegmento = idsegmento;
    }

    public MktCobhDimUo getIduo() {
        return iduo;
    }

    public void setIduo(MktCobhDimUo iduo) {
        this.iduo = iduo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddatos != null ? iddatos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktCobhStHisDatos)) {
            return false;
        }
        MktCobhStHisDatos other = (MktCobhStHisDatos) object;
        if ((this.iddatos == null && other.iddatos != null) || (this.iddatos != null && !this.iddatos.equals(other.iddatos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktCobhStHisDatos[ iddatos=" + iddatos + " ]";
    }
    
}
