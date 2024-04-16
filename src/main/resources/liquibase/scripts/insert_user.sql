-- liquibase formatted sql

-- changeset ivan:1
INSERT INTO users(id, first_name, last_name, password, phone_number, role, username, avatar_id)
    OVERRIDING SYSTEM VALUE
VALUES (100, 'IVAN', 'IVANOV', '$2a$10$z3/aZ9/5zTbV6YpQf5jwP.ZL06f5UIoz.4P1KJSUWAYKBhd/lRXWy', '+77777777777', 'USER', 'user@gmail.com', null);

INSERT INTO users(id, first_name, last_name, password, phone_number, role, username, avatar_id)
    OVERRIDING SYSTEM VALUE
VALUES (200, 'PETR', 'PETROV', '$2a$10$z3/aZ9/5zTbV6YpQf5jwP.ZL06f5UIoz.4P1KJSUWAYKBhd/lRXWy', '+77777777777', 'ADMIN', 'admin@gmail.com', null);