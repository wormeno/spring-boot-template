package service.rest.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import service.rest.exception.GenericException;
import service.rest.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtToken jwtToken;


    @Autowired
    private UserService userService;

    private UserDetails userAuthenticated;
    private String token;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try{
            setToken(request);
            setUserAuthenticated();
            setAuthorization(request);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            logger.warn(ex.getMessage(), ex);
        }

        filterChain.doFilter(request, httpServletResponse);
    }

    private void setAuthorization(HttpServletRequest request){
        if (  SecurityContextHolder.getContext().getAuthentication() == null) {

            UsernamePasswordAuthenticationToken authorizationUser =
                    new UsernamePasswordAuthenticationToken(  userAuthenticated, null, userAuthenticated.getAuthorities());

            authorizationUser.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
            SecurityContextHolder.getContext().setAuthentication(authorizationUser);
        }
    }

    private void setToken(HttpServletRequest request){
        final String requestTokenHeader = request.getHeader("Authorization");

        if ( requestTokenHeader == null  || !requestTokenHeader.startsWith("Bearer "))
            throw new GenericException("JWT Token does not begin with Bearer String");

        token = request.getHeader("Authorization").substring(7);
    }

    /**
     * Valida:
     *      Token: firma, expiración
     *      Usuario sea válido en el sistema
     * @return el usuario informado en el token
     */
    private void setUserAuthenticated(){
        String username = jwtToken.getUserToken(token);
        service.rest.model.User user = userService.getUserEnable(username);

        if( user == null )
            throw new GenericException("Username not found");

        userAuthenticated = new User(user.getUsuario(),"",new ArrayList<>());
    }



}
