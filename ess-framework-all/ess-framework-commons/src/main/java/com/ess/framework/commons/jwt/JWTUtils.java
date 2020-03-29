package com.ess.framework.commons.jwt;

import com.alibaba.fastjson.JSON;
import com.ess.framework.commons.utils.ExceptionUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;

import java.security.KeyPair;
import java.util.Date;

/**
 * JWT工具类
 */
public class JWTUtils {

    private static JWTUtils instance = null;

    private static KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    /**
     * JWTUtils 单例模式
     */
    public static JWTUtils getInstance(){
        if(instance == null){
            instance = new JWTUtils();
        }
        return instance;
    }


    /**
     * 获取token
     * @return
     */
    public String getToken(Date expirationTime){

        AuthUser authUser = new AuthUser();
        authUser.setUserId("100001");
        authUser.setUserName("zhangsan");
        authUser.setType("1");
        String  token = Jwts.builder().setSubject(JSON.toJSONString(authUser))
                .setExpiration(expirationTime)
                .signWith(keyPair.getPrivate()).compact();
        return token;
    }



    public void checkToken(String token){
        try {
            String body = Jwts.parserBuilder().setSigningKey(keyPair.getPublic()).build().parseClaimsJws(token).getBody().getSubject();
            System.out.println(body);
        }catch (ExpiredJwtException e){
            ExceptionUtils.throwBusiness(1601,"Token已过期");
        }catch (Exception e){
            ExceptionUtils.throwBusiness(1601,"非法请求");
        }
    }

}
