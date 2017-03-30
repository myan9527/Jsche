package org.jsche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
        container.setSessionTimeout(45, TimeUnit.MINUTES);
        container.addErrorPages(error404, error500);
        container.setPort(8000);
    }

}
