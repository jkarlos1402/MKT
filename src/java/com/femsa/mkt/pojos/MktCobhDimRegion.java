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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MKT_COBH_DIM_REGION")
@NamedQueries({
    @NamedQuery(name = "MktCobhDimRegion.findAll", query = "SELECT m FROM MktCobhDimRegion m")})
public class MktCobhDimRegion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_COBH_REGION")
    @SequenceGenerator(name = "MKT_SEQ_COBH_REGION", sequenceName = "MKT_SEQ_COBH_REGION", allocationSize = 1)

    @Column(name = "PK_REGION")
    private Integer pkRegion;

    @JoinColumn(name = "FK_ESTADO", referencedColumnName = "PK_ESTADO")
    @ManyToOne(fetch = FetchType.EAGER)
    private MktCobhDimEstado fkEstado;

    @Column(name = "GV_REGION")
    private String gvRegion;

    @Column(name = "REGION")
    private String region;

    @OneToMany(mappedBy = "fkRegion", fetch = FetchType.EAGER)
    private List<MktCobhDimUo> mktCobhDimUoList;

//    @OneToMany(mappedBy = "idregion")
//    private List<MktCobhStHisDatos> mktCobhStHisDatosList;
    public MktCobhDimRegion() {
    }

    public MktCobhDimRegion(String region) {
        this.region = region;
    }

    public List<MktCobhDimUo> getMktCobhDimUoList() {
        return mktCobhDimUoList;
    }

    public void setMktCobhDimUoList(List<MktCobhDimUo> mktCobhDimUoList) {
        this.mktCobhDimUoList = mktCobhDimUoList;
    }

    public MktCobhDimEstado getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(MktCobhDimEstado fkEstado) {
        this.fkEstado = fkEstado;
    }

    public MktCobhDimRegion(Integer pkRegion) {
        this.pkRegion = pkRegion;
    }

    public Integer getPkRegion() {
        return pkRegion;
    }

    public void setPkRegion(Integer pkRegion) {
        this.pkRegion = pkRegion;
    }

    public String getGvRegion() {
        return gvRegion;
    }

    public void setGvRegion(String gvRegion) {
        this.gvRegion = gvRegion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
        hash = 41 * hash + (int) (this.pkRegion ^ (this.pkRegion >>> 32));
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
        final MktCobhDimRegion other = (MktCobhDimRegion) obj;

        if ((this.region == null) ? (other.region != null) : !this.region.equals(other.region)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimRegion{" + "pkRegion=" + pkRegion + ", fkEstado=" + fkEstado + ", gvRegion=" + gvRegion + ", region=" + region ;
    }

}
