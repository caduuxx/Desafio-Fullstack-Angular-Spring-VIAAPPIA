package com.desafioviaappia.api.Controllers;

import com.desafioviaappia.api.Domain.Incident;
import com.desafioviaappia.api.Service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    // GET /incidents
    @GetMapping
    public ResponseEntity<Page<Incident>> list(
            @RequestParam Optional<String> status,
            @RequestParam Optional<String> prioridade,
            @RequestParam Optional<String> q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dataAbertura") String sort,
            @RequestParam(defaultValue = "desc") String dir
    ) {
        Sort.Direction direction = dir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        return ResponseEntity.ok(incidentService.listIncidents(status, prioridade, q, pageable));
    }

    // GET /incidents/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Incident> getById(@PathVariable UUID id) {
        return incidentService.getIncidentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /incidents
    @PostMapping
    public ResponseEntity<Incident> create(@RequestBody Incident incident) {
        Incident created = incidentService.createIncident(incident);
        return ResponseEntity.ok(created);
    }

    // PUT /incidents/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Incident> update(@PathVariable UUID id, @RequestBody Incident incident) {
        return incidentService.updateIncident(id, incident)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /incidents/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH /incidents/{id}/status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Incident> updateStatus(@PathVariable UUID id, @RequestBody StatusUpdate statusUpdate) {
        return incidentService.updateStatus(id, statusUpdate.getStatus())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DTO interno para PATCH
    public static class StatusUpdate {
        private Incident.Status status;
        public Incident.Status getStatus() { return status; }
        public void setStatus(Incident.Status status) { this.status = status; }
    }
}
