CREATE TABLE note (
    id serial INT PRIMARY KEY,
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
    id serial INT,
    is_routine BOOLEAN,
    date DATE,
    description TEXT,
    status TaskStatus
);

CREATE TABLE theme (
    id serial INT,
    name VARCHAR(128),
    category CategoryStatus,
    created_at DATE,
    timespent DECIMAL(7,3)
);

CREATE TABLE project (
    completed_at DATE,
    estimated_time DECIMAL(7,3),
    CONSTRAINT theme FOREIGN KEY (id) REFERENCES theme(id)
) INHERITS (theme);

CREATE TABLE routine (
    repeated_days DayType[8],
    time TIME,
    is_active BOOLEAN
) INHERITS (theme);

CREATE TABLE image (
    id serial INT,
    filename VARCHAR(255),
    location VARCHAR(255)
);

ALTER TABLE task 
ADD theme 
INT FOREIGN KEY REFERENCES theme(id);

ALTER TABLE theme 
ADD image 
INT FOREIGN KEY REFERENCES image(id);
