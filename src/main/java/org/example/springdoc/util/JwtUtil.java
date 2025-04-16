package org.example.springdoc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JwtUtil {
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;//有效期24小时

    public static boolean verify(String token, String userName, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userName", userName).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String sign(String userName, String password) throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(password);
        return JWT.create().withClaim("userName", userName).withExpiresAt(date).sign(algorithm);
    }

    public static boolean isExpire(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expiresAt = jwt.getExpiresAt();
            long currentTimeMillis = System.currentTimeMillis();
            long expiresAtTime = expiresAt.getTime();
            if (expiresAtTime > currentTimeMillis) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
