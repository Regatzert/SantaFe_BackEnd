package com.SantaFe_BackEnd.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 12)
    private String username;
    
    @NotBlank
    private String password;

    @JsonIgnoreProperties({"handler","hibernateLazyInitializer"}) //este decorador ignora los campos "basura" evitando carga de informacion innecesaria, por defecto omitir agregar ambos parametros para que no afecte la carga
    @ManyToMany //por defecto tiene el Fetch de tipo Lazy
    @JoinTable(
        name = "users_roles",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns =  {@JoinColumn(name="role_id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})}
    )
    private List<Role> roles ;

    public User() {
        this.roles = new ArrayList<>();
    }

    

}
