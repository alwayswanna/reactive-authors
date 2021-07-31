package a.gleb.reactiverest.models;

import org.springframework.security.core.GrantedAuthority;

public enum AccountRole implements GrantedAuthority {

    AUTHOR, ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
