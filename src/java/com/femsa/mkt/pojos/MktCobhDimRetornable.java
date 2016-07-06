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
@Table(name = "MKT_COBH_DIM_RETORNABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimRetornable.findAll", query = "SELECT m FROM MktCobhDimRetornable m")})
public class MktCobhDimRetornable implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_RETORNABLE")
    @SequenceGenerator(name = "MKT_COBH_SEQ_RETORNABLE", sequenceName = "MKT_COBH_SEQ_RETORNABLE", allocationSize = 1)
    @Column(name = "PK_RETORNABLE")
    private Integer pkRetornable;
    @Column(name = "GV_RETORNABLE")
    private String gvRetornable;
    @Column(name = "RETORNABLETEXTO")
    private String retornabletexto;

    public MktCobhDimRetornable() {
    }

    public MktCobhDimRetornable(Integer pkRetornable) {
        this.pkRetornable = pkRetornable;
    }

    public MktCobhDimRetornable(String retornabletexto) {
        this.retornabletexto = retornabletexto;
    }

    public Integer getPkRetornable() {
        return pkRetornable;
    }

    public void setPkRetornable(Integer pkRetornable) {
        this.pkRetornable = pkRetornable;
    }

    public String getGvRetornable() {
        return gvRetornable;
    }

    public void setGvRetornable(String gvRetornable) {
        this.gvRetornable = gvRetornable;
    }

    public String getRetornabletexto() {
        return retornabletexto;
    }

    public void setRetornabletexto(String retornabletexto) {
        this.retornabletexto = retornabletexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkRetornable != null ? pkRetornable.hashCode() : 0);
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
        final MktCobhDimRetornable other = (MktCobhDimRetornable) obj;
        if ((this.retornabletexto == null) ? (other.retornabletexto != null) : !this.retornabletexto.equals(other.retornabletexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimRetornable{" + "pkRetornable=" + pkRetornable + ", gvRetornable=" + gvRetornable + ", retornabletexto=" + retornabletexto + '}';
    }

}
