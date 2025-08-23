package com.desafioviaappia.api.Service;

import com.desafioviaappia.api.Domain.Incident;
import com.desafioviaappia.api.Repositores.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    // Criar
    @Transactional
    @CacheEvict(value = "incidents", allEntries = true)
    public Incident createIncident(Incident incident) {
        normalizeTags(incident);
        incident.setDataAbertura(LocalDateTime.now());
        incident.setDataAtualizacao(LocalDateTime.now());
        return incidentRepository.save(incident);
    }

    // Listar com filtros e paginação
    @Cacheable("incidents")
    public Page<Incident> listIncidents(Optional<String> status,
                                        Optional<String> prioridade,
                                        Optional<String> q,
                                        Pageable pageable) {
        List<Incident> all = incidentRepository.findAll();

        List<Incident> filtered = all.stream()
                .filter(i -> status.map(s -> i.getStatus().name().equalsIgnoreCase(s)).orElse(true))
                .filter(i -> prioridade.map(p -> i.getPrioridade().name().equalsIgnoreCase(p)).orElse(true))
                .filter(i -> q.map(text -> i.getTitulo().toLowerCase().contains(text.toLowerCase())
                                || (i.getDescricao() != null && i.getDescricao().toLowerCase().contains(text.toLowerCase())))
                        .orElse(true))
                .collect(Collectors.toList());

        int start = Math.min((int)pageable.getOffset(), filtered.size());
        int end = Math.min(start + pageable.getPageSize(), filtered.size());
        return new PageImpl<>(filtered.subList(start, end), pageable, filtered.size());
    }

    // Buscar por ID
    public Optional<Incident> getIncidentById(UUID id) {
        return incidentRepository.findById(id);
    }

    // Atualizar
    @Transactional
    @CacheEvict(value = "incidents", allEntries = true)
    public Optional<Incident> updateIncident(UUID id, Incident incident) {
        return incidentRepository.findById(id)
                .map(existing -> {
                    existing.setTitulo(incident.getTitulo());
                    existing.setDescricao(incident.getDescricao());
                    existing.setPrioridade(incident.getPrioridade());
                    existing.setStatus(incident.getStatus());
                    existing.setResponsavelEmail(incident.getResponsavelEmail());
                    existing.setTags(incident.getTags());
                    touchUpdate(existing);
                    return incidentRepository.save(existing);
                });
    }

    // Deletar
    @Transactional
    @CacheEvict(value = "incidents", allEntries = true)
    public void deleteIncident(UUID id) {
        incidentRepository.deleteById(id);
    }

    // Alterar status
    @Transactional
    @CacheEvict(value = "incidents", allEntries = true)
    public Optional<Incident> updateStatus(UUID id, Incident.Status status) {
        return incidentRepository.findById(id)
                .map(existing -> {
                    existing.setStatus(status);
                    touchUpdate(existing);
                    return incidentRepository.save(existing);
                });
    }

    // ======================
    // Métodos DRY / utilitários
    // ======================
    private void normalizeTags(Incident incident) {
        if (incident.getTags() != null) {
            List<String> normalized = incident.getTags().stream()
                    .filter(t -> t != null && !t.isBlank())
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .distinct()
                    .collect(Collectors.toList());
            incident.setTags(normalized);
        }
    }

    private void touchUpdate(Incident incident) {
        incident.setDataAtualizacao(LocalDateTime.now());
    }
}
