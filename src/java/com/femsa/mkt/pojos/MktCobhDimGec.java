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

/**
 *
 * @author TMXIDSCPEREZ
 */
@Entity
@Table(name = "MKT_COBH_DIM_GEC")
@NamedQueries({
    @NamedQuery(name = "MktCobhDimGec.findAll", query = "SELECT m FROM MktCobhDimGec m")})
public class MktCobhDimGec implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_GEC")
    @SequenceGenerator(name = "MKT_COBH_SEQ_GEC", sequenceName = "MKT_COBH_SEQ_GEC", allocationSize = 1)
    @Column(name = "PK_GEC")
    private Long pkGec;

    @Column(name = "GV_GEC")
    private String gvGec;
    @Column(name = "GECTEXTO")
    private String gecTexto;
//    @OneToMany(mappedBy = "idgec")
//    private List<MktCobhStHisDatos> mktCobhStHisDatosList;

    public MktCobhDimGec() {
    }

    public MktCobhDimGec(Long pkGec) {
        this.pkGec = pkGec;
    }

    public MktCobhDimGec(String gecTexto) {
        this.gecTexto = gecTexto;
    }

    public Long getPkGec() {
        return pkGec;
    }

    public void setPkGec(Long pkGec) {
        this.pkGec = pkGec;
    }

    public String getGvGec() {
        return gvGec;
    }

    public void setGvGec(String gvGec) {
        this.gvGec = gvGec;
    }

    public String getGecTexto() {
        return gecTexto;
    }

    public void setGecTexto(String gecTexto) {
        this.gecTexto = gecTexto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.pkGec ^ (this.pkGec >>> 32));
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
        final MktCobhDimGec other = (MktCobhDimGec) obj;
        if ((this.gecTexto == null) ? (other.gecTexto != null) : !this.gecTexto.equals(other.gecTexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimGec{" + "pkGec=" + pkGec + ", gvGec=" + gvGec + ", gecTexto=" + gecTexto + '}';
    }

}
