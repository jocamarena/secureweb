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
@Table(name = "system_authority")
public class SystemAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authority;
    @ManyToMany(mappedBy = "authorities")
    private Set<SystemUser> users;
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
