package agleb.databaseservice.model;

import agleb.databaseservice.model.dto.RoleDTO;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    AUTHOR, ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
