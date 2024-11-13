IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'santization_db')
    BEGIN
        CREATE DATABASE santization_db;
    END
GO
