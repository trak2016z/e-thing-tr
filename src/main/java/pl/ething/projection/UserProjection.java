package pl.ething.projection;

import org.springframework.data.rest.core.config.Projection;

import pl.ething.model.User;


@Projection(name="UserProjection", types = { User.class })
public interface UserProjection {

	
}
