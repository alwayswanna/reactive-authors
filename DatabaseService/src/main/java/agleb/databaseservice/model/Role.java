package agleb.databaseservice.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_AUTHOR, ROLE_ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
