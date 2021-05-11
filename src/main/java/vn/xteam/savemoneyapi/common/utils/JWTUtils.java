package vn.xteam.savemoneyapi.common.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.xteam.savemoneyapi.entities.v1.UserEntity;

import java.util.Date;

public class JWTUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtils.class.getName());

    private static final String JWT_SECRET = "saving_money_book_api_secret";
    private static final long JWT_EXPIRATION = 604800000L;

    public static String generateToken(UserEntity userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    public static String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.");
        }
        return false;
    }

//    public static void main(String[] args) {
//        UserEntity user = UserEntity.builder()
//                .lastName("tuan")
//                .id(2)
//                .email("tuan.nguyen15@tiki.vn")
//                .build();
//        String token = generateToken(user);
//        String userId = getUserIdFromJWT(token);
//        System.out.println(userId);
//    }
}
