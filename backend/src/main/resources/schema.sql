CREATE TABLE IF NOT EXISTS training_levels (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS teams (
    id SERIAL PRIMARY KEY,
    name varchar (100) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    user_role_id INTEGER NOT NULL,
    training_level_id INTEGER,
    first_name VARCHAR(32),
    last_name VARCHAR(32),
    phone_number VARCHAR(20),
    date_of_birth DATE,
    team_id INTEGER,

    FOREIGN KEY (user_role_id) REFERENCES user_roles(id),
    FOREIGN KEY (training_level_id) REFERENCES training_levels(id),
    FOREIGN KEY (team_id) REFERENCES teams(id)
);

CREATE TABLE IF NOT EXISTS general_programs (
    id SERIAL PRIMARY KEY,
    scheduled_date DATE NOT NULL,
    training_level_id INTEGER NOT NULL,
    is_rest_day BOOLEAN NOT NULL DEFAULT FALSE,
    daily_program JSONB,

    FOREIGN KEY (training_level_id) REFERENCES training_levels(id),

    CHECK (
        (is_rest_day = TRUE AND daily_program IS NULL)
        OR
        (is_rest_day = FALSE AND daily_program IS NOT NULL)
    )
);

CREATE TABLE individual_programs (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    scheduled_date DATE NOT NULL,
    is_rest_day BOOLEAN NOT NULL DEFAULT FALSE,
    daily_program JSONB,

    FOREIGN KEY (user_id) REFERENCES users(id),

    CHECK (
        (is_rest_day = TRUE AND daily_program IS NULL)
        OR
        (is_rest_day = FALSE AND daily_program IS NOT NULL)
    )
);



INSERT INTO training_levels (id, name) VALUES
    (1, 'Amateur'),
    (2, 'RX'),
    (3, 'Elite');

INSERT INTO user_roles (id, name) VALUES
    (1, 'USER'),
    (2, 'ADMIN');

INSERT INTO teams (id, name) VALUES
    (1, 'Team Alpha'),
    (2, 'Team Beta');