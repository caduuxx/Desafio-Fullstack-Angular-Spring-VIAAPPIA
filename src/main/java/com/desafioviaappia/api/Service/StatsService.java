package com.desafioviaappia.api.Service;

import com.desafioviaappia.api.Repositores.IncidentRepository;
import com.desafioviaappia.api.Web.DTO.IncidentStatsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    private final IncidentRepository incidentRepository;

    public StatsService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<IncidentStatsDTO> getIncidentsByPriority() {
        return incidentRepository.countIncidentsByPriority().stream()
                .map(obj -> new IncidentStatsDTO(obj[0].toString(), ((Long) obj[1]).intValue()))
                .toList();
    }

    public List<IncidentStatsDTO> getIncidentsByStatus() {
        return incidentRepository.countIncidentsByStatus().stream()
                .map(obj -> new IncidentStatsDTO(obj[0].toString(), ((Long) obj[1]).intValue()))
                .toList();
    }
}
