package com.app.projectdelivery.security;

import com.app.projectdelivery.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class AppUserDetails implements UserDetails
{
    private final Optional<UserModel> users;

    public AppUserDetails( Optional<UserModel> users )
    {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return new ArrayList<>();
    }

    @Override
    public String getPassword()
    {
        return users.orElse( new UserModel() ).getPassword();
    }

    @Override
    public String getUsername()
    {
        return users.orElse( new UserModel() ).getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
