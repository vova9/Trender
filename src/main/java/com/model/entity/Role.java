/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vladimir.Avtushko
 */
@Entity
@Table(name = "role", catalog = "tender", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE r.rolePK.roleId = :roleId"),
    @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.roleName = :roleName"),
    @NamedQuery(name = "Role.findByUserUserId", query = "SELECT r FROM Role r WHERE r.rolePK.userUserId = :userUserId")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolePK rolePK;
    @Basic(optional = false)
    @Column(name = "role_name")
    private String roleName;
    @JoinColumn(name = "user_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    public Role() {
    }

    public Role(RolePK rolePK) {
        this.rolePK = rolePK;
    }

    public Role(RolePK rolePK, String roleName) {
        this.rolePK = rolePK;
        this.roleName = roleName;
    }

    public Role(long roleId, long userUserId) {
        this.rolePK = new RolePK(roleId, userUserId);
    }

    public RolePK getRolePK() {
        return rolePK;
    }

    public void setRolePK(RolePK rolePK) {
        this.rolePK = rolePK;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolePK != null ? rolePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.rolePK == null && other.rolePK != null) || (this.rolePK != null && !this.rolePK.equals(other.rolePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trender.model.entity.Role[ rolePK=" + rolePK + " ]";
    }
    
}
