package vip.bblog.cunadmin.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import vip.bblog.cunadmin.common.entity.CustomToken;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2019年11月05日 12:53
 * @desc JwtUtils jwt工具
 */
@Slf4j
public class JwtUtils {

    private static final String SECRET = "w&5pWMzjaW!pikv5R5Ys@2KIJ0rMMy$q";

    private static final String ISSUER = "vip.bblog";

    public static CustomToken createToken(String userName, String uuid, Long expireSeconds) {
        HashMap<String, Object> value = new HashMap<>(1);
        value.put("token", uuid);
        value.put("username", userName);
        SignatureAlgorithm signature = SignatureAlgorithm.HS256;
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key secretKey = new SecretKeySpec(secretBytes, signature.getJcaName());
        long expiration = System.currentTimeMillis() + (expireSeconds) * 1000;
        Date expirationDate = new Date(expiration);
        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .setIssuer(ISSUER)
                .setClaims(value)
                .signWith(signature, secretKey).compact();
        CustomToken customToken = new CustomToken();
        customToken.setAccess_token(token);
        customToken.setExpires_in(expirationDate);
        customToken.setLogin_in(new Date());
        return customToken;
    }

    /**
     * 从jwt中获取uuid
     */
    public static String getuuid(String jwt) {
        try {
            if (StringUtils.isBlank(jwt)) {
                return null;
            }
            Map<String, Object> jwtClaims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jwt).getBody();
            return String.format("%s:%s", jwtClaims.get("username").toString(), jwtClaims.get("token").toString());
        } catch (ExpiredJwtException e) {
            log.warn("凭证已过期:{}", jwt);
            return null;
        } catch (Exception e) {
            log.warn("无效的凭证:{}", jwt);
            return null;
        }
    }
}
