package com.tuberculosis.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author: Li Shaoqing
 */
@Entity
@Table(name = "t_auth")
public class Auths extends IdEntity{

    private String permission;
    private String name;

    public Auths(){}

    public Auths(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}
