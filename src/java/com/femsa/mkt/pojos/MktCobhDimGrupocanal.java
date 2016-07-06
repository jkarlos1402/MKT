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
@Table(name = "MKT_COBH_DIM_GRUPOCANAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimGrupocanal.findAll", query = "SELECT m FROM MktCobhDimGrupocanal m")})
public class MktCobhDimGrupocanal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_GRUPOCANAL")
    @SequenceGenerator(name = "MKT_COBH_SEQ_GRUPOCANAL", sequenceName = "MKT_COBH_SEQ_GRUPOCANAL", allocationSize = 1)
    @Column(name = "PK_GRUPOCANAL")
    private Integer pkGrupocanal;
    @Column(name = "GV_GRUPOCANAL")
    private String gvGrupocanal;
    @Column(name = "GRUPOCANALTEXTO")
    private String grupocanaltexto;

    public MktCobhDimGrupocanal() {
    }

    public MktCobhDimGrupocanal(Integer pkGrupocanal) {
        this.pkGrupocanal = pkGrupocanal;
    }

    public Integer getPkGrupocanal() {
        return pkGrupocanal;
    }

    public void setPkGrupocanal(Integer pkGrupocanal) {
        this.pkGrupocanal = pkGrupocanal;
    }

    public String getGvGrupocanal() {
        return gvGrupocanal;
    }

    public void setGvGrupocanal(String gvGrupocanal) {
        this.gvGrupocanal = gvGrupocanal;
    }

    public String getGrupocanaltexto() {
        return grupocanaltexto;
    }

    public void setGrupocanaltexto(String grupocanaltexto) {
        this.grupocanaltexto = grupocanaltexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGrupocanal != null ? pkGrupocanal.hashCode() : 0);
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
        final MktCobhDimGrupocanal other = (MktCobhDimGrupocanal) obj;
        if ((this.grupocanaltexto == null) ? (other.grupocanaltexto != null) : !this.grupocanaltexto.equals(other.grupocanaltexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimGrupocanal{" + "pkGrupocanal=" + pkGrupocanal + ", gvGrupocanal=" + gvGrupocanal + ", grupocanaltexto=" + grupocanaltexto + '}';
    }

}
