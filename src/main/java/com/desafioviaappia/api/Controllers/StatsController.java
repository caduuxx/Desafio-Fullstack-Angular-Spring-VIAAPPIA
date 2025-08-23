package com.desafioviaappia.api.Controllers;

import com.desafioviaappia.api.Domain.Incident;
import com.desafioviaappia.api.Repositores.IncidentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class StatsController {

    private final IncidentRepository incidentRepository;

    public StatsController(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @GetMapping("/stats/incidents")
    public Map<String, Object> getStats() {
        List<Incident> all = incidentRepository.findAll();

        Map<String, Long> byStatus = all.stream()
                .collect(Collectors.groupingBy(i -> i.getStatus().name(), Collectors.counting()));

        Map<String, Long> byPrioridade = all.stream()
                .collect(Collectors.groupingBy(i -> i.getPrioridade().name(), Collectors.counting()));

        Map<String, Object> result = new HashMap<>();
        result.put("byStatus", byStatus);
        result.put("byPrioridade", byPrioridade);

        return result;
    }
}
