package agleb.databaseservice.model.dto;

import org.springframework.security.core.GrantedAuthority;

public enum RoleDTO implements GrantedAuthority {

    ROLE_AUTHOR, ROLE_ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
