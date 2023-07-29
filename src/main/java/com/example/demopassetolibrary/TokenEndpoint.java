package com.example.demopassetolibrary;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class TokenEndpoint {

    private TokenService tokenService;

    @PostMapping("get-token")
    public String getToken(@RequestBody String source) {
        return tokenService.getTokenForSource(source);
    }

    @PostMapping("decode-token")
    public String decodeToken(@RequestBody String token) {
        return tokenService.decodeToken(token);
    }
}
