package com.yazdi.pandemic.sharedkernel.service;

import com.yazdi.pandemic.sharedkernel.events.ApplicationEvent;
import com.yazdi.pandemic.sharedkernel.events.EventBus;
import com.yazdi.pandemic.sharedkernel.events.EventSubscriber;

public interface ApplicationService {

    default <E extends ApplicationEvent> void publishEvent(E event) {
        EventBus eventBus = getEventBus();
        if (eventBus != null) {
            eventBus.publish(event);
        }
    }

    default <E extends ApplicationEvent> void subscribe(String eventType, EventSubscriber subscriber) {
        EventBus eventBus = getEventBus();
        if (eventBus != null) {
            eventBus.subscribe(eventType, subscriber);
        }
    }

    default <E extends ApplicationEvent> void unsubscribe(String eventType, EventSubscriber subscriber) {
        EventBus eventBus = getEventBus();
        if (eventBus != null) {
            eventBus.unsubscribe(eventType, subscriber);
        }
    }

    EventBus getEventBus();

    void setEventBus(EventBus eventBus);
}
