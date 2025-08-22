package com.desafioviaappia.api.services;

import com.desafioviaappia.api.Domain.Comment;
import com.desafioviaappia.api.Domain.Incident;
import com.desafioviaappia.api.domain.Comment;
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
    private CommentService commentRepository;

    @Autowired
    private IncedentRepository incidentRepository;

    // Criar comentário
    @Transactional
    public Optional<Comment> addComment(UUID incidentId, Comment comment) {
        return incidentRepository.findById(incidentId)
                .map(incident -> {
                    comment.setIncidentId(incidentId);
                    comment.setDataCriacao(LocalDateTime.now());
                    return commentRepository.save(comment);
                });
    }

    // Listar comentários de um incident
    public List<Comment> getCommentsByIncident(UUID incidentId) {
        return commentRepository.findByIncidentIdOrderByDataCriacaoAsc(incidentId);
    }

    // Remover comentário (opcional)
    @Transactional
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }

}
