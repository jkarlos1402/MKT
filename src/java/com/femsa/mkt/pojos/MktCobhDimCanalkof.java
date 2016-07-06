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
@Table(name = "MKT_COBH_DIM_CANALKOF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimCanalkof.findAll", query = "SELECT m FROM MktCobhDimCanalkof m")})
public class MktCobhDimCanalkof implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_CANALKOF")
    @SequenceGenerator(name = "MKT_COBH_SEQ_CANALKOF", sequenceName = "MKT_COBH_SEQ_CANALKOF", allocationSize = 1)
    @Column(name = "PK_CANALKOF")
    private Integer pkCanalkof;
    @Column(name = "GV_CANALKOF")
    private String gvCanalkof;
    @Column(name = "CANALKOFTEXTO")
    private String canalkoftexto;

    public MktCobhDimCanalkof() {
    }

    public MktCobhDimCanalkof(Integer pkCanalkof) {
        this.pkCanalkof = pkCanalkof;
    }

    public Integer getPkCanalkof() {
        return pkCanalkof;
    }

    public void setPkCanalkof(Integer pkCanalkof) {
        this.pkCanalkof = pkCanalkof;
    }

    public String getGvCanalkof() {
        return gvCanalkof;
    }

    public void setGvCanalkof(String gvCanalkof) {
        this.gvCanalkof = gvCanalkof;
    }

    public String getCanalkoftexto() {
        return canalkoftexto;
    }

    public void setCanalkoftexto(String canalkoftexto) {
        this.canalkoftexto = canalkoftexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCanalkof != null ? pkCanalkof.hashCode() : 0);
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
        final MktCobhDimCanalkof other = (MktCobhDimCanalkof) obj;
        if ((this.canalkoftexto == null) ? (other.canalkoftexto != null) : !this.canalkoftexto.equals(other.canalkoftexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimCanalkof{" + "pkCanalkof=" + pkCanalkof + ", gvCanalkof=" + gvCanalkof + ", canalkoftexto=" + canalkoftexto + '}';
    }

}
