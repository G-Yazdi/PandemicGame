package com.yazdi.pandemic.sharedkernel.events;

public interface EventSubscriber {
    <E extends ApplicationEvent> void onEvent(E event);
}
