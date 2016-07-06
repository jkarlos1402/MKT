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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author TMXIDSCPEREZ
 */
@Entity
@Table(name = "MKT_COBH_DIM_UO")
@NamedQueries({
    @NamedQuery(name = "MktCobhDimUo.findAll", query = "SELECT m FROM MktCobhDimUo m")})
public class MktCobhDimUo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_COBH_UO")
    @SequenceGenerator(name = "MKT_SEQ_COBH_UO", sequenceName = "MKT_SEQ_COBH_UO", allocationSize = 1)
    @Column(name = "PK_UO")
    private Long pkUo;

    @Column(name = "GV_UO")
    private String gvUo;
    
    @JoinColumn(name = "FK_ESTADO", referencedColumnName = "PK_ESTADO")
    @ManyToOne(fetch = FetchType.EAGER)
    private MktCobhDimEstado fkEstado;

    @JoinColumn(name = "FK_REGION", referencedColumnName = "PK_REGION")
    @ManyToOne(fetch = FetchType.EAGER)
    private MktCobhDimRegion fkRegion;

    @Column(name = "UO")
    private String uo;

    public MktCobhDimUo() {
    }

    public MktCobhDimUo(Long pkUo) {
        this.pkUo = pkUo;
    }

    public MktCobhDimUo(String uo) {
        this.uo = uo;
    }

    public Long getPkUo() {
        return pkUo;
    }

    public void setPkUo(Long pkUo) {
        this.pkUo = pkUo;
    }

    public String getGvUo() {
        return gvUo;
    }

    public void setGvUo(String gvUo) {
        this.gvUo = gvUo;
    }

    public MktCobhDimEstado getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(MktCobhDimEstado fkEstado) {
        this.fkEstado = fkEstado;
    }

    public MktCobhDimRegion getFkRegion() {
        return fkRegion;
    }

    public void setFkRegion(MktCobhDimRegion fkRegion) {
        this.fkRegion = fkRegion;
    }

    public String getUo() {
        return uo;
    }

    public void setUo(String uo) {
        this.uo = uo;
    }

//    public List<MktCobhStHisDatos> getMktCobhStHisDatosList() {
//        return mktCobhStHisDatosList;
//    }
//
//    public void setMktCobhStHisDatosList(List<MktCobhStHisDatos> mktCobhStHisDatosList) {
//        this.mktCobhStHisDatosList = mktCobhStHisDatosList;
//    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.pkUo ^ (this.pkUo >>> 32));
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
        final MktCobhDimUo other = (MktCobhDimUo) obj;

        if ((this.uo == null) ? (other.uo != null) : !this.uo.equals(other.uo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimUo{" + "pkUo=" + pkUo + ", gvUo=" + gvUo + ", uo=" + uo + '}';
    }

}
