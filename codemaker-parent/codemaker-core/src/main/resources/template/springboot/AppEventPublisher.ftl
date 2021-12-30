package ${package}.utils;

import ${package}.domain.event.BaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppEventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public <T extends BaseEvent> void publish(T t) {
        applicationContext.publishEvent(t);
    }

}