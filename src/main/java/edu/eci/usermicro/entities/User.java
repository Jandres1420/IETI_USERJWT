package edu.eci.usermicro.entities;

import edu.eci.usermicro.dto.LoginDto;
import edu.eci.usermicro.dto.UserDto;
import edu.eci.usermicro.entities.enumerating.RoleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

@Document
public class
User {
    @Id
    String id;
    String name, lastName, createdAt;
    @Indexed(unique = true)
    String email;
    private String passwordHash;
    private List<RoleEnum> roles;


    public User(String id, String name, String email, String lastName, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }
    public User(UserDto userDTO) {
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.lastName = userDTO.getLastName();
        this.createdAt = userDTO.getCreatedAt();
        System.out.println("Estamos en costructor de User ");
        System.out.println("Contraseña " + userDTO.getPassword());
        passwordHash = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
    }
    public User(){}

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }


}
