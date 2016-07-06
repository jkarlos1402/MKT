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
@Table(name = "MKT_COBH_DIM_SABOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimSabor.findAll", query = "SELECT m FROM MktCobhDimSabor m")})
public class MktCobhDimSabor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_SABOR")
    @SequenceGenerator(name = "MKT_COBH_SEQ_SABOR", sequenceName = "MKT_COBH_SEQ_SABOR", allocationSize = 1)    
    @Column(name = "PK_SABOR")
    private Integer pkSabor;
    @Column(name = "GV_SABOR")
    private String gvSabor;
    @Column(name = "SABORTEXTO")
    private String sabortexto;

    public MktCobhDimSabor() {
    }

    public MktCobhDimSabor(Integer pkSabor) {
        this.pkSabor = pkSabor;
    }

    public Integer getPkSabor() {
        return pkSabor;
    }

    public void setPkSabor(Integer pkSabor) {
        this.pkSabor = pkSabor;
    }

    public String getGvSabor() {
        return gvSabor;
    }

    public void setGvSabor(String gvSabor) {
        this.gvSabor = gvSabor;
    }

    public String getSabortexto() {
        return sabortexto;
    }

    public void setSabortexto(String sabortexto) {
        this.sabortexto = sabortexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkSabor != null ? pkSabor.hashCode() : 0);
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
        final MktCobhDimSabor other = (MktCobhDimSabor) obj;
        if ((this.sabortexto == null) ? (other.sabortexto != null) : !this.sabortexto.equals(other.sabortexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimSabor{" + "pkSabor=" + pkSabor + ", gvSabor=" + gvSabor + ", sabortexto=" + sabortexto + '}';
    }

}
