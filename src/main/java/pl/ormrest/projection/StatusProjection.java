package pl.ormrest.projection;

import org.springframework.data.rest.core.config.Projection;

import pl.ormrest.model.Status;


@Projection(name="StatusProjection", types = { Status.class })
public interface StatusProjection {

	
}
