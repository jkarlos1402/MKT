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
@Table(name = "MKT_COBH_DIM_SKU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimSku.findAll", query = "SELECT m FROM MktCobhDimSku m")})
public class MktCobhDimSku implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_SKU")
    @SequenceGenerator(name = "MKT_COBH_SEQ_SKU", sequenceName = "MKT_COBH_SEQ_SKU", allocationSize = 1)
    @Column(name = "PK_SKU")
    private Integer pkSku;
    @Column(name = "GV_SKU")
    private String gvSku;
    @Column(name = "SKUTEXTO")
    private String skutexto;

    public MktCobhDimSku() {
    }

    public MktCobhDimSku(Integer pkSku) {
        this.pkSku = pkSku;
    }

    public Integer getPkSku() {
        return pkSku;
    }

    public void setPkSku(Integer pkSku) {
        this.pkSku = pkSku;
    }

    public String getGvSku() {
        return gvSku;
    }

    public void setGvSku(String gvSku) {
        this.gvSku = gvSku;
    }

    public String getSkutexto() {
        return skutexto;
    }

    public void setSkutexto(String skutexto) {
        this.skutexto = skutexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkSku != null ? pkSku.hashCode() : 0);
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
        final MktCobhDimSku other = (MktCobhDimSku) obj;
        if ((this.skutexto == null) ? (other.skutexto != null) : !this.skutexto.equals(other.skutexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimSku{" + "pkSku=" + pkSku + ", gvSku=" + gvSku + ", skutexto=" + skutexto + '}';
    }

}
