package com.app.projectdelivery.model;

import com.app.projectdelivery.jpa.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "users" )
@Getter
@Setter
public class UserModel extends BaseModel
{
    private String username;
    private String email;
    private String password;
    private String address;
}
