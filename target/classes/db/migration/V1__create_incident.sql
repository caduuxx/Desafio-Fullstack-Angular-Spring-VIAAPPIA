-- Ativa extensão para UUIDs
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Tabela principal de incidentes
CREATE TABLE IF NOT EXISTS incident (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titulo VARCHAR(120) NOT NULL,
    descricao TEXT,
    prioridade VARCHAR(10) NOT NULL CHECK (prioridade IN ('BAIXA','MEDIA','ALTA')),
    status VARCHAR(15) NOT NULL CHECK (status IN ('ABERTA','EM_ANDAMENTO','RESOLVIDA','CANCELADA')),
    responsavel_email VARCHAR(255) NOT NULL,
    tags TEXT,
    data_abertura TIMESTAMP NOT NULL DEFAULT now(),
    data_atualizacao TIMESTAMP NOT NULL DEFAULT now()
);

-- Indexes
CREATE INDEX IF NOT EXISTS idx_incident_status ON incident(status);
CREATE INDEX IF NOT EXISTS idx_incident_prioridade ON incident(prioridade);
CREATE INDEX IF NOT EXISTS idx_incident_titulo_texto
    ON incident USING GIN (to_tsvector('portuguese', titulo || ' ' || coalesce(descricao, '')));
