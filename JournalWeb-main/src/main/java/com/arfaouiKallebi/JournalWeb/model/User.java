package com.arfaouiKallebi.JournalWeb.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "email")
    private String email ;

    @Column(name = "password")
    private String password ;

    @ManyToMany(fetch = FetchType.EAGER  , cascade = CascadeType.PERSIST)
    List <Role> roles ;


    public User (String email , String password , List<Role> roles) {
      this.email= email ;
      this.password=password ;
      this.roles=roles ;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
