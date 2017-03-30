package org.jsche.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
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
        container.setPort(8000);

    }

}
