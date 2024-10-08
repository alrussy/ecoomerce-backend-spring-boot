package com.alrussy.idantityservice.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	

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
	
	
	
	public static String generateTokin(UserDetails user) {
		return generateTokin(new HashMap<>(), user);
	}
	
	public static String generateTokin(Map<String,Object> claims,UserDetails user) {
		
		if(user.getAuthorities()!=null)
				
		return Jwts.builder()
				.subject(user.getUsername())
				.claims(claims)
				.claim("authorities",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
				.signWith(getSigninkey())
				.compact();
		else throw new RuntimeException("This User Not Have Roles : plaese register agein");
	}
	public static Boolean isValid(String token,UserDetails user) {
		String userName=extractUserName(token);
		return userName.equals(user.getUsername())&&!isTokenExpired(token);
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
