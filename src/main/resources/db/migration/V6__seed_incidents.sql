INSERT INTO incident (id, titulo, descricao, prioridade, status, responsavel_email)
VALUES
    (gen_random_uuid(), 'Servidor fora do ar', 'Erro 500 no servidor principal', 'ALTA', 'ABERTA', 'admin@viaappia.com'),
    (gen_random_uuid(), 'Bug no frontend', 'Botão de login não funciona', 'MEDIA', 'EM_ANDAMENTO', 'reader@viaappia.com'),
    (gen_random_uuid(), 'Banco lento', 'Consultas demoram mais de 5s', 'ALTA', 'RESOLVIDA', 'admin@viaappia.com');
