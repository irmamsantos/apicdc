package com.irmasantos.apicdc.shared;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irmasantos.apicdc.site.detalhe.Carrinho;

@Component
public class Cookies {

	/**
	 * @param key chave que vai ser gerada para o cookie
	 * @param carrinho carrinho de compras que vai ser serializado
	 * @param response
	 */
	public void writeAsJson(String key, Carrinho carrinho, HttpServletResponse response) {
		Cookie cookie;
		try {
			cookie = new Cookie(key, new ObjectMapper().writeValueAsString(carrinho));
			cookie.setHttpOnly(true);
			//para o cookie ser valido para o site inteiro senao definisse esta propriedade só era válido para o url associado ao request
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
