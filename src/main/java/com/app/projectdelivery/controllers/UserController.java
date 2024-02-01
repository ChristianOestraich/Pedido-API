package com.app.projectdelivery.controllers;

import com.app.projectdelivery.model.UserModel;
import com.app.projectdelivery.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/user" )
public class UserController
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController( UserRepository userRepository, PasswordEncoder passwordEncoder )
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/listAll")
    public List<UserModel> findAll()
    {
        return userRepository.findAll();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserModel> findOne(@PathVariable long id )
    {
        return userRepository.findById( id )
                .map( ResponseEntity::ok )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping( "/save" )
    public ResponseEntity<UserModel> save( @RequestBody @Valid UserModel user )
    {
        return saveUser( user );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> remove( @PathVariable Long id )
    {
        return userRepository.findById( id )
                .map( userModel ->
                {
                    userRepository.deleteById( id );
                    return ResponseEntity.ok().build();
                } )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping( "/edit" )
    public ResponseEntity<UserModel> editPost( @RequestBody @Valid UserModel user )
    {
        return saveUser( user );
    }

    @GetMapping( "/validatePassword" )
    public ResponseEntity<Boolean> validatePassword( @RequestParam String userName,
                                                     @RequestParam String password )
    {
        Optional<UserModel> optUser = userRepository.findByUsername( userName );
        if ( optUser.toString().isEmpty() )
        {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( false );
        }

        UserModel user = optUser.get();
        boolean valid = passwordEncoder.matches( password, user.getPassword() );

        HttpStatus status = ( valid ) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status( status ).body( valid );
    }

    private ResponseEntity<UserModel> saveUser( UserModel user )
    {
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        return ResponseEntity.ok( userRepository.save( user ) );
    }
}