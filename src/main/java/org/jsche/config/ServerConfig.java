package org.jsche.config;

import io.undertow.UndertowOptions;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/3/30.
 */
@Configuration
public class ServerConfig implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        ErrorPage error403 = new ErrorPage(HttpStatus.FORBIDDEN, "/403.html");
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
        container.setSessionTimeout(45, TimeUnit.MINUTES);
        container.addErrorPages(error404, error403, error500);
    }

    @Bean
    @ConfigurationProperties(prefix = "server.undertow")
    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        UndertowEmbeddedServletContainerFactory undertow = new UndertowEmbeddedServletContainerFactory();
        undertow.addBuilderCustomizers(builder -> {
            builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
//            builder.addHttpsListener(8443,"local")
        });

        return undertow;
    }
}
