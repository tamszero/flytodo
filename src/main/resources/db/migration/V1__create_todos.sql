CREATE TABLE todos (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(80) NOT NULL,
    description VARCHAR(100),
    due_at TIMESTAMP,
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    completed_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
