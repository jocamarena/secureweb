package com.example.secureweb.model.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "system_user")
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    private  String username;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "system_user_authority",
            joinColumns = @JoinColumn(name = "system_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "system_authority_id", referencedColumnName = "id"))
    private  Set<SystemAuthority> authorities;
    private  boolean accountNonExpired = true;
    private  boolean accountNonLocked = true;
    private  boolean credentialsNonExpired = true;
    private  boolean enabled = true;
}
