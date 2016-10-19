package pl.ormrest.projection;

import org.springframework.data.rest.core.config.Projection;

import pl.ormrest.model.User;


@Projection(name="UserProjection", types = { User.class })
public interface UserProjection {

	
}
