CREATE TABLE note (
    id serial INT PRIMARY_KEY,
    created_at DATE,
    updated_at DATE,
    title VARCHAR(255),
    content TEXT
);