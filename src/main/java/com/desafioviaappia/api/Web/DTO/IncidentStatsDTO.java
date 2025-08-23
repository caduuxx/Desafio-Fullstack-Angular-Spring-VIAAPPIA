package com.desafioviaappia.api.Web.DTO;

import com.desafioviaappia.api.Domain.Incident.Prioridade;
import com.desafioviaappia.api.Domain.Incident.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentStatsDTO {

    private Prioridade prioridade;
    private Status status;
    private long count;

    // Construtor para contar por prioridade
    public IncidentStatsDTO(Prioridade prioridade, long count) {
        this.prioridade = prioridade;
        this.count = count;
    }

    // Construtor para contar por status
    public IncidentStatsDTO(Status status, long count) {
        this.status = status;
        this.count = count;
    }

    // Getters e setters
    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
