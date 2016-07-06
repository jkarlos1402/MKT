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
@Table(name = "MKT_COBH_DIM_OCACONSUMO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimOcaconsumo.findAll", query = "SELECT m FROM MktCobhDimOcaconsumo m")})
public class MktCobhDimOcaconsumo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_OCACONSUMO")
    @SequenceGenerator(name = "MKT_COBH_SEQ_OCACONSUMO", sequenceName = "MKT_COBH_SEQ_OCACONSUMO", allocationSize = 1)
    @Column(name = "PK_OCACONSUMO")
    private Integer pkOcaconsumo;
    @Column(name = "GV_OCACONSUMO")
    private String gvOcaconsumo;
    @Column(name = "OCACONSUMOTEXTO")
    private String ocaconsumotexto;

    public MktCobhDimOcaconsumo() {
    }

    public MktCobhDimOcaconsumo(Integer pkOcaconsumo) {
        this.pkOcaconsumo = pkOcaconsumo;
    }

    public Integer getPkOcaconsumo() {
        return pkOcaconsumo;
    }

    public void setPkOcaconsumo(Integer pkOcaconsumo) {
        this.pkOcaconsumo = pkOcaconsumo;
    }

    public String getGvOcaconsumo() {
        return gvOcaconsumo;
    }

    public void setGvOcaconsumo(String gvOcaconsumo) {
        this.gvOcaconsumo = gvOcaconsumo;
    }

    public String getOcaconsumotexto() {
        return ocaconsumotexto;
    }

    public void setOcaconsumotexto(String ocaconsumotexto) {
        this.ocaconsumotexto = ocaconsumotexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkOcaconsumo != null ? pkOcaconsumo.hashCode() : 0);
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
        final MktCobhDimOcaconsumo other = (MktCobhDimOcaconsumo) obj;
        if ((this.ocaconsumotexto == null) ? (other.ocaconsumotexto != null) : !this.ocaconsumotexto.equals(other.ocaconsumotexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimOcaconsumo{" + "pkOcaconsumo=" + pkOcaconsumo + ", gvOcaconsumo=" + gvOcaconsumo + ", ocaconsumotexto=" + ocaconsumotexto + '}';
    }

}
