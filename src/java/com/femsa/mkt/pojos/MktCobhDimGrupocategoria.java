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
@Table(name = "MKT_COBH_DIM_GRUPOCATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimGrupocategoria.findAll", query = "SELECT m FROM MktCobhDimGrupocategoria m")})
public class MktCobhDimGrupocategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_GRUPOCATEGORIA")
    @SequenceGenerator(name = "MKT_COBH_SEQ_GRUPOCATEGORIA", sequenceName = "MKT_COBH_SEQ_GRUPOCATEGORIA", allocationSize = 1)
    @Column(name = "PK_GRUPOCATEGORIA")
    private Integer pkGrupocategoria;
    @Column(name = "GV_GRUPOCATEGORIA")
    private String gvGrupocategoria;
    @Column(name = "GRUPOCATEGORIATEXTO")
    private String grupocategoriatexto;

    public MktCobhDimGrupocategoria() {
    }

    public MktCobhDimGrupocategoria(Integer pkGrupocategoria) {
        this.pkGrupocategoria = pkGrupocategoria;
    }

    public Integer getPkGrupocategoria() {
        return pkGrupocategoria;
    }

    public void setPkGrupocategoria(Integer pkGrupocategoria) {
        this.pkGrupocategoria = pkGrupocategoria;
    }

    public String getGvGrupocategoria() {
        return gvGrupocategoria;
    }

    public void setGvGrupocategoria(String gvGrupocategoria) {
        this.gvGrupocategoria = gvGrupocategoria;
    }

    public String getGrupocategoriatexto() {
        return grupocategoriatexto;
    }

    public void setGrupocategoriatexto(String grupocategoriatexto) {
        this.grupocategoriatexto = grupocategoriatexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGrupocategoria != null ? pkGrupocategoria.hashCode() : 0);
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
        final MktCobhDimGrupocategoria other = (MktCobhDimGrupocategoria) obj;
        if ((this.grupocategoriatexto == null) ? (other.grupocategoriatexto != null) : !this.grupocategoriatexto.equals(other.grupocategoriatexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimGrupocategoria{" + "pkGrupocategoria=" + pkGrupocategoria + ", gvGrupocategoria=" + gvGrupocategoria + ", grupocategoriatexto=" + grupocategoriatexto + '}';
    }

}
