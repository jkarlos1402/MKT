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
@Table(name = "MKT_COBH_DIM_GRUPO_RM1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimGrupoRm1.findAll", query = "SELECT m FROM MktCobhDimGrupoRm1 m")})
public class MktCobhDimGrupoRm1 implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_GRUPO_RM1")
    @SequenceGenerator(name = "MKT_COBH_SEQ_GRUPO_RM1", sequenceName = "MKT_COBH_SEQ_GRUPO_RM1", allocationSize = 1)
    @Column(name = "PK_RM1")
    private Integer pkRm1;
    @Column(name = "GV_RM1")
    private String gvRm1;
    @Column(name = "RM1TEXTO")
    private String rm1texto;

    public MktCobhDimGrupoRm1() {
    }

    public MktCobhDimGrupoRm1(Integer pkRm1) {
        this.pkRm1 = pkRm1;
    }

    public MktCobhDimGrupoRm1(String rm1texto) {
        this.rm1texto = rm1texto;
    }

    public Integer getPkRm1() {
        return pkRm1;
    }

    public void setPkRm1(Integer pkRm1) {
        this.pkRm1 = pkRm1;
    }

    public String getGvRm1() {
        return gvRm1;
    }

    public void setGvRm1(String gvRm1) {
        this.gvRm1 = gvRm1;
    }

    public String getRm1texto() {
        return rm1texto;
    }

    public void setRm1texto(String rm1texto) {
        this.rm1texto = rm1texto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkRm1 != null ? pkRm1.hashCode() : 0);
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
        final MktCobhDimGrupoRm1 other = (MktCobhDimGrupoRm1) obj;
        if ((this.rm1texto == null) ? (other.rm1texto != null) : !this.rm1texto.equals(other.rm1texto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimGrupoRm1{" + "pkRm1=" + pkRm1 + ", gvRm1=" + gvRm1 + ", rm1texto=" + rm1texto + '}';
    }

}
