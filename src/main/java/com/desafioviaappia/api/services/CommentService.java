package com.desafioviaappia.api.services;

import com.desafioviaappia.api.domain.Comment;
import com.desafioviaappia.api.Domain.Incident;
import com.desafioviaappia.api.Repositores.CommentRepository;
import com.desafioviaappia.api.Repositores.IncedentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IncedentRepository incidentRepository;

    // Criar comentário
    @Transactional
    public Optional<Comment> addComment(UUID incidentId, Comment comment) {
        return incidentRepository.findById(incidentId)
                .map(incident -> {
                    comment.setIncident(incident); // <-- seta o objeto Incident
                    comment.setDataCriacao(LocalDateTime.now());
                    return commentRepository.save(comment);
                });
    }

    // Listar comentários de um incident
    public List<Comment> getCommentsByIncident(UUID incidentId) {
        Incident incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident não encontrado"));
        return commentRepository.findByIncidentOrderByDataCriacaoAsc(incident);
    }

    // Remover comentário
    @Transactional
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }

}
