/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_COBH_LOG_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhLogDet.findAll", query = "SELECT m FROM MktCobhLogDet m")})
public class MktCobhLogDet implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_LOG_DET")
    @SequenceGenerator(name = "MKT_COBH_SEQ_LOG_DET", sequenceName = "MKT_COBH_SEQ_LOG_DET", allocationSize = 1)
    @Column(name = "PK_IDREGISTRODET")
    private Integer pkIdregistrodet;
    @Column(name = "DESCRIPCION_MENSAJE")
    private String descripcionMensaje;
    @Column(name = "INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Column(name = "FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date final1;
    @Column(name = "TOTALREGISTROS")
    private long totalregistros;
    @Column(name = "NOMARCHIVO_ORIGEN")
    private String nomarchivoOrigen;
    @Column(name = "NOMARCHIVO_BAK")
    private String nomarchivoBak;
    @Column(name = "TIEMPO")
    private Integer tiempo;
    @JoinColumn(name = "FK_IDREGISTRO", referencedColumnName = "PK_IDREGISTRO")
    @ManyToOne
    private MktCobhLogEnc fkIdregistro;
    @JoinColumn(name = "FK_IDSTATUS", referencedColumnName = "PK_IDSTATUS")
    @ManyToOne
    private MktCobhLogStatus fkIdstatus;

    public MktCobhLogDet() {
    }

    public MktCobhLogDet(Integer pkIdregistrodet) {
        this.pkIdregistrodet = pkIdregistrodet;
    }
    
    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getPkIdregistrodet() {
        return pkIdregistrodet;
    }

    public void setPkIdregistrodet(Integer pkIdregistrodet) {
        this.pkIdregistrodet = pkIdregistrodet;
    }

    public String getDescripcionMensaje() {
        return descripcionMensaje;
    }

    public void setDescripcionMensaje(String descripcionMensaje) {
        this.descripcionMensaje = descripcionMensaje;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFinal1() {
        return final1;
    }

    public void setFinal1(Date final1) {
        this.final1 = final1;
    }

    public long getTotalregistros() {
        return totalregistros;
    }

    public void setTotalregistros(long totalregistros) {
        this.totalregistros = totalregistros;
    }

    public String getNomarchivoOrigen() {
        return nomarchivoOrigen;
    }

    public void setNomarchivoOrigen(String nomarchivoOrigen) {
        this.nomarchivoOrigen = nomarchivoOrigen;
    }

    public String getNomarchivoBak() {
        return nomarchivoBak;
    }

    public void setNomarchivoBak(String nomarchivoBak) {
        this.nomarchivoBak = nomarchivoBak;
    }

    public MktCobhLogEnc getFkIdregistro() {
        return fkIdregistro;
    }

    public void setFkIdregistro(MktCobhLogEnc fkIdregistro) {
        this.fkIdregistro = fkIdregistro;
    }

    public MktCobhLogStatus getFkIdstatus() {
        return fkIdstatus;
    }

    public void setFkIdstatus(MktCobhLogStatus fkIdstatus) {
        this.fkIdstatus = fkIdstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdregistrodet != null ? pkIdregistrodet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktCobhLogDet)) {
            return false;
        }
        MktCobhLogDet other = (MktCobhLogDet) object;
        if ((this.pkIdregistrodet == null && other.pkIdregistrodet != null) || (this.pkIdregistrodet != null && !this.pkIdregistrodet.equals(other.pkIdregistrodet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktCobhLogDet[ pkIdregistrodet=" + pkIdregistrodet + " ]";
    }
    
}
