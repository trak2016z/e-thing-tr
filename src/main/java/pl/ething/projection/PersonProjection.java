package pl.ething.projection;

import org.springframework.data.rest.core.config.Projection;

import pl.ething.model.Person;


@Projection(name="PersonProjection", types = { Person.class })
public interface PersonProjection {

	public Long getId();
	public String getName();
	public String getCountry();
}
