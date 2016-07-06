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
@Table(name = "MKT_TMP_LZ_OBJETIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktTmpLzObjetivo.findAll", query = "SELECT m FROM MktTmpLzObjetivo m")})
public class MktTmpLzObjetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_LZ_OBJETIVO")
    @SequenceGenerator(name = "MKT_SEQ_LZ_OBJETIVO", sequenceName = "MKT_SEQ_LZ_OBJETIVO", allocationSize = 1)
    @Column(name = "ID_LZ_OBJETIVO")
    private Integer idLzObjetivo;
    @Column(name = "CONSECUTIVO")
    private String consecutivo;
    @Column(name = "CLAVE_LANZAMIENTO")
    private String claveLanzamiento;
    @Column(name = "NOMBRE_LANZAMIENTO")
    private String nombreLanzamiento;
    @Column(name = "CORTE")
    private String corte;
    @Column(name = "CLAVE_CORTE")
    private String claveCorte;
    @Column(name = "FECHA_INI_TOTAL")
    private String fechaIniTotal;
    @Column(name = "FECHA_FIN_TOTAL")
    private String fechaFinTotal;
    @Column(name = "FEHCA_INI_REAL")
    private String fehcaIniReal;
    @Column(name = "FECHA_FIN_REAL")
    private String fechaFinReal;
    @Column(name = "TIPO_ENTRADA")
    private String tipoEntrada;
    @Column(name = "ID_ENTRADA")
    private String idEntrada;
    @Column(name = "MES")
    private String mes;
    @Column(name = "CANAL")
    private String canal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CF")
    private Double cf;
    @Column(name = "CU")
    private Double cu;
    @Column(name = "CFC")
    private Double cfc;
    @Column(name = "PIEZAS")
    private Double piezas;
    @Column(name = "CLIENTES")
    private Double clientes;
    @Column(name = "COBERUTRA_TRADICIONAL")
    private Double coberutraTradicional;
    @Column(name = "COBERTURA_ESPECIALIZADA")
    private Double coberturaEspecializada;
    @Column(name = "COBERTURA_ORO_TRA")
    private Double coberturaOroTra;
    @Column(name = "COBERTURA_PLATA_TRA")
    private Double coberturaPlataTra;
    @Column(name = "COBERTURA_BRONCE_TRA")
    private Double coberturaBronceTra;
    @Column(name = "COBERTURA_ORO_ESP")
    private Double coberturaOroEsp;
    @Column(name = "COBERTURA_PLATA_ESP")
    private Double coberturaPlataEsp;
    @Column(name = "COBERTURA_BRONCE_ESP")
    private Double coberturaBronceEsp;
    @Column(name = "RECOMPRA_TRA")
    private Double recompraTra;
    @Column(name = "RECOMPRA_ESP")
    private Double recompraEsp;
    @Column(name = "GENERICO_1")
    private Double generico1;
    @Column(name = "GENERICO_2")
    private Double generico2;
    @Column(name = "GENERICO_3")
    private Double generico3;    

    public MktTmpLzObjetivo() {
    }

    public MktTmpLzObjetivo(Integer idLzObjetivo) {
        this.idLzObjetivo = idLzObjetivo;
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

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }

    public String getClaveCorte() {
        return claveCorte;
    }

    public void setClaveCorte(String claveCorte) {
        this.claveCorte = claveCorte;
    }

    public String getFechaIniTotal() {
        return fechaIniTotal;
    }

    public void setFechaIniTotal(String fechaIniTotal) {
        this.fechaIniTotal = fechaIniTotal;
    }

    public String getFechaFinTotal() {
        return fechaFinTotal;
    }

    public void setFechaFinTotal(String fechaFinTotal) {
        this.fechaFinTotal = fechaFinTotal;
    }

    public String getFehcaIniReal() {
        return fehcaIniReal;
    }

    public void setFehcaIniReal(String fehcaIniReal) {
        this.fehcaIniReal = fehcaIniReal;
    }

    public String getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(String fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Double getCf() {
        return cf;
    }

    public void setCf(Double cf) {
        this.cf = cf;
    }

    public Double getCu() {
        return cu;
    }

    public void setCu(Double cu) {
        this.cu = cu;
    }

    public Double getCfc() {
        return cfc;
    }

    public void setCfc(Double cfc) {
        this.cfc = cfc;
    }

    public Double getPiezas() {
        return piezas;
    }

    public void setPiezas(Double piezas) {
        this.piezas = piezas;
    }

    public Double getClientes() {
        return clientes;
    }

    public void setClientes(Double clientes) {
        this.clientes = clientes;
    }

    public Double getCoberutraTradicional() {
        return coberutraTradicional;
    }

    public void setCoberutraTradicional(Double coberutraTradicional) {
        this.coberutraTradicional = coberutraTradicional;
    }

    public Double getCoberturaEspecializada() {
        return coberturaEspecializada;
    }

    public void setCoberturaEspecializada(Double coberturaEspecializada) {
        this.coberturaEspecializada = coberturaEspecializada;
    }

    public Double getCoberturaOroTra() {
        return coberturaOroTra;
    }

    public void setCoberturaOroTra(Double coberturaOroTra) {
        this.coberturaOroTra = coberturaOroTra;
    }

    public Double getCoberturaPlataTra() {
        return coberturaPlataTra;
    }

    public void setCoberturaPlataTra(Double coberturaPlataTra) {
        this.coberturaPlataTra = coberturaPlataTra;
    }

    public Double getCoberturaBronceTra() {
        return coberturaBronceTra;
    }

    public void setCoberturaBronceTra(Double coberturaBronceTra) {
        this.coberturaBronceTra = coberturaBronceTra;
    }

    public Double getCoberturaOroEsp() {
        return coberturaOroEsp;
    }

    public void setCoberturaOroEsp(Double coberturaOroEsp) {
        this.coberturaOroEsp = coberturaOroEsp;
    }

    public Double getCoberturaPlataEsp() {
        return coberturaPlataEsp;
    }

    public void setCoberturaPlataEsp(Double coberturaPlataEsp) {
        this.coberturaPlataEsp = coberturaPlataEsp;
    }

    public Double getCoberturaBronceEsp() {
        return coberturaBronceEsp;
    }

    public void setCoberturaBronceEsp(Double coberturaBronceEsp) {
        this.coberturaBronceEsp = coberturaBronceEsp;
    }

    public Double getRecompraTra() {
        return recompraTra;
    }

    public void setRecompraTra(Double recompraTra) {
        this.recompraTra = recompraTra;
    }

    public Double getRecompraEsp() {
        return recompraEsp;
    }

    public void setRecompraEsp(Double recompraEsp) {
        this.recompraEsp = recompraEsp;
    }

    public Double getGenerico1() {
        return generico1;
    }

    public void setGenerico1(Double generico1) {
        this.generico1 = generico1;
    }

    public Double getGenerico2() {
        return generico2;
    }

    public void setGenerico2(Double generico2) {
        this.generico2 = generico2;
    }

    public Double getGenerico3() {
        return generico3;
    }

    public void setGenerico3(Double generico3) {
        this.generico3 = generico3;
    }

    public Integer getIdLzObjetivo() {
        return idLzObjetivo;
    }

    public void setIdLzObjetivo(Integer idLzObjetivo) {
        this.idLzObjetivo = idLzObjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLzObjetivo != null ? idLzObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktTmpLzObjetivo)) {
            return false;
        }
        MktTmpLzObjetivo other = (MktTmpLzObjetivo) object;
        if ((this.idLzObjetivo == null && other.idLzObjetivo != null) || (this.idLzObjetivo != null && !this.idLzObjetivo.equals(other.idLzObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktTmpLzObjetivo[ idLzObjetivo=" + idLzObjetivo + " ]";
    }
    
}
