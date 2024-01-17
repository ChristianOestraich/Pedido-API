package com.app.projectdelivery.services;

import com.app.projectdelivery.security.AppUserDetails;
//import com.app.projectdelivery.objects.User;
import com.app.projectdelivery.model.UserModel;
import com.app.projectdelivery.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailUserServiceImpl implements UserDetailsService
{
    private final UserRepository userRepository;

    public DetailUserServiceImpl( UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
    {
        Optional<UserModel> user = userRepository.findByUsername( username );

        if ( user.toString().isEmpty() )
        {
            throw new UsernameNotFoundException( "User [" + username + "] not found " );
        }

        return new AppUserDetails( user );
    }
}
