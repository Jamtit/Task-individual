CREATE TABLE task(
    id VARCHAR PRIMARY KEY,
    task_name VARCHAR NOT NULL,
    task_desc VARCHAR,
    unique(task_name)
);