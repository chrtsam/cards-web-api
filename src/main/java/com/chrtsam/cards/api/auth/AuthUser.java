package com.chrtsam.cards.api.auth;

import com.chrtsam.cards.api.model.Role;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Chris
 */
public class AuthUser extends User {

    private Integer id;
    private Role userRole;

    public AuthUser(Integer id, Role userRole, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public Role getUserRole() {
        return userRole;
    }

    public boolean isAdmin() {
        return userRole.equals(Role.ADMIN);
    }

}
