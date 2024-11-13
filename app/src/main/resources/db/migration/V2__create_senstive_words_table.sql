IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'sensitive_word_model' AND TABLE_SCHEMA = 'dbo')
    BEGIN
        CREATE TABLE sensitive_word_model
        (
            id         BIGINT IDENTITY (1,1) PRIMARY KEY,
            text       VARCHAR(255) NOT NULL,
            is_deleted BIT DEFAULT 0,
        );
    END
GO
