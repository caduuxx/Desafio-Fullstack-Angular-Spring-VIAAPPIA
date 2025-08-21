package com.desafioviaappia.api.Repositores;

import com.desafioviaappia.api.Domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IncedentRepository extends JpaRepository<Incident, UUID> {

}
