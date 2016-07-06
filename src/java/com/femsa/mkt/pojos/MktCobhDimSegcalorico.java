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
@Table(name = "MKT_COBH_DIM_SEGCALORICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimSegcalorico.findAll", query = "SELECT m FROM MktCobhDimSegcalorico m")})
public class MktCobhDimSegcalorico implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_SEGCALORICO")
    @SequenceGenerator(name = "MKT_COBH_SEQ_SEGCALORICO", sequenceName = "MKT_COBH_SEQ_SEGCALORICO", allocationSize = 1)
    @Column(name = "PK_SEGCALORICO")
    private Integer pkSegcalorico;
    @Column(name = "GV_SEGCALORICO")
    private String gvSegcalorico;
    @Column(name = "SEGCALORICOTEXTO")
    private String segcaloricotexto;

    public MktCobhDimSegcalorico() {
    }

    public MktCobhDimSegcalorico(Integer pkSegcalorico) {
        this.pkSegcalorico = pkSegcalorico;
    }

    public MktCobhDimSegcalorico(String segcaloricotexto) {
        this.segcaloricotexto = segcaloricotexto;
    }

    public Integer getPkSegcalorico() {
        return pkSegcalorico;
    }

    public void setPkSegcalorico(Integer pkSegcalorico) {
        this.pkSegcalorico = pkSegcalorico;
    }

    public String getGvSegcalorico() {
        return gvSegcalorico;
    }

    public void setGvSegcalorico(String gvSegcalorico) {
        this.gvSegcalorico = gvSegcalorico;
    }

    public String getSegcaloricotexto() {
        return segcaloricotexto;
    }

    public void setSegcaloricotexto(String segcaloricotexto) {
        this.segcaloricotexto = segcaloricotexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkSegcalorico != null ? pkSegcalorico.hashCode() : 0);
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
        final MktCobhDimSegcalorico other = (MktCobhDimSegcalorico) obj;
        if ((this.segcaloricotexto == null) ? (other.segcaloricotexto != null) : !this.segcaloricotexto.equals(other.segcaloricotexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimSegcalorico{" + "pkSegcalorico=" + pkSegcalorico + ", gvSegcalorico=" + gvSegcalorico + ", segcaloricotexto=" + segcaloricotexto + '}';
    }

}
