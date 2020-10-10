package com.wtulich.photosupp.userhandling.logic.api.to;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationEntityTransportObject;

import java.util.Objects;

public class UserEto extends AbstractApplicationEntityTransportObject {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private AccountEto accountEto;

    @NotNull
    private RoleEto roleEto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public AccountEto getAccountEto() {
        return accountEto;
    }

    public void setAccountEto(AccountEto accountEto) {
        this.accountEto = accountEto;
    }

    public RoleEto getRoleEto() {
        return roleEto;
    }

    public void setRoleEto(RoleEto roleEto) {
        this.roleEto = roleEto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEto)) return false;
        if (!super.equals(o)) return false;
        UserEto userEto = (UserEto) o;
        return name.equals(userEto.name) &&
                surname.equals(userEto.surname) &&
                accountEto.equals(userEto.accountEto) &&
                roleEto.equals(userEto.roleEto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, accountEto, roleEto);
    }
}
