package service.rest.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtToken {

//    @Value("${jwt.token.secret}")
    private String secret="Api-Snic";

//    @Value("${jwt.token.timeValidation}")
    private Long timeValidation=9800L;

//    @Value("${jwt.token.nameApplication}")
    private String nameApplication="Api_Snic";

    public String generateToken(String username,Long userNameId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userNameNumber", userNameId);
       return
         Jwts.builder()
                .setClaims(claims)
                .setId(nameApplication)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + timeValidation*1000))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();

    }

    /**
     * Al obtener el usuario del token se está validando el token
     * @param token token
     * @return username
     */
    public String getUserToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)//Valida el token
                .getBody();
    }

}
