package com.example.demopassetolibrary;

import lombok.SneakyThrows;
import net.aholbrook.paseto.meta.PasetoBuilders;
import net.aholbrook.paseto.service.LocalTokenService;
import net.aholbrook.paseto.service.Token;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.util.Collections;

@Service
public class TokenService {

    private final LocalTokenService<Token> localTokenService;

    @SneakyThrows
    public TokenService() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(Collections.singletonList("azerty7896325"));
        byte[] key = bos.toByteArray();

        localTokenService = PasetoBuilders.V1.localService(() -> key, Token.class)
                .withDefaultValidityPeriod(Duration.ofDays(15).getSeconds())
                .build();
    }

    public String getTokenForSource(String source) {

        Token token = new Token();
        token.setTokenId(source); // A session key, user id, etc.

        return localTokenService.encode(token);
    }

    public String decodeToken(String token) {

        Token decoded = localTokenService.decode(token);
        return decoded.getTokenId();
    }
}
