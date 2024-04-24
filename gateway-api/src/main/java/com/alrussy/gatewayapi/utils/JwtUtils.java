package com.alrussy.gatewayapi.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;


import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils{
	

	 private static String secret="efa172fa81073a55c4bfcdfd40a4e31c1d1f24b63a0f92752d90f51c4c283345";

	public static String extractUserName(String token) {
		return exteractClaim(token,Claims::getSubject);
	}
	public static ArrayList extractAuthoriteis(String token) {
		return exteractClaim(token,claim ->claim.get("authorities",ArrayList.class));
	}

	private static <T> T exteractClaim(String token, Function<Claims, T> claimResolve) {
		final Claims claims= extractAllClaims(token);
		return claimResolve.apply(claims) ;
	}

	
	private static Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSigninkey())
				.build().parseSignedClaims(token)
				.getPayload();
	}
	
		
	public static Boolean isValid(String token) {
		return !isTokenExpired(token);
	}

	private static boolean isTokenExpired(String token) {
		return exteractExpiration(token).before(new Date());
	}

	private static Date exteractExpiration(String token) {
		return exteractClaim(token, Claims::getExpiration);
	}

	private static SecretKey getSigninkey() {
		byte[] keyBytes=Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	
	
	

	
	
	
}
