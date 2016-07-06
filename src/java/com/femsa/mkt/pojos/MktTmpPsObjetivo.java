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
@Table(name = "MKT_TMP_PS_OBJETIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktTmpPsObjetivo.findAll", query = "SELECT m FROM MktTmpPsObjetivo m")})
public class MktTmpPsObjetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_PS_OBJETIVO")
    @SequenceGenerator(name = "MKT_SEQ_PS_OBJETIVO", sequenceName = "MKT_SEQ_PS_OBJETIVO", allocationSize = 1)
    @Column(name = "ID_PS_OBJETIVO")
    private Integer idPsObjetivo;
    @Column(name = "CLAVE_PROMOCION")
    private String clavePromocion;
    @Column(name = "NOMBRE_PROMOCION")
    private String nombrePromocion;
    @Column(name = "CORTE")
    private String corte;
    @Column(name = "CLAVE_CORTE")
    private String claveCorte;
    @Column(name = "TM_CATEGORIA")
    private String tmCategoria;
    @Column(name = "TM_RETORNABILIDAD")
    private String tmRetornabilidad;
    @Column(name = "TM_EMPAQUE")
    private String tmEmpaque;
    @Column(name = "TM_TAMANO")
    private String tmTamano;
    @Column(name = "TM_TPV")
    private String tmTpv;
    @Column(name = "FECHA_INICIO")
    private String fechaInicio;
    @Column(name = "FECHA_FIN")
    private String fechaFin;
    @Column(name = "SKU_VENTA")
    private String skuVenta;
    @Column(name = "CANAL")
    private String canal;
    @Column(name = "CF")
    private String cf;
    @Column(name = "CU")
    private String cu;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CFC")
    private Double cfc;
    @Column(name = "PIEZAS")
    private Double piezas;
    @Column(name = "C_ORO_TRA")
    private Double cOroTra;
    @Column(name = "C_PLATA_TRA")
    private Double cPlataTra;
    @Column(name = "C_BRONCE_TRA")
    private Double cBronceTra;
    @Column(name = "C_TRA")
    private Double cTra;
    @Column(name = "COB_ORO_TRA")
    private Double cobOroTra;
    @Column(name = "COB_PLATA_TRA")
    private Double cobPlataTra;
    @Column(name = "COB_BRONCE_TRA")
    private Double cobBronceTra;
    @Column(name = "COB_TRA")
    private Double cobTra;
    @Column(name = "C_ORO_ESP")
    private Double cOroEsp;
    @Column(name = "C_PLATA_ESP")
    private Double cPlataEsp;
    @Column(name = "C_BRONCE_ESP")
    private Double cBronceEsp;
    @Column(name = "C_ESP")
    private Double cEsp;
    @Column(name = "COB_ORO_ESP")
    private Double cobOroEsp;
    @Column(name = "COB_PLATA_ESP")
    private Double cobPlataEsp;
    @Column(name = "COB_BRONCE_ESP")
    private Double cobBronceEsp;
    @Column(name = "COB_ESP")
    private Double cobEsp;
    @Column(name = "REDENCION")
    private Double redencion;
    @Column(name = "DESCUENTOS")
    private Double descuentos;
    @Column(name = "GEN_1")
    private Double gen1;
    @Column(name = "GEN_2")
    private Double gen2;
    @Column(name = "GEN_3")
    private Double gen3;
    @Column(name = "GEN_4")
    private Double gen4;
    @Column(name = "GEN_5")
    private Double gen5;
    @Column(name = "GEN_6")
    private Double gen6;
    @Column(name = "GEN_7")
    private Double gen7;
    @Column(name = "GEN_8")
    private Double gen8;
    @Column(name = "GEN_9")
    private Double gen9;
    @Column(name = "GEN_10")
    private Double gen10;
    

    public MktTmpPsObjetivo() {
    }

    public MktTmpPsObjetivo(Integer idPsObjetivo) {
        this.idPsObjetivo = idPsObjetivo;
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

    public String getTmCategoria() {
        return tmCategoria;
    }

    public void setTmCategoria(String tmCategoria) {
        this.tmCategoria = tmCategoria;
    }

    public String getTmRetornabilidad() {
        return tmRetornabilidad;
    }

    public void setTmRetornabilidad(String tmRetornabilidad) {
        this.tmRetornabilidad = tmRetornabilidad;
    }

    public String getTmEmpaque() {
        return tmEmpaque;
    }

    public void setTmEmpaque(String tmEmpaque) {
        this.tmEmpaque = tmEmpaque;
    }

    public String getTmTamano() {
        return tmTamano;
    }

    public void setTmTamano(String tmTamano) {
        this.tmTamano = tmTamano;
    }

    public String getTmTpv() {
        return tmTpv;
    }

    public void setTmTpv(String tmTpv) {
        this.tmTpv = tmTpv;
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

    public String getSkuVenta() {
        return skuVenta;
    }

    public void setSkuVenta(String skuVenta) {
        this.skuVenta = skuVenta;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getCu() {
        return cu;
    }

    public void setCu(String cu) {
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

    public Double getCOroTra() {
        return cOroTra;
    }

    public void setCOroTra(Double cOroTra) {
        this.cOroTra = cOroTra;
    }

    public Double getCPlataTra() {
        return cPlataTra;
    }

    public void setCPlataTra(Double cPlataTra) {
        this.cPlataTra = cPlataTra;
    }

    public Double getCBronceTra() {
        return cBronceTra;
    }

    public void setCBronceTra(Double cBronceTra) {
        this.cBronceTra = cBronceTra;
    }

    public Double getCTra() {
        return cTra;
    }

    public void setCTra(Double cTra) {
        this.cTra = cTra;
    }

    public Double getCobOroTra() {
        return cobOroTra;
    }

    public void setCobOroTra(Double cobOroTra) {
        this.cobOroTra = cobOroTra;
    }

    public Double getCobPlataTra() {
        return cobPlataTra;
    }

    public void setCobPlataTra(Double cobPlataTra) {
        this.cobPlataTra = cobPlataTra;
    }

    public Double getCobBronceTra() {
        return cobBronceTra;
    }

    public void setCobBronceTra(Double cobBronceTra) {
        this.cobBronceTra = cobBronceTra;
    }

    public Double getCobTra() {
        return cobTra;
    }

    public void setCobTra(Double cobTra) {
        this.cobTra = cobTra;
    }

    public Double getCOroEsp() {
        return cOroEsp;
    }

    public void setCOroEsp(Double cOroEsp) {
        this.cOroEsp = cOroEsp;
    }

    public Double getCPlataEsp() {
        return cPlataEsp;
    }

    public void setCPlataEsp(Double cPlataEsp) {
        this.cPlataEsp = cPlataEsp;
    }

    public Double getCBronceEsp() {
        return cBronceEsp;
    }

    public void setCBronceEsp(Double cBronceEsp) {
        this.cBronceEsp = cBronceEsp;
    }

    public Double getCEsp() {
        return cEsp;
    }

    public void setCEsp(Double cEsp) {
        this.cEsp = cEsp;
    }

    public Double getCobOroEsp() {
        return cobOroEsp;
    }

    public void setCobOroEsp(Double cobOroEsp) {
        this.cobOroEsp = cobOroEsp;
    }

    public Double getCobPlataEsp() {
        return cobPlataEsp;
    }

    public void setCobPlataEsp(Double cobPlataEsp) {
        this.cobPlataEsp = cobPlataEsp;
    }

    public Double getCobBronceEsp() {
        return cobBronceEsp;
    }

    public void setCobBronceEsp(Double cobBronceEsp) {
        this.cobBronceEsp = cobBronceEsp;
    }

    public Double getCobEsp() {
        return cobEsp;
    }

    public void setCobEsp(Double cobEsp) {
        this.cobEsp = cobEsp;
    }

    public Double getRedencion() {
        return redencion;
    }

    public void setRedencion(Double redencion) {
        this.redencion = redencion;
    }

    public Double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(Double descuentos) {
        this.descuentos = descuentos;
    }

    public Double getGen1() {
        return gen1;
    }

    public void setGen1(Double gen1) {
        this.gen1 = gen1;
    }

    public Double getGen2() {
        return gen2;
    }

    public void setGen2(Double gen2) {
        this.gen2 = gen2;
    }

    public Double getGen3() {
        return gen3;
    }

    public void setGen3(Double gen3) {
        this.gen3 = gen3;
    }

    public Double getGen4() {
        return gen4;
    }

    public void setGen4(Double gen4) {
        this.gen4 = gen4;
    }

    public Double getGen5() {
        return gen5;
    }

    public void setGen5(Double gen5) {
        this.gen5 = gen5;
    }

    public Double getGen6() {
        return gen6;
    }

    public void setGen6(Double gen6) {
        this.gen6 = gen6;
    }

    public Double getGen7() {
        return gen7;
    }

    public void setGen7(Double gen7) {
        this.gen7 = gen7;
    }

    public Double getGen8() {
        return gen8;
    }

    public void setGen8(Double gen8) {
        this.gen8 = gen8;
    }

    public Double getGen9() {
        return gen9;
    }

    public void setGen9(Double gen9) {
        this.gen9 = gen9;
    }

    public Double getGen10() {
        return gen10;
    }

    public void setGen10(Double gen10) {
        this.gen10 = gen10;
    }

    public Integer getIdPsObjetivo() {
        return idPsObjetivo;
    }

    public void setIdPsObjetivo(Integer idPsObjetivo) {
        this.idPsObjetivo = idPsObjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPsObjetivo != null ? idPsObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktTmpPsObjetivo)) {
            return false;
        }
        MktTmpPsObjetivo other = (MktTmpPsObjetivo) object;
        if ((this.idPsObjetivo == null && other.idPsObjetivo != null) || (this.idPsObjetivo != null && !this.idPsObjetivo.equals(other.idPsObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktTmpPsObjetivo[ idPsObjetivo=" + idPsObjetivo + " ]";
    }
    
}
