CREATE TYPE level AS ENUM ('AMATEUR', 'RX', 'ELITE');
CREATE TYPE role as ENUM ('ADMIN', 'COACH', 'ATHLETE');

CREATE TABLE IF NOT EXISTS teams (
    id SERIAL PRIMARY KEY,
    name varchar (100) NOT NULL
);

CREATE TABLE IF NOT EXISTS programs (
    id SERIAL PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    level level NOT NULL,
    program OID NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email varchar(100) NOT NULL,
    password varchar(32) NOT NULL,
    role role NOT NULL,
    level level,
    first_name VARCHAR(32),
    last_name VARCHAR(32),
    phone_number varchar(20),
    date_of_birth DATE,
    team_id INTEGER,
    FOREIGN KEY (team_id) references teams(id)
);