/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_TMP_PS_FILTRO_ID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktTmpPsFiltroId.findAll", query = "SELECT m FROM MktTmpPsFiltroId m")})
public class MktTmpPsFiltroId implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_PS_FILTRO_ID")
    @SequenceGenerator(name = "MKT_SEQ_PS_FILTRO_ID", sequenceName = "MKT_SEQ_PS_FILTRO_ID", allocationSize = 1)
    @Column(name = "ID_PS_FILTRO_ID")
    private Integer idPsFiltroId;
    @Column(name = "CLAVE_PROMOCION")
    private String clavePromocion;
    @Column(name = "NOMBRE_PROMOCION")
    private String nombrePromocion;
    @Column(name = "TIPO_ENTRADA")
    private String tipoEntrada;
    @Column(name = "ID")
    private String id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CF")
    private Double cf;
    @Column(name = "PIEZAS")
    private Double piezas;
    @Column(name = "FECHA_INICIO")
    private String fechaInicio;
    @Column(name = "FECHA_FIN")
    private String fechaFin;
    @Column(name = "UO")
    private String uo;

    public MktTmpPsFiltroId() {
    }

    public MktTmpPsFiltroId(Integer idPsFiltroId) {
        this.idPsFiltroId = idPsFiltroId;
    }

    public String getClavePromocion() {
        return clavePromocion;
    }

    public void setClavePromocion(String clavePromocion) {
        this.clavePromocion = clavePromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCf() {
        return cf;
    }

    public void setCf(Double cf) {
        this.cf = cf;
    }

    public Double getPiezas() {
        return piezas;
    }

    public void setPiezas(Double piezas) {
        this.piezas = piezas;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUo() {
        return uo;
    }

    public void setUo(String uo) {
        this.uo = uo;
    }

    public Integer getIdPsFiltroId() {
        return idPsFiltroId;
    }

    public void setIdPsFiltroId(Integer idPsFiltroId) {
        this.idPsFiltroId = idPsFiltroId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPsFiltroId != null ? idPsFiltroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktTmpPsFiltroId)) {
            return false;
        }
        MktTmpPsFiltroId other = (MktTmpPsFiltroId) object;
        if ((this.idPsFiltroId == null && other.idPsFiltroId != null) || (this.idPsFiltroId != null && !this.idPsFiltroId.equals(other.idPsFiltroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktTmpPsFiltroId[ idPsFiltroId=" + idPsFiltroId + " ]";
    }

}
