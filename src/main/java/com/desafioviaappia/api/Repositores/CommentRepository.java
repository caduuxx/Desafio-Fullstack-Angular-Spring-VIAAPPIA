package com.desafioviaappia.api.Repositores;

import com.desafioviaappia.api.Domain.Comment;
import com.desafioviaappia.api.Domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByIncidentOrderByDataCriacaoAsc(Incident incident);
}
