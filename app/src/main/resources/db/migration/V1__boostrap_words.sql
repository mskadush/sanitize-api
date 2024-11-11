-- Insert initial data into your tables
CREATE TABLE sensitive_word_model (
                                      id BIGINT IDENTITY(1,1) PRIMARY KEY,
                                      text VARCHAR(255) NOT NULL,
                                      is_deleted BIT DEFAULT 0,
                                      CONSTRAINT UQ_sensitive_word UNIQUE (text)
);
INSERT INTO sensitive_word_model (text)
VALUES ('"ACTION"'), ('"ADD"'), ('"ALL"'), ('"ALLOCATE"'), ('"ALTER"'), ('"ANY"'), ('"APPLICATION"'), ('"ARE"'), ('"AREA"'), ('"ASC"')
     , ('"ASSERTION"'), ('"ATOMIC"'), ('"AUTHORIZATION"'), ('"AVG"'), ('"BEGIN"'), ('"BY"'), ('"CALL"'), ('"CASCADE"'), ('"CASCADED"'), ('"CATALOG"'), ('"CHECK"'), ('"CLOSE"'), ('"COLUMN"'), ('"COMMIT"'), ('"COMPRESS"'), ('"CONNECT"'), ('"CONNECTION"'), ('"CONSTRAINT"'), ('"CONSTRAINTS"')
     , ('"CONTINUE"'), ('"CONVERT"'), ('"CORRESPONDING"'), ('"CREATE"'), ('"CROSS"'), ('"CURRENT"'), ('"CURRENT_PATH"'), ('"CURRENT_SCHEMA"'), ('"CURRENT_SCHEMAID"'), ('"CURRENT_USER"')
     , ('"CURRENT_USERID"'), ('"CURSOR"'), ('"DATA"'), ('"DEALLOCATE"'), ('"DECLARE"'), ('"DEFAULT"'), ('"DEFERRABLE"'), ('"DEFERRED"'), ('"DELETE"'), ('"DESC"')
     , ('"DESCRIBE"'), ('"DESCRIPTOR"'), ('"DETERMINISTIC"'), ('"DIAGNOSTICS"'), ('"DIRECTORY"'), ('"DISCONNECT"'), ('"DISTINCT"'), ('"DO"'), ('"DOMAIN"'), ('"DOUBLEATTRIBUTE"')
     , ('"DROP"'), ('"EACH"'), ('"EXCEPT"'), ('"EXCEPTION"'), ('"EXEC"'), ('"EXECUTE"'), ('"EXTERNAL"'), ('"FETCH"'), ('"FLOAT"'), ('"FOREIGN"')
     , ('"FOUND"'), ('"FULL"'), ('"FUNCTION"'), ('"GET"'), ('"GLOBAL"'), ('"GO"'), ('"GOTO"'), ('"GRANT"'), ('"GROUP"'), ('"HANDLER"')
     , ('"HAVING"'), ('"IDENTITY"'), ('"IMMEDIATE"'), ('"INDEX"'), ('"INDEXED"'), ('"INDICATOR"'), ('"INITIALLY"'), ('"INNER"'), ('"INOUT"'), ('"INPUT"')
     , ('"INSENSITIVE"'), ('"INSERT"'), ('"INTERSECT"'), ('"INTO"'), ('"ISOLATION"'), ('"JOIN"'), ('"KEY"'), ('"LANGUAGE"'), ('"LAST"'), ('"LEAVE"')
     , ('"LEVEL"')
     , ('"LOCAL"')
     , ('"LONGATTRIBUTE"')
     , ('"LOOP"')
     , ('"MODIFIES"')
     , ('"MODULE"')
     , ('"NAMES"')
     , ('"NATIONAL"')
     , ('"NATURAL"')
     , ('"NEXT"')
     , ('"NULLIF"')
     , ('"ON"')
     , ('"ONLY"')
     , ('"OPEN"')
     , ('"OPTION"')
     , ('"ORDER"')
     , ('"OUT"')
     , ('"OUTER"')
     , ('"OUTPUT"')
     , ('"OVERLAPS"')
     , ('"OWNER"')
     , ('"PARTIAL"')
     , ('"PATH"')
     , ('"PRECISION"')
     , ('"PREPARE"')
     , ('"PRESERVE"')
     , ('"PRIMARY"')
     , ('"PRIOR"')
     , ('"PRIVILEGES"')
     , ('"PROCEDURE"')
     , ('"PUBLIC"')
     , ('"READ"')
     , ('"READS"')
     , ('"REFERENCES"')
     , ('"RELATIVE"')
     , ('"REPEAT"')
     , ('"RESIGNAL"')
     , ('"RESTRICT"')
     , ('"RETURN"')
     , ('"RETURNS"')
     , ('"REVOKE"')
     , ('"ROLLBACK"')
     , ('"ROUTINE"')
     , ('"ROW"')
     , ('"ROWS"')
     , ('"SCHEMA"')
     , ('"SCROLL"')
     , ('"SECTION"')
     , ('"SELECT"')
     , ('"SEQ"')
     , ('"SEQUENCE"')
     , ('"SESSION"')
     , ('"SESSION_USER"')
     , ('"SESSION_USERID"')
     , ('"SET"')
     , ('"SIGNAL"')
     , ('"SOME"')
     , ('"SPACE"')
     , ('"SPECIFIC"')
     , ('"SQL"')
     , ('"SQLCODE"')
     , ('"SQLERROR"')
     , ('"SQLEXCEPTION"')
     , ('"SQLSTATE"')
     , ('"SQLWARNING"')
     , ('"STATEMENT"')
     , ('"STRINGATTRIBUTE"')
     , ('"SUM"')
     , ('"SYSACC"')
     , ('"SYSHGH"')
     , ('"SYSLNK"')
     , ('"SYSNIX"')
     , ('"SYSTBLDEF"')
     , ('"SYSTBLDSC"')
     , ('"SYSTBT"')
     , ('"SYSTBTATT"')
     , ('"SYSTBTDEF"')
     , ('"SYSUSR"')
     , ('"SYSTEM_USER"')
     , ('"SYSVIW"')
     , ('"SYSVIWCOL"')
     , ('"TABLE"')
     , ('"TABLETYPE"')
     , ('"TEMPORARY"')
     , ('"TRANSACTION"')
     , ('"TRANSLATE"')
     , ('"TRANSLATION"')
     , ('"TRIGGER"')
     , ('"UNDO"')
     , ('"UNION"')
     , ('"UNIQUE"')
     , ('"UNTIL"')
     , ('"UPDATE"')
     , ('"USAGE"')
     , ('"USER"')
     , ('"USING"')
     , ('"VALUE"')
     , ('"VALUES"')
     , ('"VIEW"')
     , ('"WHERE"')
     , ('"WHILE"')
     , ('"WITH"')
     , ('"WORK"')
     , ('"WRITE"')
     , ('"ALLSCHEMAS"')
     , ('"ALLTABLES"')
     , ('"ALLVIEWS"')
     , ('"ALLVIEWTEXTS"')
     , ('"ALLCOLUMNS"')
     , ('"ALLINDEXES"')
     , ('"ALLINDEXCOLS"')
     , ('"ALLUSERS"')
     , ('"ALLTBTS"')
     , ('"TABLEPRIVILEGES"')
     , ('"TBTPRIVILEGES"')
     , ('"MYSCHEMAS"')
     , ('"MYTABLES"')
     , ('"MYTBTS"')
     , ('"MYVIEWS"')
     , ('"SCHEMAVIEWS"')
     , ('"DUAL"')
     , ('"SCHEMAPRIVILEGES"')
     , ('"SCHEMATABLES"')
     , ('"STATISTICS"')
     , ('"USRTBL"')
     , ('"STRINGTABLE"')
     , ('"LONGTABLE"')
     , ('"DOUBLETABLE"')
     , ('"SELECT * FROM"');