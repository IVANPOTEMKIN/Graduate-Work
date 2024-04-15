-- liquibase formatted sql

-- changeset ivan:1
CREATE TABLE images
(
    id   INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    path VARCHAR(255),
    size BIGINT,
    type VARCHAR(255)
);

CREATE TABLE users
(
    id           INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    password     VARCHAR(255),
    phone_number VARCHAR(255),
    role         VARCHAR(255),
    username     VARCHAR(255),
    avatar_id    INTEGER REFERENCES images
);

CREATE TABLE ads
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title       VARCHAR(255),
    price       DOUBLE PRECISION,
    description VARCHAR(255),
    user_id     INTEGER REFERENCES users,
    image_id    INTEGER REFERENCES images
);

CREATE TABLE comments
(
    id         INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at TIMESTAMP,
    text       VARCHAR(255),
    ad_id      INTEGER REFERENCES ads,
    user_id    INTEGER REFERENCES users
);