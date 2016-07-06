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
@Table(name = "MKT_COBH_DIM_CANALRM1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimCanalrm1.findAll", query = "SELECT m FROM MktCobhDimCanalrm1 m")})
public class MktCobhDimCanalrm1 implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_CANALRM1")
    @SequenceGenerator(name = "MKT_COBH_SEQ_CANALRM1", sequenceName = "MKT_COBH_SEQ_CANALRM1", allocationSize = 1)
    @Column(name = "PK_CANALRM1")
    private Integer pkCanalrm1;
    @Column(name = "GV_CANALRM1")
    private String gvCanalrm1;
    @Column(name = "CANALRM1TEXTO")
    private String canalrm1texto;

    public MktCobhDimCanalrm1() {
    }

    public MktCobhDimCanalrm1(Integer pkCanalrm1) {
        this.pkCanalrm1 = pkCanalrm1;
    }

    public Integer getPkCanalrm1() {
        return pkCanalrm1;
    }

    public void setPkCanalrm1(Integer pkCanalrm1) {
        this.pkCanalrm1 = pkCanalrm1;
    }

    public String getGvCanalrm1() {
        return gvCanalrm1;
    }

    public void setGvCanalrm1(String gvCanalrm1) {
        this.gvCanalrm1 = gvCanalrm1;
    }

    public String getCanalrm1texto() {
        return canalrm1texto;
    }

    public void setCanalrm1texto(String canalrm1texto) {
        this.canalrm1texto = canalrm1texto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCanalrm1 != null ? pkCanalrm1.hashCode() : 0);
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
        final MktCobhDimCanalrm1 other = (MktCobhDimCanalrm1) obj;
        if ((this.canalrm1texto == null) ? (other.canalrm1texto != null) : !this.canalrm1texto.equals(other.canalrm1texto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimCanalrm1{" + "pkCanalrm1=" + pkCanalrm1 + ", gvCanalrm1=" + gvCanalrm1 + ", canalrm1texto=" + canalrm1texto + '}';
    }

}
