package com.desafioviaappia.api.controllers;

import com.desafioviaappia.api.domain.Comment;
import com.desafioviaappia.api.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/incidents/{incidentId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // GET /incidents/{id}/comments
    @GetMapping
    public ResponseEntity<List<Comment>> list(@PathVariable UUID incidentId) {
        List<Comment> comments = commentService.getCommentsByIncident(incidentId);
        return ResponseEntity.ok(comments);
    }

    // POST /incidents/{id}/comments
    @PostMapping
    public ResponseEntity<Comment> add(@PathVariable UUID incidentId, @RequestBody Comment comment) {
        return commentService.addComment(incidentId, comment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /incidents/{id}/comments/{commentId} (opcional)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
