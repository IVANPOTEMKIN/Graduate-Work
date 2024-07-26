-- liquibase formatted sql

-- changeset ivan:1
INSERT INTO users(first_name, last_name, password, phone_number, role, username, avatar_id)
    OVERRIDING SYSTEM VALUE
VALUES ('IVAN', 'IVANOV', '$2a$10$z3/aZ9/5zTbV6YpQf5jwP.ZL06f5UIoz.4P1KJSUWAYKBhd/lRXWy', '+77777777777', 'USER',
        'user@gmail.com', null);

INSERT INTO users(first_name, last_name, password, phone_number, role, username, avatar_id)
    OVERRIDING SYSTEM VALUE
VALUES ('PETR', 'PETROV', '$2a$10$z3/aZ9/5zTbV6YpQf5jwP.ZL06f5UIoz.4P1KJSUWAYKBhd/lRXWy', '+77777777777', 'ADMIN',
        'admin@gmail.com', null);