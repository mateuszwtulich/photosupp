package com.wtulich.photosupp.userhandling.user.dataaccess.api.entity;

import com.sun.istack.NotNull;
import com.wtulich.photosupp.general.dataaccess.api.entity.AbstractApplicationPersistenceEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class UserEntity extends AbstractApplicationPersistenceEntity {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false, referencedColumnName = "id", unique = true)
    private AccountEntity account;

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

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return name.equals(that.name) &&
                surname.equals(that.surname) &&
                account.equals(that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, account);
    }
}
