package com.example.kiemtra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleName;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    public Role (String roleName, Set<User> users)
    {
        this.roleName = roleName;
        this.users = users;
    }

}
