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

/**
 *
 * @author TMXIDSCPEREZ
 */
@Entity
@Table(name = "MKT_COB_ST_HIS_OBJETIVOS")
@NamedQueries({
    @NamedQuery(name = "MktCobStHisObjetivos.findAll", query = "SELECT m FROM MktCobStHisObjetivos m")})
public class MktCobStHisObjetivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CONCEPTO")
    private String concepto;
    @Column(name = "C_CENTRO")
    private String cCentro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "OBJETIVO")
    private double objetivo;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_ST_HIS_OBJETIVOS")
    @SequenceGenerator(name = "MKT_SEQ_ST_HIS_OBJETIVOS", sequenceName = "MKT_SEQ_ST_HIS_OBJETIVOS", allocationSize = 1)
    @Column(name = "PK_OBJETIVOS")
    private BigDecimal pkObjetivos;

    public MktCobStHisObjetivos() {
    }

    public MktCobStHisObjetivos(BigDecimal pkObjetivos) {
        this.pkObjetivos = pkObjetivos;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCCentro() {
        return cCentro;
    }

    public void setCCentro(String cCentro) {
        this.cCentro = cCentro;
    }

    public double getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(double objetivo) {
        this.objetivo = objetivo;
    }

    public BigDecimal getPkObjetivos() {
        return pkObjetivos;
    }

    public void setPkObjetivos(BigDecimal pkObjetivos) {
        this.pkObjetivos = pkObjetivos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkObjetivos != null ? pkObjetivos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktCobStHisObjetivos)) {
            return false;
        }
        MktCobStHisObjetivos other = (MktCobStHisObjetivos) object;
        if ((this.pkObjetivos == null && other.pkObjetivos != null) || (this.pkObjetivos != null && !this.pkObjetivos.equals(other.pkObjetivos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktCobStHisObjetivos[ pkObjetivos=" + pkObjetivos + " ]";
    }
    
}
