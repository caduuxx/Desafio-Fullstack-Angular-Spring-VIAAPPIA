package com.desafioviaappia.api.Repositores;

import com.desafioviaappia.api.Domain.Incident;
import com.desafioviaappia.api.Web.DTO.IncidentStatsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IncidentRepository extends JpaRepository<Incident, UUID> {

    // Conta incidentes por prioridade
    @Query("SELECT new com.desafioviaappia.api.Web.DTO.IncidentStatsDTO(i.prioridade, COUNT(i)) " +
            "FROM Incident i GROUP BY i.prioridade")
    List<IncidentStatsDTO> countByPriority();

    // Conta incidentes por status
    @Query("SELECT new com.desafioviaappia.api.Web.DTO.IncidentStatsDTO(i.status, COUNT(i)) " +
            "FROM Incident i GROUP BY i.status")
    List<IncidentStatsDTO> countByStatus();
}
