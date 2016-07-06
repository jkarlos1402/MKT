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
@Table(name = "MKT_COBH_DIM_CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimCliente.findAll", query = "SELECT m FROM MktCobhDimCliente m")})
public class MktCobhDimCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_CLIENTE")
    @SequenceGenerator(name = "MKT_COBH_SEQ_CLIENTE", sequenceName = "MKT_COBH_SEQ_CLIENTE", allocationSize = 1)
    @Column(name = "PK_CLIENTE")
    private Integer pkCliente;
    @Column(name = "GV_CLIENTE")
    private String gvCliente;
    @Column(name = "CLIENTETEXTO")
    private String clientetexto;

    public MktCobhDimCliente() {
    }

    public MktCobhDimCliente(Integer pkCliente) {
        this.pkCliente = pkCliente;
    }

    public Integer getPkCliente() {
        return pkCliente;
    }

    public void setPkCliente(Integer pkCliente) {
        this.pkCliente = pkCliente;
    }

    public String getGvCliente() {
        return gvCliente;
    }

    public void setGvCliente(String gvCliente) {
        this.gvCliente = gvCliente;
    }

    public String getClientetexto() {
        return clientetexto;
    }

    public void setClientetexto(String clientetexto) {
        this.clientetexto = clientetexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCliente != null ? pkCliente.hashCode() : 0);
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
        final MktCobhDimCliente other = (MktCobhDimCliente) obj;
        if ((this.clientetexto == null) ? (other.clientetexto != null) : !this.clientetexto.equals(other.clientetexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimCliente{" + "pkCliente=" + pkCliente + ", gvCliente=" + gvCliente + ", clientetexto=" + clientetexto + '}';
    }

}
