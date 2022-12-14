package ru.gb.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.services.JwtService;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authorize(@RequestBody AuthRequest request) {
        Map<String, Object> headerMap = new HashMap<String, Object>();
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            UserDetails user = (UserDetails) authenticate.getPrincipal();
            String jwtToken = jwtService.generateJwtToken(user);
            headerMap.put("token", jwtToken);
            headerMap.put("user", user);
            return new ResponseEntity<Map<String, Object>>(headerMap, HttpStatus.OK);

        }catch (ArithmeticException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

    }

}