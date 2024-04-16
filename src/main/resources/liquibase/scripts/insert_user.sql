-- liquibase formatted sql

-- changeset ivan:1
INSERT INTO users(id, first_name, last_name, password, phone_number, role, username, avatar_id)
    OVERRIDING SYSTEM VALUE
VALUES (1, 'IVAN', 'IVANOV', 'password', '+77777777777', 'USER', 'user@gmail.com', null);