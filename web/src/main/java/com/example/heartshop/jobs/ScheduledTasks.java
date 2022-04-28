package com.example.heartshop.jobs;

import com.example.heartshop.service.PaintingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final PaintingService paintingService;

    public ScheduledTasks(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @Scheduled(fixedDelay = 5000)
    public void refreshMaterializedView() {
        //this.productService.refreshMaterializedView();
    }
}
