/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author TMXIDSCPEREZ
 */
@Entity
@Table(name = "MKT_COBH_DIM_ESTADO")
@NamedQueries({
    @NamedQuery(name = "MktCobhDimEstado.findAll", query = "SELECT m FROM MktCobhDimEstado m")})
public class MktCobhDimEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_COBH_ESTADO")
    @SequenceGenerator(name = "MKT_SEQ_COBH_ESTADO", sequenceName = "MKT_SEQ_COBH_ESTADO", allocationSize = 1)
    @Column(name = "PK_ESTADO")    
    private Integer pkEstado;
    
    @Column(name = "GV_ESTADO")
    private String gvEstado;
    @Column(name = "ESTADO")
    private String estado;
//    @OneToMany(mappedBy = "idestado",fetch = FetchType.LAZY)
//    private List<MktCobhStHisDatos> mktCobhStHisDatosList;
    @OneToMany(mappedBy = "fkEstado",fetch = FetchType.EAGER)
    private List<MktCobhDimRegion> mktCobhDimRegionList;

    public MktCobhDimEstado() {
    }

    public List<MktCobhDimRegion> getMktCobhDimRegionList() {
        return mktCobhDimRegionList;
    }

    public MktCobhDimEstado(String estado) {
        this.estado = estado;
    }

    public void setMktCobhDimRegionList(List<MktCobhDimRegion> mktCobhDimRegionList) {
        this.mktCobhDimRegionList = mktCobhDimRegionList;
    }

    public MktCobhDimEstado(Integer pkEstado) {
        this.pkEstado = pkEstado;
    }

    public Integer getPkEstado() {
        return pkEstado;
    }

    public void setPkEstado(Integer pkEstado) {
        this.pkEstado = pkEstado;
    }

    public String getGvEstado() {
        return gvEstado;
    }

    public void setGvEstado(String gvEstado) {
        this.gvEstado = gvEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    public List<MktCobhStHisDatos> getMktCobhStHisDatosList() {
//        return mktCobhStHisDatosList;
//    }
//
//    public void setMktCobhStHisDatosList(List<MktCobhStHisDatos> mktCobhStHisDatosList) {
//        this.mktCobhStHisDatosList = mktCobhStHisDatosList;
//    }
//
//    public List<MktCobhDimRegion> getMktCobhDimRegionList() {
//        return mktCobhDimRegionList;
//    }
//
//    public void setMktCobhDimRegionList(List<MktCobhDimRegion> mktCobhDimRegionList) {
//        this.mktCobhDimRegionList = mktCobhDimRegionList;
//    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.pkEstado ^ (this.pkEstado >>> 32));
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
        final MktCobhDimEstado other = (MktCobhDimEstado) obj;        
        if ((this.estado == null) ? (other.estado != null) : !this.estado.equals(other.estado)) {
            return false;
        }
        return true;
    }

    


    @Override
    public String toString() {
        return "MktCobhDimEstado{" + "pkEstado=" + pkEstado + ", gvEstado=" + gvEstado + ", estado=" + estado +'}';
    }

   
    
}
