package in.hocg.zeus.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by hocgin on 2020/12/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class JwtUtils {
    /**
     * 10年
     */
    private static final long EXPIRE_MILLIS = 10 * 365 * 24 * 60 * 60 * 1000L;
    private static final String KEY = "Zeus";

    /**
     * 编码 Token
     *
     * @param username
     * @return
     */
    public String encode(String username) {
        final long currentTimeMillis = System.currentTimeMillis();
        final long expirationTimeMillis = currentTimeMillis + EXPIRE_MILLIS;
        return Jwts.builder().setClaims(new HashMap<>()).setSubject(username)
            .setIssuedAt(new Date(currentTimeMillis))
            .setExpiration(new Date(expirationTimeMillis))
            .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    /**
     * 解码 Token
     *
     * @param token
     * @return
     */
    public String decode(String token) {
        return Jwts.parser().setSigningKey(KEY)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
