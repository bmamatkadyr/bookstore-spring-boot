CREATE TABLE books
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    author      VARCHAR(255) NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2)
);

-- full-text search index on title, author, and description
CREATE INDEX books_fulltext_idx
    ON books
        USING GIN (to_tsvector('english', title || ' ' || author || ' ' || description));