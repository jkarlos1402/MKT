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
@Table(name = "MKT_COBH_DIM_GRUPO_RM2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimGrupoRm2.findAll", query = "SELECT m FROM MktCobhDimGrupoRm2 m")})
public class MktCobhDimGrupoRm2 implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_GRUPO_RM2")
    @SequenceGenerator(name = "MKT_COBH_SEQ_GRUPO_RM2", sequenceName = "MKT_COBH_SEQ_GRUPO_RM2", allocationSize = 1)
    @Column(name = "PK_RM2")
    private Integer pkRm2;
    @Column(name = "GV_RM2")
    private String gvRm2;
    @Column(name = "RM2TEXTO")
    private String rm2texto;

    public MktCobhDimGrupoRm2() {
    }

    public MktCobhDimGrupoRm2(Integer pkRm2) {
        this.pkRm2 = pkRm2;
    }

    public Integer getPkRm2() {
        return pkRm2;
    }

    public void setPkRm2(Integer pkRm2) {
        this.pkRm2 = pkRm2;
    }

    public String getGvRm2() {
        return gvRm2;
    }

    public void setGvRm2(String gvRm2) {
        this.gvRm2 = gvRm2;
    }

    public String getRm2texto() {
        return rm2texto;
    }

    public void setRm2texto(String rm2texto) {
        this.rm2texto = rm2texto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkRm2 != null ? pkRm2.hashCode() : 0);
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
        final MktCobhDimGrupoRm2 other = (MktCobhDimGrupoRm2) obj;
        if ((this.rm2texto == null) ? (other.rm2texto != null) : !this.rm2texto.equals(other.rm2texto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimGrupoRm2{" + "pkRm2=" + pkRm2 + ", gvRm2=" + gvRm2 + ", rm2texto=" + rm2texto + '}';
    }

}
