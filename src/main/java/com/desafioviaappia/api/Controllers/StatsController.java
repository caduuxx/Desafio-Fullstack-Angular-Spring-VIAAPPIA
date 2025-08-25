package com.desafioviaappia.api.Controllers;

import com.desafioviaappia.api.Service.StatsService;
import com.desafioviaappia.api.Web.DTO.IncidentStatsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/incidents")
    public Map<String, List<IncidentStatsDTO>> getIncidentStats() {
        Map<String, List<IncidentStatsDTO>> response = new HashMap<>();
        response.put("byPriority", statsService.getIncidentsByPriority());
        response.put("byStatus", statsService.getIncidentsByStatus());
        return response;
    }
}
