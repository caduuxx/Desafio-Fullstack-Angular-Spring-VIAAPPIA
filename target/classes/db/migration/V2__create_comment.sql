-- Tabela de comentários vinculados a incidentes
CREATE TABLE IF NOT EXISTS comment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    incident_id UUID NOT NULL REFERENCES incident(id) ON DELETE CASCADE,
    autor VARCHAR(120) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT now()
);

-- Index para busca rápida por incident_id
CREATE INDEX IF NOT EXISTS idx_comment_incident_id ON comment(incident_id);
