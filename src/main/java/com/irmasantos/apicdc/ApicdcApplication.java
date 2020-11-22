package com.irmasantos.apicdc;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//by default cada metodo repository é transactional para definir por método de negócio têm desabilitar o default
@EnableJpaRepositories(enableDefaultTransactions=false)
@EnableAsync
public class ApicdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicdcApplication.class, args);
	}

	//não deu-me erro no adiocionar da cookie ao response mas eu adicionei igual video respectivo
	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
	    return (factory) -> factory
	            .addContextCustomizers((context) -> context.setCookieProcessor(new LegacyCookieProcessor()));
	}	

}
