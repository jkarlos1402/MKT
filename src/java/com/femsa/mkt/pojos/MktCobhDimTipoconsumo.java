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
@Table(name = "MKT_COBH_DIM_TIPOCONSUMO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimTipoconsumo.findAll", query = "SELECT m FROM MktCobhDimTipoconsumo m")})
public class MktCobhDimTipoconsumo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_TIPOCONSUMO")
    @SequenceGenerator(name = "MKT_COBH_SEQ_TIPOCONSUMO", sequenceName = "MKT_COBH_SEQ_TIPOCONSUMO", allocationSize = 1)
    @Column(name = "PK_TIPOCONSUMO")
    private Integer pkTipoconsumo;
    @Column(name = "GV_TIPOCONSUMO")
    private String gvTipoconsumo;
    @Column(name = "TIPOCONSUMOTEXTO")
    private String tipoconsumotexto;

    public MktCobhDimTipoconsumo() {
    }

    public MktCobhDimTipoconsumo(Integer pkTipoconsumo) {
        this.pkTipoconsumo = pkTipoconsumo;
    }

    public Integer getPkTipoconsumo() {
        return pkTipoconsumo;
    }

    public void setPkTipoconsumo(Integer pkTipoconsumo) {
        this.pkTipoconsumo = pkTipoconsumo;
    }

    public String getGvTipoconsumo() {
        return gvTipoconsumo;
    }

    public void setGvTipoconsumo(String gvTipoconsumo) {
        this.gvTipoconsumo = gvTipoconsumo;
    }

    public String getTipoconsumotexto() {
        return tipoconsumotexto;
    }

    public void setTipoconsumotexto(String tipoconsumotexto) {
        this.tipoconsumotexto = tipoconsumotexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTipoconsumo != null ? pkTipoconsumo.hashCode() : 0);
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
        final MktCobhDimTipoconsumo other = (MktCobhDimTipoconsumo) obj;
        if ((this.tipoconsumotexto == null) ? (other.tipoconsumotexto != null) : !this.tipoconsumotexto.equals(other.tipoconsumotexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimTipoconsumo{" + "pkTipoconsumo=" + pkTipoconsumo + ", gvTipoconsumo=" + gvTipoconsumo + ", tipoconsumotexto=" + tipoconsumotexto + '}';
    }

}
