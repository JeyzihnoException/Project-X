package com.platform.platform.model.event;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class Processor {

    private static final List<Consumer<MessageEvent>> consumerList = new CopyOnWriteArrayList<>();

    public void register(Consumer<MessageEvent> event) {
        consumerList.add(event);
    }

    public void process(MessageEvent event) {
        consumerList.forEach(messageEventConsumer -> messageEventConsumer.accept(event));
    }
}
