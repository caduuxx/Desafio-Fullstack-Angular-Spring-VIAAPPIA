package com.desafioviaappia.api.Controllers;

import com.desafioviaappia.api.Service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/incidents")
    public Map<String, Object> getIncidentStats() {
        Map<String, Object> response = new HashMap<>();
        response.put("byStatus", statsService.getIncidentsByStatus());
        response.put("byPriority", statsService.getIncidentsByPriority());
        return response;
    }
}
