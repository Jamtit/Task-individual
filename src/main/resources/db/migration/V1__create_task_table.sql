CREATE TABLE task(
    id BIGSERIAL PRIMARY KEY,
    task_name VARCHAR NOT NULL,
    task_desc VARCHAR,
    unique(task_name)
);