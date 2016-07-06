/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_COBH_LOG_ENC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhLogEnc.findAll", query = "SELECT m FROM MktCobhLogEnc m")})
public class MktCobhLogEnc implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_LOG_ENC")
    @SequenceGenerator(name = "MKT_COBH_SEQ_LOG_ENC", sequenceName = "MKT_COBH_SEQ_LOG_ENC", allocationSize = 1)
    @Column(name = "PK_IDREGISTRO")
    private Integer pkIdregistro;
    @Column(name = "NOMBRE_PROCESO")
    private String nombreProceso;
    @Column(name = "INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Column(name = "FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date final1;
    @Column(name = "TIEMPO")
    private Integer tiempo;
    @Column(name = "TOTALREGISTROS")
    private long totalregistros;
    @Column(name = "TOTALPROCESADOS")
    private long totalprocesados;
    @Column(name = "TOTALERROR")
    private long totalerror;
    @Column(name = "TOTALST")
    private long totalst;
    @Column(name = "TOTALAGREGADOS")
    private long totalagregados;
    @Column(name = "TOTALACTUALIZADOS")
    private long totalactualizados;
    @Column(name = "TOTALELIMINADOS")
    private long totaleliminados;
    @Column(name = "NOMARCHIVO_ORIGEN")
    private String nomarchivoOrigen;
    @Column(name = "NOMBARCHIVO_BAK")
    private String nombarchivoBak;
    @Column(name = "TOTALMENSAJES")
    private Integer totalmensajes;
    @Column(name = "TOTALARCHIVOS")
    private Integer totalarchivos;
    @JoinColumn(name = "FK_PROYECTO", referencedColumnName = "ID_PROYECTO")
    @ManyToOne
    private MktAdmin_CatProyecto fkProyecto;
    @JoinColumn(name = "FK_USUARIO", referencedColumnName = "PK_USUARIO")
    @ManyToOne
    private MktAdmin_Usuario fkUsuario;
    @JoinColumn(name = "FK_IDSTATUS", referencedColumnName = "PK_IDSTATUS")
    @ManyToOne
    private MktCobhLogStatus fkIdstatus;
    @OneToMany(mappedBy = "fkIdregistro", cascade = CascadeType.ALL)
    private List<MktCobhLogDet> mktCobhLogDetList;

    public MktCobhLogEnc() {
    }

    public MktCobhLogEnc(Integer pkIdregistro) {
        this.pkIdregistro = pkIdregistro;
    }

    public Integer getTotalarchivos() {
        return totalarchivos;
    }

    public void setTotalarchivos(Integer totalarchivos) {
        this.totalarchivos = totalarchivos;
    }

    public Integer getPkIdregistro() {
        return pkIdregistro;
    }

    public void setPkIdregistro(Integer pkIdregistro) {
        this.pkIdregistro = pkIdregistro;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
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

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public long getTotalregistros() {
        return totalregistros;
    }

    public void setTotalregistros(long totalregistros) {
        this.totalregistros = totalregistros;
    }

    public long getTotalprocesados() {
        return totalprocesados;
    }

    public void setTotalprocesados(long totalprocesados) {
        this.totalprocesados = totalprocesados;
    }

    public long getTotalerror() {
        return totalerror;
    }

    public void setTotalerror(long totalerror) {
        this.totalerror = totalerror;
    }

    public long getTotalst() {
        return totalst;
    }

    public void setTotalst(long totalst) {
        this.totalst = totalst;
    }

    public long getTotalagregados() {
        return totalagregados;
    }

    public void setTotalagregados(long totalagregados) {
        this.totalagregados = totalagregados;
    }

    public long getTotalactualizados() {
        return totalactualizados;
    }

    public void setTotalactualizados(long totalactualizados) {
        this.totalactualizados = totalactualizados;
    }

    public long getTotaleliminados() {
        return totaleliminados;
    }

    public void setTotaleliminados(Integer totaleliminados) {
        this.totaleliminados = totaleliminados;
    }

    public String getNomarchivoOrigen() {
        return nomarchivoOrigen;
    }

    public void setNomarchivoOrigen(String nomarchivoOrigen) {
        this.nomarchivoOrigen = nomarchivoOrigen;
    }

    public String getNombarchivoBak() {
        return nombarchivoBak;
    }

    public void setNombarchivoBak(String nombarchivoBak) {
        this.nombarchivoBak = nombarchivoBak;
    }

    public Integer getTotalmensajes() {
        return totalmensajes;
    }

    public void setTotalmensajes(Integer totalmensajes) {
        this.totalmensajes = totalmensajes;
    }

    public MktAdmin_CatProyecto getFkProyecto() {
        return fkProyecto;
    }

    public void setFkProyecto(MktAdmin_CatProyecto fkProyecto) {
        this.fkProyecto = fkProyecto;
    }

    public MktAdmin_Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(MktAdmin_Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public MktCobhLogStatus getFkIdstatus() {
        return fkIdstatus;
    }

    public void setFkIdstatus(MktCobhLogStatus fkIdstatus) {
        this.fkIdstatus = fkIdstatus;
    }

    @XmlTransient
    public List<MktCobhLogDet> getMktCobhLogDetList() {
        return mktCobhLogDetList;
    }

    public void setMktCobhLogDetList(List<MktCobhLogDet> mktCobhLogDetList) {
        this.mktCobhLogDetList = mktCobhLogDetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdregistro != null ? pkIdregistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktCobhLogEnc)) {
            return false;
        }
        MktCobhLogEnc other = (MktCobhLogEnc) object;
        if ((this.pkIdregistro == null && other.pkIdregistro != null) || (this.pkIdregistro != null && !this.pkIdregistro.equals(other.pkIdregistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktCobhLogEnc[ pkIdregistro=" + pkIdregistro + " ]";
    }

}
