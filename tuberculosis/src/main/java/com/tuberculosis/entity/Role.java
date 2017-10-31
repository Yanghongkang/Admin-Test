package com.tuberculosis.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author: Li Shaoqing
 */
@Entity
@Table(name = "t_role")
public class Role extends IdEntity{

    public Role() {
    }

    public Role(Long id){
        this.id = id;
    }

    private String name;
    private String permissions;
    private List<Auths> authList = Lists.newArrayList();

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public List<String> getPermissionList() {
        if(permissions != null){
            return ImmutableList.copyOf(StringUtils.split(permissions, ","));
        }
        return new ArrayList<String>();
    }

    @Transient
    public List<Auths> getAuthList() {
        return authList;
    }

}
