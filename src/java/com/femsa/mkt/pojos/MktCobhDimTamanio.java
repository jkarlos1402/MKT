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
@Table(name = "MKT_COBH_DIM_TAMANIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimTamanio.findAll", query = "SELECT m FROM MktCobhDimTamanio m")})
public class MktCobhDimTamanio implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_TAMANIO")
    @SequenceGenerator(name = "MKT_COBH_SEQ_TAMANIO", sequenceName = "MKT_COBH_SEQ_TAMANIO", allocationSize = 1)
    @Column(name = "PK_TAMANIO")
    private Integer pkTamanio;
    @Column(name = "GV_TAMANIO")
    private String gvTamanio;
    @Column(name = "TAMANIOTEXTO")
    private String tamaniotexto;

    public MktCobhDimTamanio() {
    }

    public MktCobhDimTamanio(Integer pkTamanio) {
        this.pkTamanio = pkTamanio;
    }

    public MktCobhDimTamanio(String tamaniotexto) {
        this.tamaniotexto = tamaniotexto;
    }

    public Integer getPkTamanio() {
        return pkTamanio;
    }

    public void setPkTamanio(Integer pkTamanio) {
        this.pkTamanio = pkTamanio;
    }

    public String getGvTamanio() {
        return gvTamanio;
    }

    public void setGvTamanio(String gvTamanio) {
        this.gvTamanio = gvTamanio;
    }

    public String getTamaniotexto() {
        return tamaniotexto;
    }

    public void setTamaniotexto(String tamaniotexto) {
        this.tamaniotexto = tamaniotexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTamanio != null ? pkTamanio.hashCode() : 0);
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
        final MktCobhDimTamanio other = (MktCobhDimTamanio) obj;
        if ((this.tamaniotexto == null) ? (other.tamaniotexto != null) : !this.tamaniotexto.equals(other.tamaniotexto)) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() {
        return "MktCobhDimTamanio{" + "pkTamanio=" + pkTamanio + ", gvTamanio=" + gvTamanio + ", tamaniotexto=" + tamaniotexto + '}';
    }

}
