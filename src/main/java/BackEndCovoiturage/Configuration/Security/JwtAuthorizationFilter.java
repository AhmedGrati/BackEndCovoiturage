package BackEndCovoiturage.Configuration.Security;

import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Repository.UserRepo;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepo userRepo;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header, where the JWT token should be
        String header = request.getHeader(JwtPropreties.HEADER_STRING);

        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(JwtPropreties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("the header is not null");
        // If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(new WebAuthenticationDetailsSource().buildDetails(request),request);
        System.out.println(authentication);
        // Continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(WebAuthenticationDetails authenticationDetails ,HttpServletRequest request) {
        String token = request.getHeader(JwtPropreties.HEADER_STRING)
                .replace(JwtPropreties.TOKEN_PREFIX,"");

        if (token != null) {
            // parse the token and validate it
            String userName = JWT.require(HMAC512(JwtPropreties.SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();

            // Search in the DB if we find the user by token subject (username)
            // If so, then grab user details and create spring auth token using username, pass, authorities/roles
            if (userName != null) {
                User user = userRepo.findUserByEmail(userName);
                UserPrincipal principal = new UserPrincipal(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, principal.getAuthorities());
                auth.setDetails(authenticationDetails);
                SecurityContextHolder.getContext().setAuthentication(auth);
                return auth;
            }
            return null;
        }
        return null;
    }
}
