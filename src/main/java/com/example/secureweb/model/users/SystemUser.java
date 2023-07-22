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
    @Column(unique = true)
    private  String username;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "system_user_authority",
            joinColumns = @JoinColumn(name = "system_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "system_authority_id", referencedColumnName = "id"))
    private  Set<SystemAuthority> authorities;
    @Column(columnDefinition = "boolean default true")
    private  boolean accountNonExpired = true;
    @Column(columnDefinition = "boolean default true")
    private  boolean accountNonLocked = true;
    @Column(columnDefinition = "boolean default true")
    private  boolean credentialsNonExpired = true;
    @Column(columnDefinition = "boolean default true")
    private  boolean enabled = true;
}
