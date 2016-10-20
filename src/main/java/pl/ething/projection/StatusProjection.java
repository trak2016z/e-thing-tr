package pl.ething.projection;

import org.springframework.data.rest.core.config.Projection;

import pl.ething.model.Status;


@Projection(name="StatusProjection", types = { Status.class })
public interface StatusProjection {

	
}
