/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.math.BigInteger;
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
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_COBH_DIM_AGRUPADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimAgrupador.findAll", query = "SELECT m FROM MktCobhDimAgrupador m")})
public class MktCobhDimAgrupador implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_AGRUPADOR")
    @SequenceGenerator(name = "MKT_COBH_SEQ_AGRUPADOR", sequenceName = "MKT_COBH_SEQ_AGRUPADOR", allocationSize = 1)
    @Column(name = "PK_IDAGRUPADOR")
    private Integer pkIdagrupador;
    @Column(name = "GV_IDAGRUPADOR_BW")
    private String gvIdagrupadorBw;
    @Column(name = "IDAGRUPADORTEXTO")
    private String idagrupadortexto;
    @Column(name = "FK_SEGMENTO_MKT")
    private BigInteger fkSegmentoMkt;
    @Column(name = "FK_GEC_MKT")
    private BigInteger fkGecMkt;
    @Column(name = "FK_MARCA_MKT")
    private BigInteger fkMarcaMkt;
    @Column(name = "FK_TAMANIO_MKT")
    private BigInteger fkTamanioMkt;
    @Column(name = "FK_EMPAQUE_MKT")
    private BigInteger fkEmpaqueMkt;
    @Column(name = "FK_CONCEPTO_MKT")
    private BigInteger fkConceptoMkt;
    @Column(name = "FK_AGRU1_MKT")
    private BigInteger fkAgru1Mkt;
    @Column(name = "FK_AGRU2_MKT")
    private BigInteger fkAgru2Mkt;
    @Column(name = "FK_AGRU3_MKT")
    private BigInteger fkAgru3Mkt;
    @Column(name = "SEGMENTO_MKT")
    private String segmentoMkt;
    @Column(name = "GEC_MKT")
    private String gecMkt;
    @Column(name = "MARCA_MKT")
    private String marcaMkt;
    @Column(name = "TAMANIO_MKT")
    private String tamanioMkt;
    @Column(name = "EMPAQUE_MKT")
    private String empaqueMkt;
    @Column(name = "CONCEPTO_MKT")
    private String conceptoMkt;
    @Column(name = "AGRU1_MKT")
    private String agru1Mkt;
    @Column(name = "AGRU2_MKT")
    private String agru2Mkt;
    @Column(name = "AGRU3_MKT")
    private String agru3Mkt;

    public MktCobhDimAgrupador() {
    }

    public MktCobhDimAgrupador(Integer pkIdagrupador) {
        this.pkIdagrupador = pkIdagrupador;
    }

    public Integer getPkIdagrupador() {
        return pkIdagrupador;
    }

    public MktCobhDimAgrupador(String idagrupadortexto) {
        this.idagrupadortexto = idagrupadortexto;
    }

    public void setPkIdagrupador(Integer pkIdagrupador) {
        this.pkIdagrupador = pkIdagrupador;
    }

    public String getGvIdagrupadorBw() {
        return gvIdagrupadorBw;
    }

    public void setGvIdagrupadorBw(String gvIdagrupadorBw) {
        this.gvIdagrupadorBw = gvIdagrupadorBw;
    }

    public String getIdagrupadortexto() {
        return idagrupadortexto;
    }

    public void setIdagrupadortexto(String idagrupadortexto) {
        this.idagrupadortexto = idagrupadortexto;
    }

    public BigInteger getFkSegmentoMkt() {
        return fkSegmentoMkt;
    }

    public void setFkSegmentoMkt(BigInteger fkSegmentoMkt) {
        this.fkSegmentoMkt = fkSegmentoMkt;
    }

    public BigInteger getFkGecMkt() {
        return fkGecMkt;
    }

    public void setFkGecMkt(BigInteger fkGecMkt) {
        this.fkGecMkt = fkGecMkt;
    }

    public BigInteger getFkMarcaMkt() {
        return fkMarcaMkt;
    }

    public void setFkMarcaMkt(BigInteger fkMarcaMkt) {
        this.fkMarcaMkt = fkMarcaMkt;
    }

    public BigInteger getFkTamanioMkt() {
        return fkTamanioMkt;
    }

    public void setFkTamanioMkt(BigInteger fkTamanioMkt) {
        this.fkTamanioMkt = fkTamanioMkt;
    }

    public BigInteger getFkEmpaqueMkt() {
        return fkEmpaqueMkt;
    }

    public void setFkEmpaqueMkt(BigInteger fkEmpaqueMkt) {
        this.fkEmpaqueMkt = fkEmpaqueMkt;
    }

    public BigInteger getFkConceptoMkt() {
        return fkConceptoMkt;
    }

    public void setFkConceptoMkt(BigInteger fkConceptoMkt) {
        this.fkConceptoMkt = fkConceptoMkt;
    }

    public BigInteger getFkAgru1Mkt() {
        return fkAgru1Mkt;
    }

    public void setFkAgru1Mkt(BigInteger fkAgru1Mkt) {
        this.fkAgru1Mkt = fkAgru1Mkt;
    }

    public BigInteger getFkAgru2Mkt() {
        return fkAgru2Mkt;
    }

    public void setFkAgru2Mkt(BigInteger fkAgru2Mkt) {
        this.fkAgru2Mkt = fkAgru2Mkt;
    }

    public BigInteger getFkAgru3Mkt() {
        return fkAgru3Mkt;
    }

    public void setFkAgru3Mkt(BigInteger fkAgru3Mkt) {
        this.fkAgru3Mkt = fkAgru3Mkt;
    }

    public String getSegmentoMkt() {
        return segmentoMkt;
    }

    public void setSegmentoMkt(String segmentoMkt) {
        this.segmentoMkt = segmentoMkt;
    }

    public String getGecMkt() {
        return gecMkt;
    }

    public void setGecMkt(String gecMkt) {
        this.gecMkt = gecMkt;
    }

    public String getMarcaMkt() {
        return marcaMkt;
    }

    public void setMarcaMkt(String marcaMkt) {
        this.marcaMkt = marcaMkt;
    }

    public String getTamanioMkt() {
        return tamanioMkt;
    }

    public void setTamanioMkt(String tamanioMkt) {
        this.tamanioMkt = tamanioMkt;
    }

    public String getEmpaqueMkt() {
        return empaqueMkt;
    }

    public void setEmpaqueMkt(String empaqueMkt) {
        this.empaqueMkt = empaqueMkt;
    }

    public String getConceptoMkt() {
        return conceptoMkt;
    }

    public void setConceptoMkt(String conceptoMkt) {
        this.conceptoMkt = conceptoMkt;
    }

    public String getAgru1Mkt() {
        return agru1Mkt;
    }

    public void setAgru1Mkt(String agru1Mkt) {
        this.agru1Mkt = agru1Mkt;
    }

    public String getAgru2Mkt() {
        return agru2Mkt;
    }

    public void setAgru2Mkt(String agru2Mkt) {
        this.agru2Mkt = agru2Mkt;
    }

    public String getAgru3Mkt() {
        return agru3Mkt;
    }

    public void setAgru3Mkt(String agru3Mkt) {
        this.agru3Mkt = agru3Mkt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdagrupador != null ? pkIdagrupador.hashCode() : 0);
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
        final MktCobhDimAgrupador other = (MktCobhDimAgrupador) obj;
        if ((this.idagrupadortexto == null) ? (other.idagrupadortexto != null) : !this.idagrupadortexto.equals(other.idagrupadortexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimAgrupador{" + "pkIdagrupador=" + pkIdagrupador + ", gvIdagrupadorBw=" + gvIdagrupadorBw + ", idagrupadortexto=" + idagrupadortexto + ", fkSegmentoMkt=" + fkSegmentoMkt + ", fkGecMkt=" + fkGecMkt + ", fkMarcaMkt=" + fkMarcaMkt + ", fkTamanioMkt=" + fkTamanioMkt + ", fkEmpaqueMkt=" + fkEmpaqueMkt + ", fkConceptoMkt=" + fkConceptoMkt + ", fkAgru1Mkt=" + fkAgru1Mkt + ", fkAgru2Mkt=" + fkAgru2Mkt + ", fkAgru3Mkt=" + fkAgru3Mkt + ", segmentoMkt=" + segmentoMkt + ", gecMkt=" + gecMkt + ", marcaMkt=" + marcaMkt + ", tamanioMkt=" + tamanioMkt + ", empaqueMkt=" + empaqueMkt + ", conceptoMkt=" + conceptoMkt + ", agru1Mkt=" + agru1Mkt + ", agru2Mkt=" + agru2Mkt + ", agru3Mkt=" + agru3Mkt + '}';
    }

}
