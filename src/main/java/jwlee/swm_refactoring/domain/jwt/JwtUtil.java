package jwlee.swm_refactoring.domain.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import jwlee.swm_refactoring.domain.exceptions.ExpiredTokenException;
import jwlee.swm_refactoring.domain.exceptions.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private static final String secretKey = "b2d6f5c79a2b2f1db939f04e3dbd6d9bdb56c07b557073d2a0c24fd4faec0a4f";

    private SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    private static final Long EXPIRATION_TIME_MS = 1000 * 60 * 60 * 24L; // 밀리세컨이라 1000 * 60초 * 60분 * 24시 => 하루
    private static final String USER_NO_KEY_NAME = "no";
    private final String USER_ID_KEY_NAME = "id";

    /**
     * 액세스 토큰생성해주는 메서드
     *   별일 없으면 이걸 사용하세요
     * @param loginUser
     * @return
     */
    public String createAccessToken(final Admin loginUser) {
        return this.createAccessToken(loginUser, EXPIRATION_TIME_MS);
    }

    /**
     * 액세스 토큰생성해주는 메서드 (만료시간을 파라미터로 받는 오버로딩된 메서드)
     *  굳이 만료시간을 다르게 가져가야할 경우만 사용하도록 오버로딩해둠
     *  되도록이면  createAccessToken()를 사용해서 토큰생성바람
     * @param loginUser
     * @param expirationTimeMs
     * @return
     */
    public String createAccessToken(final Admin loginUser, final long expirationTimeMs) {
        String token = Jwts.builder()
                .claim(USER_NO_KEY_NAME, loginUser.getId())
                .claim(USER_ID_KEY_NAME, loginUser.getAdminId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTimeMs))
                .signWith(key)
                .compact();
        log.debug("created token : {} ", token);
        return token;
    }

    /**
     * 액세스 토큰에서 로그인유저정보 꺼내오기
     * @param accessToken
     * @return
     */
    public Admin getLoginUserFromAccessToken(final String accessToken) {
        Claims claims = getClaims(accessToken);

        return Admin.builder()
                .id(claims.get(USER_NO_KEY_NAME, Long.class))
                .adminId(claims.get(USER_ID_KEY_NAME, String.class))
                .build();
    }

    /**
     * 토큰으로부터 클레임 꺼내기 (예외처리를 위해 별도 메서드로 분리시킴)
     * @param accessToken
     * @return
     */
    private Claims getClaims(final String accessToken) {
        Claims claims ;
        try {
            claims = Jwts.parser()
                    .verifyWith(key) // 단순히 key 타입만 검증하더라...
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();
        } catch(ExpiredJwtException eje) { // 만료된 토큰일 경우 발생하는 Exception
            throw new ExpiredTokenException(); // 내가 만든 Exception으로 바꿔서 던짐 -> 리프레시토큰 로직으로 분기되어야함
        } catch(Exception e) { // 기타 나머지(변조되었거나, 형식이 안맞거나 등등등)는 퉁쳐서 비정상 토큰으로 간주
            throw new InvalidTokenException();
        }
        return claims;
    }
}
