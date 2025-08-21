package com.desafioviaappia.api.Repositores;
import com.desafioviaappia.api.domain.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComentariosRepository extends JpaRepository<Comentarios, UUID> {
}
