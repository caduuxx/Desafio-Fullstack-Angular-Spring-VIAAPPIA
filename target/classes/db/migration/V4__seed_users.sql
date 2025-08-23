-- admin
INSERT INTO users (id, email, password, role)
VALUES (gen_random_uuid(), 'admin@viaappia.com',
        '$2a$10$9vT0m3bY5l9w6c7b2cH5HOPp1p9v5lUqH6A0t2p4LQyqkX6bH7v9O', -- 123456
        'ADMIN')
ON CONFLICT (email) DO NOTHING;

-- read-only
INSERT INTO users (id, email, password, role)
VALUES (gen_random_uuid(), 'reader@viaappia.com',
        '$2a$10$9vT0m3bY5l9w6c7b2cH5HOPp1p9v5lUqH6A0t2p4LQyqkX6bH7v9O', -- 123456
        'READ')
ON CONFLICT (email) DO NOTHING;
