package com.ess.example.demo;

import com.alibaba.fastjson.JSON;
import com.ess.example.goods.auth.AuthUser;
import com.ess.framework.commons.utils.ExceptionUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.security.KeyPair;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	private static KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
//	private static KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

	/**
	 * 获取token
	 * @return
	 */
	public static String getToken(){
		Date expirationTime = DateUtils.addHours(new Date(),1);
		AuthUser authUser = new AuthUser();
		authUser.setUserId("100001");
		authUser.setUserName("zhangsan");
		authUser.setType("1");
		String  token = Jwts.builder().setSubject(JSON.toJSONString(authUser))
				.setExpiration(expirationTime)
				.signWith(keyPair.getPrivate()).compact();
		return token;
	}



	public static void checkToken(String token){
		try {
			String body = Jwts.parserBuilder().setSigningKey(keyPair.getPublic()).build().parseClaimsJws(token).getBody().getSubject();
			System.out.println(body);
		}catch (ExpiredJwtException e){
			ExceptionUtils.throwBusiness(1601,"Token已过期");
		}catch (SignatureException e){
			ExceptionUtils.throwBusiness(1601,"非法请求");
		}catch (Exception e){
			ExceptionUtils.throwBusiness(1601,"非法请求");
		}
	}
	public static void main(String[] args) {
		String token = getToken();
		System.out.println(token);
		checkToken(token);
	}

}
