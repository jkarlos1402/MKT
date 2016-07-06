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
@Table(name = "MKT_COBH_DIM_TERRITORIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimTerritorio.findAll", query = "SELECT m FROM MktCobhDimTerritorio m")})
public class MktCobhDimTerritorio implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_TERRITORIO")
    @SequenceGenerator(name = "MKT_COBH_SEQ_TERRITORIO", sequenceName = "MKT_COBH_SEQ_TERRITORIO", allocationSize = 1)
    @Column(name = "PK_TERRITORIO")
    private Integer pkTerritorio;
    @Column(name = "GV_TERRITORIO")
    private String gvTerritorio;
    @Column(name = "TERRITORIOTEXTO")
    private String territoriotexto;

    public MktCobhDimTerritorio() {
    }

    public MktCobhDimTerritorio(Integer pkTerritorio) {
        this.pkTerritorio = pkTerritorio;
    }

    public MktCobhDimTerritorio(String territoriotexto) {
        this.territoriotexto = territoriotexto;
    }

    public Integer getPkTerritorio() {
        return pkTerritorio;
    }

    public void setPkTerritorio(Integer pkTerritorio) {
        this.pkTerritorio = pkTerritorio;
    }

    public String getGvTerritorio() {
        return gvTerritorio;
    }

    public void setGvTerritorio(String gvTerritorio) {
        this.gvTerritorio = gvTerritorio;
    }

    public String getTerritoriotexto() {
        return territoriotexto;
    }

    public void setTerritoriotexto(String territoriotexto) {
        this.territoriotexto = territoriotexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTerritorio != null ? pkTerritorio.hashCode() : 0);
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
        final MktCobhDimTerritorio other = (MktCobhDimTerritorio) obj;
        if ((this.territoriotexto == null) ? (other.territoriotexto != null) : !this.territoriotexto.equals(other.territoriotexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimTerritorio{" + "pkTerritorio=" + pkTerritorio + ", gvTerritorio=" + gvTerritorio + ", territoriotexto=" + territoriotexto + '}';
    }

}
