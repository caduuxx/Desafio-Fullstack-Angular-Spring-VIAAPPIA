package com.desafioviaappia.api.Service;

import com.desafioviaappia.api.Repositores.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatsService {

    private final IncidentRepository incidentRepository;

    public StatsService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public Map<String, Long> getIncidentsByStatus() {
        List<Object[]> results = incidentRepository.countByStatus();
        Map<String, Long> response = new HashMap<>();
        for (Object[] row : results) {
            response.put(row[0].toString(), (Long) row[1]);
        }
        return response;
    }

    public Map<String, Long> getIncidentsByPriority() {
        List<Object[]> results = incidentRepository.countByPriority();
        Map<String, Long> response = new HashMap<>();
        for (Object[] row : results) {
            response.put(row[0].toString(), (Long) row[1]);
        }
        return response;
    }
}