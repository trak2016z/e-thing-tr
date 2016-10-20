package pl.ething.projection;

import org.springframework.data.rest.core.config.Projection;

import pl.ething.model.Message;
import pl.ething.model.Person;


@Projection(name="MessageProjection", types = { Message.class })
public interface MessageProjection {

}
