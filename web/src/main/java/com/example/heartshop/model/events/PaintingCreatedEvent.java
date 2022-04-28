package com.example.heartshop.model.events;

import com.example.heartshop.model.Painting;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class PaintingCreatedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public PaintingCreatedEvent(Painting source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public PaintingCreatedEvent(Painting source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}

