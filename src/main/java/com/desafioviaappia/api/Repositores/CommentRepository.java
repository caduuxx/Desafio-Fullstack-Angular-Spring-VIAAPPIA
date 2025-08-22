import com.desafioviaappia.api.domain.Comment;
import com.desafioviaappia.api.Domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByIncidentOrderByDataCriacaoAsc(Incident incident);
}
