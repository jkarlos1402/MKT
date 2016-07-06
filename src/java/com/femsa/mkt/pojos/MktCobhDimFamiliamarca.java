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
@Table(name = "MKT_COBH_DIM_FAMILIAMARCA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimFamiliamarca.findAll", query = "SELECT m FROM MktCobhDimFamiliamarca m")})
public class MktCobhDimFamiliamarca implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_FAMILIAMARCA")
    @SequenceGenerator(name = "MKT_COBH_SEQ_FAMILIAMARCA", sequenceName = "MKT_COBH_SEQ_FAMILIAMARCA", allocationSize = 1)
    @Column(name = "PK_FAMILIAMARCA")
    private Integer pkFamiliamarca;
    @Column(name = "GV_FAMILIAMARCA")
    private String gvFamiliamarca;
    @Column(name = "FAMILIAMARCATEXTO")
    private String familiamarcatexto;

    public MktCobhDimFamiliamarca() {
    }

    public MktCobhDimFamiliamarca(Integer pkFamiliamarca) {
        this.pkFamiliamarca = pkFamiliamarca;
    }

    public Integer getPkFamiliamarca() {
        return pkFamiliamarca;
    }

    public void setPkFamiliamarca(Integer pkFamiliamarca) {
        this.pkFamiliamarca = pkFamiliamarca;
    }

    public String getGvFamiliamarca() {
        return gvFamiliamarca;
    }

    public void setGvFamiliamarca(String gvFamiliamarca) {
        this.gvFamiliamarca = gvFamiliamarca;
    }

    public String getFamiliamarcatexto() {
        return familiamarcatexto;
    }

    public void setFamiliamarcatexto(String familiamarcatexto) {
        this.familiamarcatexto = familiamarcatexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFamiliamarca != null ? pkFamiliamarca.hashCode() : 0);
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
        final MktCobhDimFamiliamarca other = (MktCobhDimFamiliamarca) obj;
        if ((this.familiamarcatexto == null) ? (other.familiamarcatexto != null) : !this.familiamarcatexto.equals(other.familiamarcatexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimFamiliamarca{" + "pkFamiliamarca=" + pkFamiliamarca + ", gvFamiliamarca=" + gvFamiliamarca + ", familiamarcatexto=" + familiamarcatexto + '}';
    }

}
