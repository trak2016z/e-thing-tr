package pl.ormrest.projection;

import org.springframework.data.rest.core.config.Projection;

import pl.ormrest.model.Person;


@Projection(name="PersonProjection", types = { Person.class })
public interface PersonProjection {

	public Long getId();
	public String getName();
	public String getCountry();
}
