package com.example.heartshop.listeners;

import com.example.heartshop.model.events.PaintingCreatedEvent;
import com.example.heartshop.service.PaintingService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PaintingEventHandler {

    private final PaintingService paintingService;

    public PaintingEventHandler(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @EventListener
    public void onProductCreated(PaintingCreatedEvent event) {
        this.paintingService.refreshMaterializedView();
    }
}
