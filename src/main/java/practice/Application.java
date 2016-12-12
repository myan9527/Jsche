package practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application implements EmbeddedServletContainerCustomizer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class);
	}

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8000);
    }
}
