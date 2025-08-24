package com.desafioviaappia.api.Web.DTO;

public class IncidentStatsDTO {
    private String name;
    private Long count;

    public IncidentStatsDTO(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    // Se quiser aceitar int também:
    public IncidentStatsDTO(String name, int count) {
        this.name = name;
        this.count = (long) count;
    }

    public String getName() { return name; }
    public Long getCount() { return count; }
}
