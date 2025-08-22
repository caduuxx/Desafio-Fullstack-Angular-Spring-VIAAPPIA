CREATE TABLE comment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    incident_id UUID NOT NULL REFERENCES incident(id) ON DELETE CASCADE,
    autor VARCHAR(120) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_comment_incident_id ON comment(incident_id);
