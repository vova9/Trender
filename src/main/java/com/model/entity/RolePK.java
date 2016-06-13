/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Vladimir.Avtushko
 */
@Embeddable
public class RolePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "role_id")
    private long roleId;
    @Basic(optional = false)
    @Column(name = "user_user_id")
    private long userUserId;

    public RolePK() {
    }

    public RolePK(long roleId, long userUserId) {
        this.roleId = roleId;
        this.userUserId = userUserId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(long userUserId) {
        this.userUserId = userUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) roleId;
        hash += (int) userUserId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolePK)) {
            return false;
        }
        RolePK other = (RolePK) object;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.userUserId != other.userUserId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.trender.model.entity.RolePK[ roleId=" + roleId + ", userUserId=" + userUserId + " ]";
    }
    
}
