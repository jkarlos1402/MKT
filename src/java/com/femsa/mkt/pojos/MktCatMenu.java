/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_CAT_MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCatMenu.findAll", query = "SELECT m FROM MktCatMenu m")})
public class MktCatMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "IDEMPRESA")
    private BigInteger idempresa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SQ_MENU")
    @SequenceGenerator(name = "MKT_SQ_MENU", sequenceName = "MKT_SQ_MENU", allocationSize = 1)
    @Column(name = "IDMENU")
    private Integer idmenu;
    @Column(name = "NOMBRE")
    private String nombre;

    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "IDPADRE",insertable = false,updatable = false)
    private MktCatMenu idpadre;

    @OneToMany(mappedBy = "idpadre",fetch = FetchType.EAGER)
    private List<MktCatMenu> menusHijo;

    @Column(name = "IDORDEN")
    private BigInteger idorden;
    @Column(name = "URL")
    private String url;
    @Column(name = "IDSTATUS")
    private Short idstatus;

    public MktCatMenu() {
    }

    public MktCatMenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public BigInteger getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(BigInteger idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MktCatMenu getIdpadre() {
        return idpadre;
    }

    public void setIdpadre(MktCatMenu idpadre) {
        this.idpadre = idpadre;
    }

    public BigInteger getIdorden() {
        return idorden;
    }

    public void setIdorden(BigInteger idorden) {
        this.idorden = idorden;
    }

    public List<MktCatMenu> getMenusHijo() {
        return menusHijo;
    }

    public void setMenusHijo(List<MktCatMenu> menusHijo) {
        this.menusHijo = menusHijo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Short getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(Short idstatus) {
        this.idstatus = idstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktCatMenu)) {
            return false;
        }
        MktCatMenu other = (MktCatMenu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCatMenu{" + "idempresa=" + idempresa + ", idmenu=" + idmenu + ", nombre=" + nombre + ", idorden=" + idorden + ", url=" + url + ", idstatus=" + idstatus + '}';
    }   

}
