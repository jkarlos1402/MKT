package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_CAT_ROL")
public class MktAdmin_CatRol implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_ROL")
    @SequenceGenerator(name = "MKT_SEQ_ROL", sequenceName = "MKT_SEQ_ROL", allocationSize = 1)
    @Column(name = "PK_ROL")
    private Integer pkRol;

    @Column(name = "ROL")
    private String rol;
    
    @JoinTable(name = "MKT_ROL_MENU", joinColumns = {
        @JoinColumn(name = "PK_ROL", referencedColumnName = "PK_ROL")}, inverseJoinColumns = {
        @JoinColumn(name = "IDMENU", referencedColumnName = "IDMENU")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<MktCatMenu> menuList;
        
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROYECTO")
    private MktAdmin_CatProyecto idProyecto;

    /**
     *
     * @return
     */
    public Integer getPkRol() {
        return pkRol;
    }

    /**
     *
     * @param pkRol
     */
    public void setPkRol(Integer pkRol) {
        this.pkRol = pkRol;
    }

    /**
     *
     * @return
     */
    public String getRol() {
        return rol;
    }

    /**
     *
     * @param rol
     */
    public void setRol(String rol) {
        this.rol = rol != null ? rol.toUpperCase() : rol;
    }

    public List<MktCatMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MktCatMenu> menuList) {
        this.menuList = menuList;
    }

    public MktAdmin_CatProyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(MktAdmin_CatProyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkRol != null ? pkRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktAdmin_CatRol)) {
            return false;
        }
        MktAdmin_CatRol other = (MktAdmin_CatRol) object;
        if ((this.pkRol == null && other.pkRol != null) || (this.pkRol != null && !this.pkRol.equals(other.pkRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return rol;
    }

}
