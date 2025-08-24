package com.desafioviaappia.api.Repositores;

import com.desafioviaappia.api.Domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IncidentRepository extends JpaRepository<Incident, UUID> {

    @Query("SELECT i.prioridade, COUNT(i) FROM Incident i GROUP BY i.prioridade")
    List<Object[]> countIncidentsByPriority();

    @Query("SELECT i.status, COUNT(i) FROM Incident i GROUP BY i.status")
    List<Object[]> countIncidentsByStatus();
}
