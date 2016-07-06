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
@Table(name = "MKT_COBH_DIM_PLANVISITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimPlanvisita.findAll", query = "SELECT m FROM MktCobhDimPlanvisita m")})
public class MktCobhDimPlanvisita implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_PLANVISITA")
    @SequenceGenerator(name = "MKT_COBH_SEQ_PLANVISITA", sequenceName = "MKT_COBH_SEQ_PLANVISITA", allocationSize = 1)
    @Column(name = "PK_PLANVISITA")
    private Integer pkPlanvisita;
    @Column(name = "GV_PLANVISITA")
    private String gvPlanvisita;
    @Column(name = "PLANVISITATEXTO")
    private String planvisitatexto;

    public MktCobhDimPlanvisita() {
    }

    public MktCobhDimPlanvisita(Integer pkPlanvisita) {
        this.pkPlanvisita = pkPlanvisita;
    }

    public Integer getPkPlanvisita() {
        return pkPlanvisita;
    }

    public void setPkPlanvisita(Integer pkPlanvisita) {
        this.pkPlanvisita = pkPlanvisita;
    }

    public String getGvPlanvisita() {
        return gvPlanvisita;
    }

    public void setGvPlanvisita(String gvPlanvisita) {
        this.gvPlanvisita = gvPlanvisita;
    }

    public String getPlanvisitatexto() {
        return planvisitatexto;
    }

    public void setPlanvisitatexto(String planvisitatexto) {
        this.planvisitatexto = planvisitatexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPlanvisita != null ? pkPlanvisita.hashCode() : 0);
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
        final MktCobhDimPlanvisita other = (MktCobhDimPlanvisita) obj;
        if ((this.planvisitatexto == null) ? (other.planvisitatexto != null) : !this.planvisitatexto.equals(other.planvisitatexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimPlanvisita{" + "pkPlanvisita=" + pkPlanvisita + ", gvPlanvisita=" + gvPlanvisita + ", planvisitatexto=" + planvisitatexto + '}';
    }

}
