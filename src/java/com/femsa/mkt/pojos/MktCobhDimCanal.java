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
@Table(name = "MKT_COBH_DIM_CANAL")
@NamedQueries({
@NamedQuery(name = "MktCobhDimCanal.findAll", query = "SELECT m FROM MktCobhDimCanal m")})
public class MktCobhDimCanal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_CANAL")
    @SequenceGenerator(name = "MKT_COBH_SEQ_CANAL", sequenceName = "MKT_COBH_SEQ_CANAL", allocationSize = 1)
    @Column(name = "PK_CANAL")
    private Long pkCanal;    
    @Column(name = "GV_CANAL")   
    private String gvCanal;
    @Column(name = "CANALTEXTO")
    private String canaltexto;
//    @OneToMany(mappedBy = "idcanal")
//    private List<MktCobhStHisDatos> mktCobhStHisDatosList;

    public MktCobhDimCanal() {
    }

    public MktCobhDimCanal(Long pkCanal) {
        this.pkCanal = pkCanal;
    }

    public Long getPkCanal() {
        return pkCanal;
    }

    public void setPkCanal(Long pkCanal) {
        this.pkCanal = pkCanal;
    }

    public String getGvCanal() {
        return gvCanal;
    }

    public void setGvCanal(String gvCanal) {
        this.gvCanal = gvCanal;
    }

    public String getCanaltexto() {
        return canaltexto;
    }

    public void setCanaltexto(String canaltexto) {
        this.canaltexto = canaltexto;
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
        hash = 41 * hash + (int) (this.pkCanal ^ (this.pkCanal >>> 32));
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
        final MktCobhDimCanal other = (MktCobhDimCanal) obj;
        if ((this.canaltexto == null) ? (other.canaltexto != null) : !this.canaltexto.equals(other.canaltexto)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "MktCobhDimCanal{" + "pkCanal=" + pkCanal + ", gvCanal=" + gvCanal + ", canaltexto=" + canaltexto +'}';
    }    
}
