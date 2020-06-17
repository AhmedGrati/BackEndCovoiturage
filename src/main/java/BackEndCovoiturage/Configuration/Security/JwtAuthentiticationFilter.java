package BackEndCovoiturage.Configuration.Security;

import BackEndCovoiturage.Model.LoginViewModel;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthentiticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthentiticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /* Trigger when we issue POST request to /login
    We also need to pass an existing user in the request body
    to ensure the authentication
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // Grab credentials and map them to login viewmodel
        LoginViewModel credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
            System.out.println("credentials : "+credentials.getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getEmail(),
                credentials.getPassword(),
                new ArrayList<>());

        // Authenticate user
        Authentication auth = authenticationManager. authenticate(authenticationToken);

        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Grab principal
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtPropreties.EXPIRATION_TIME))
                .sign(HMAC512(JwtPropreties.SECRET.getBytes()));

        // Add token in response
        response.addHeader(JwtPropreties.HEADER_STRING, JwtPropreties.TOKEN_PREFIX +  token);

        JSONObject jsonObject = new JSONObject();
        JSONObject user = new JSONObject();
        System.out.println(principal.getUser());
        user.put("email",principal.getUser().getEmail());
        user.put("firstName",principal.getUser().getFirstName());
        user.put("lastName",principal.getUser().getLastName());
        user.put("id",principal.getUser().getId());
        user.put("imageUrl",principal.getUser().getImageUrl());
        user.put("inscriptionDate",principal.getUser().getInscriptionDate().toString());
        user.put("age",principal.getUser().getAge());
        user.put("avis",principal.getUser().getAvis());
        user.put("lastDateEntered",principal.getUser().getLastDateEnetered());
        user.put("gender",principal.getUser().getGender());
        user.put("status",principal.getUser().getStatus());
        user.put("localisation",principal.getUser().getLocalisation());
        user.put("numTel",principal.getUser().getNumTel());


        jsonObject.put("user",user);
        jsonObject.put("token",token);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(jsonObject);
        response.getWriter().flush();
    }
}
