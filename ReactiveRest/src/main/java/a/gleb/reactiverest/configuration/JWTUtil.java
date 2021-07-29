package a.gleb.reactiverest.configuration;

import a.gleb.reactiverest.models.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String tokenSecret;
    @Value("${jwt.expiration}")
    private String tokenExpirationSeconds;

    public String extractTokenInformation(String authToken) {
        return getClaimsFromToken(authToken)
                .getSubject();
    }

    public Claims getClaimsFromToken(String authToken) {
        String key = Base64.getEncoder().encodeToString(tokenSecret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(authToken)
                .getBody();
    }

    public boolean validation(String authToken) {
        return getClaimsFromToken(authToken)
                .getExpiration()
                .before(new Date());
    }

    public String generateToken(Account account){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", List.of(account.getRoleDTO()));
        long expirationTime = Long.parseLong(tokenExpirationSeconds);
        Date creationDateToken = new Date();
        Date expirationDateToken = new Date(creationDateToken.getTime() + expirationTime * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(account.getUsername())
                .setIssuedAt(creationDateToken)
                .setExpiration(expirationDateToken)
                .signWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .compact();
    }
}
