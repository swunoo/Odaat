-- Enums
CREATE TYPE TaskStatus AS ENUM (
    'TODO', 'INPROGRESS', 'COMPLETED');

CREATE TYPE ProgramType AS ENUM (
    'JOB', 'FAMILY', 'LEARNING', 'EXERCISE', 'CHORES'
);

CREATE TYPE DayType AS ENUM (
    'SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'HOLIDAY', 'ABSENT'
);

-- Tables
CREATE TABLE note (
    id serial PRIMARY KEY,
    created_at DATE,
    updated_at DATE,
    title VARCHAR(255),
    content TEXT
);

CREATE TABLE absence (
    Date date PRIMARY KEY,
    title VARCHAR(127)
);

CREATE TABLE holiday (
    Date date PRIMARY KEY,
    title VARCHAR(127)
);

CREATE TABLE task (
    id serial PRIMARY KEY,
    theme INT,
    is_routine BOOLEAN,
    date DATE,
    description TEXT,
    status TaskStatus
);

CREATE TABLE theme (
    id serial PRIMARY KEY,
    image INT,
    name VARCHAR(128),
    program ProgramType,
    created_at DATE,
    timespent DECIMAL(7,3)
);

CREATE TABLE project (
    theme_id int PRIMARY KEY,
    estimated_time DECIMAL(7,3),
    CONSTRAINT theme FOREIGN KEY (theme_id) REFERENCES theme(id) ON DELETE CASCADE
);

CREATE TABLE routine (
    theme_id int PRIMARY KEY,
    repeated_days DayType[8],
    is_active BOOLEAN,
    CONSTRAINT theme FOREIGN KEY (theme_id) REFERENCES theme(id) ON DELETE CASCADE
);

CREATE TABLE image (
    id serial PRIMARY KEY,
    filename VARCHAR(255),
    location VARCHAR(255)
);

-- Adding FKs
ALTER TABLE task
ADD CONSTRAINT fk_task
FOREIGN KEY (theme)
REFERENCES theme(id);

ALTER TABLE theme
ADD CONSTRAINT fk_img
FOREIGN KEY (image)
REFERENCES image(id);