package pl.ething.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ApplicationConfig extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        //config.projectionConfiguration().addProjection(PersonProjection.class);
        config.setReturnBodyOnCreate(true);
    }

    @Bean()
    public ServletContextTemplateResolver templateResolver() {
        final ServletContextTemplateResolver resolver
                = new ServletContextTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
}
