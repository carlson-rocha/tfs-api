package com.rocha.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.rocha.config.property.TokenApiProperty;


@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{

	@Autowired
	private TokenApiProperty tokenApiProperty;
	
	@Override
	public boolean supports(MethodParameter returnType, 
							Class<? extends HttpMessageConverter<?>> convertType) {

		return returnType.getMethod().getName().equals("postAccessToken");
	}
	
	
	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken 	body, 
											 MethodParameter 	returnType, 
											 MediaType 			selectedContentType,
											 Class<? extends HttpMessageConverter<?>> selectedConverterType, 
											 ServerHttpRequest 	request, 
											 ServerHttpResponse response) {
		
		HttpServletRequest   req  = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse  resp = ((ServletServerHttpResponse) response).getServletResponse();
		
		DefaultOAuth2AccessToken	token = (DefaultOAuth2AccessToken) body;
		
		String refreshToken = body.getRefreshToken().getValue();
		
		adicionarRefreshTokenCookie(refreshToken , req , resp);
		
		removeRefreshTokenBody(token);
		
		return body;
	}
 
	
	//----- Remover o Refresh Token da Token para Segurança -----------//
	private void removeRefreshTokenBody(DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
		
	}

	//----- Adicionar o Refresh Token em um Cookie HTTPS para Segurança -----------//
	private void adicionarRefreshTokenCookie(String 				refreshToken, 
											 HttpServletRequest 	req, 
											 HttpServletResponse 	resp) {
		
		Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
				
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(tokenApiProperty.getSeguranca().isEnableHttps());  // Mudar par\ true em Produção para funcionar em HTTPS
		refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
		refreshTokenCookie.setMaxAge(2592000);
		
		resp.addCookie(refreshTokenCookie);
		
				
	}
	
}
