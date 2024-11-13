CREATE TABLE sensitive_word_model
(
    id         BIGINT IDENTITY (1,1) PRIMARY KEY,
    text       VARCHAR(255) NOT NULL,
    is_deleted BIT DEFAULT 0,
);