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
@Table(name = "MKT_TMP_LZ_FILTRO_ID_CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktTmpLzFiltroIdCliente.findAll", query = "SELECT m FROM MktTmpLzFiltroIdCliente m")})
public class MktTmpLzFiltroIdCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_LZ_FILTRO_ID_CLIENTE")
    @SequenceGenerator(name = "MKT_SEQ_LZ_FILTRO_ID_CLIENTE", sequenceName = "MKT_SEQ_LZ_FILTRO_ID_CLIENTE", allocationSize = 1)
    @Column(name = "ID_LZ_FILTRO_ID_CLIENTE")
    private Integer idLzFiltroIdCliente;
    @Column(name = "CONSECUTIVO")
    private String consecutivo;
    @Column(name = "CLAVE_LANZAMIENTO")
    private String claveLanzamiento;
    @Column(name = "NOMBRE_LANZAMIENTO")
    private String nombreLanzamiento;
    @Column(name = "FECHA_INICIO")
    private String fechaInicio;
    @Column(name = "FECHA_FIN")
    private String fechaFin;
    @Column(name = "TIPO_ENTRADA")
    private String tipoEntrada;
    @Column(name = "ID_ENTRADA")
    private String idEntrada;
    @Column(name = "CLIENTES")
    private String clientes;
    @Column(name = "GRUPO_CANAL")
    private String grupoCanal;
    @Column(name = "TIPO_PREVENTA")
    private String tipoPreventa;
    @Column(name = "TIPO_CANAL")
    private String tipoCanal;
    @Column(name = "DESCRIPCION_COMBO")
    private String descripcionCombo;
    @Column(name = "OBJETIVO")
    private Long objetivo;

    public MktTmpLzFiltroIdCliente() {
    }

    public MktTmpLzFiltroIdCliente(Integer idLzFiltroIdCliente) {
        this.idLzFiltroIdCliente = idLzFiltroIdCliente;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getClaveLanzamiento() {
        return claveLanzamiento;
    }

    public void setClaveLanzamiento(String claveLanzamiento) {
        this.claveLanzamiento = claveLanzamiento;
    }

    public String getNombreLanzamiento() {
        return nombreLanzamiento;
    }

    public void setNombreLanzamiento(String nombreLanzamiento) {
        this.nombreLanzamiento = nombreLanzamiento;
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

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(String idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getClientes() {
        return clientes;
    }

    public void setClientes(String clientes) {
        this.clientes = clientes;
    }

    public String getGrupoCanal() {
        return grupoCanal;
    }

    public void setGrupoCanal(String grupoCanal) {
        this.grupoCanal = grupoCanal;
    }

    public String getTipoPreventa() {
        return tipoPreventa;
    }

    public void setTipoPreventa(String tipoPreventa) {
        this.tipoPreventa = tipoPreventa;
    }

    public String getTipoCanal() {
        return tipoCanal;
    }

    public void setTipoCanal(String tipoCanal) {
        this.tipoCanal = tipoCanal;
    }

    public String getDescripcionCombo() {
        return descripcionCombo;
    }

    public void setDescripcionCombo(String descripcionCombo) {
        this.descripcionCombo = descripcionCombo;
    }

    public Long getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Long objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getIdLzFiltroIdCliente() {
        return idLzFiltroIdCliente;
    }

    public void setIdLzFiltroIdCliente(Integer idLzFiltroIdCliente) {
        this.idLzFiltroIdCliente = idLzFiltroIdCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLzFiltroIdCliente != null ? idLzFiltroIdCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktTmpLzFiltroIdCliente)) {
            return false;
        }
        MktTmpLzFiltroIdCliente other = (MktTmpLzFiltroIdCliente) object;
        if ((this.idLzFiltroIdCliente == null && other.idLzFiltroIdCliente != null) || (this.idLzFiltroIdCliente != null && !this.idLzFiltroIdCliente.equals(other.idLzFiltroIdCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktTmpLzFiltroIdCliente[ idLzFiltroIdCliente=" + idLzFiltroIdCliente + " ]";
    }

}
