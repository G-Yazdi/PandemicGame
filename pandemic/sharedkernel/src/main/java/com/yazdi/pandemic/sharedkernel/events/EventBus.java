package com.yazdi.pandemic.sharedkernel.events;

import java.util.Map;
import java.util.Set;

public interface EventBus {
	Map<String, Set<EventSubscriber>> getSubscribers();
    <E extends ApplicationEvent> void publish(E event);

    <E extends ApplicationEvent> void subscribe(String eventType, EventSubscriber subscriber);

    <E extends ApplicationEvent> void unsubscribe(String eventType, EventSubscriber subscriber);
}
