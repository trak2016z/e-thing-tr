package pl.ething.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import pl.ething.projection.PersonProjection;

@Configuration
public class ApplicationConfig extends RepositoryRestMvcConfiguration {

	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		super.configureRepositoryRestConfiguration(config);
		config.projectionConfiguration().addProjection(PersonProjection.class);
		config.setReturnBodyOnCreate(true);
	}

}