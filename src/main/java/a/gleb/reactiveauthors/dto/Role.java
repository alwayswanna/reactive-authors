package a.gleb.reactiveauthors.dto;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    AUTHOR, ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
