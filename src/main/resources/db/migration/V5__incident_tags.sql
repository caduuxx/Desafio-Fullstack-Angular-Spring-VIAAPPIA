CREATE TABLE incident_tags (
    incident_id UUID NOT NULL,
    tag VARCHAR(255),
    FOREIGN KEY (incident_id) REFERENCES incident(id) ON DELETE CASCADE
);
