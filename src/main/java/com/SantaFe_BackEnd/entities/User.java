package com.SantaFe_BackEnd.entities;

import java.util.ArrayList;
import java.util.List;

import com.SantaFe_BackEnd.models.IUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
public class User implements IUser{
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

    @Transient //significa que no es parte de la tabla, no es propio o obligatorio de la clase
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean admin;
    
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

    @Override
    public boolean isAdmin() {
        return admin; 
    }

    

}
