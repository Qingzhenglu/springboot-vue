package com.blog.springbootvue.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

import static io.jsonwebtoken.Jwts.SIG.HS256;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "blog.jwt")
public class JwtUtils {
    private String secret;
    private long expire;
    private String header;

    /*
    * 生成jwt token
    * */
    public String generateToken(long userId){
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .header()
                .add("typ","JWT")
                .and()
                .subject(userId+"")
                .issuedAt(nowDate)
                .expiration(expireDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()),HS256)
                .compact();
    }

    public Claims checkToken(String token) {
        String msg = null;
        try {
                return Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
        } catch (Exception e) {
            log.debug("validate is token error ", e);
            return null;
        }
    }

     /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

}
