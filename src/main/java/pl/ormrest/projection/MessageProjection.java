package pl.ormrest.projection;

import org.springframework.data.rest.core.config.Projection;

import pl.ormrest.model.Message;
import pl.ormrest.model.Person;


@Projection(name="MessageProjection", types = { Message.class })
public interface MessageProjection {

}
