CREATE TABLE sensitive_word_model
(
    id         BIGINT IDENTITY (1,1) PRIMARY KEY,
    text       VARCHAR(255) NOT NULL,
    is_deleted BIT DEFAULT 0,
);

INSERT INTO sensitive_word_model (text, is_deleted)
VALUES ('"ACTION"', 0),
       ('"ADD"', 0),
       ('"ALL"', 0),
       ('"ALLOCATE"', 0),
       ('"ALTER"', 0),
       ('"ANY"', 0),
       ('"APPLICATION"', 0),
       ('"ARE"', 0),
       ('"AREA"', 0),
       ('"ASC"', 0),
       ('"ASSERTION"', 0),
       ('"ATOMIC"', 0),
       ('"AUTHORIZATION"', 0),
       ('"AVG"', 0),
       ('"BEGIN"', 0),
       ('"BY"', 0),
       ('"CALL"', 0),
       ('"CASCADE"', 0),
       ('"CASCADED"', 0),
       ('"CATALOG"', 0),
       ('"CHECK"', 0),
       ('"CLOSE"', 0),
       ('"COLUMN"', 0),
       ('"COMMIT"', 0),
       ('"COMPRESS"', 0),
       ('"CONNECT"', 0),
       ('"CONNECTION"', 0),
       ('"CONSTRAINT"', 0),
       ('"CONSTRAINTS"', 0),
       ('"CONTINUE"', 0),
       ('"CONVERT"', 0),
       ('"CORRESPONDING"', 0),
       ('"CREATE"', 0),
       ('"CROSS"', 0),
       ('"CURRENT"', 0),
       ('"CURRENT_PATH"', 0),
       ('"CURRENT_SCHEMA"', 0),
       ('"CURRENT_SCHEMAID"', 0),
       ('"CURRENT_USER"', 0),
       ('"CURRENT_USERID"', 0),
       ('"CURSOR"', 0),
       ('"DATA"', 0),
       ('"DEALLOCATE"', 0),
       ('"DECLARE"', 0),
       ('"DEFAULT"', 0),
       ('"DEFERRABLE"', 0),
       ('"DEFERRED"', 0),
       ('"DELETE"', 0),
       ('"DESC"', 0),
       ('"DESCRIBE"', 0),
       ('"DESCRIPTOR"', 0),
       ('"DETERMINISTIC"', 0),
       ('"DIAGNOSTICS"', 0),
       ('"DIRECTORY"', 0),
       ('"DISCONNECT"', 0),
       ('"DISTINCT"', 0),
       ('"DO"', 0),
       ('"DOMAIN"', 0),
       ('"DOUBLEATTRIBUTE"', 0),
       ('"DROP"', 0),
       ('"EACH"', 0),
       ('"EXCEPT"', 0),
       ('"EXCEPTION"', 0),
       ('"EXEC"', 0),
       ('"EXECUTE"', 0),
       ('"EXTERNAL"', 0),
       ('"FETCH"', 0),
       ('"FLOAT"', 0),
       ('"FOREIGN"', 0),
       ('"FOUND"', 0),
       ('"FULL"', 0),
       ('"FUNCTION"', 0),
       ('"GET"', 0),
       ('"GLOBAL"', 0),
       ('"GO"', 0),
       ('"GOTO"', 0),
       ('"GRANT"', 0),
       ('"GROUP"', 0),
       ('"HANDLER"', 0),
       ('"HAVING"', 0),
       ('"IDENTITY"', 0),
       ('"IMMEDIATE"', 0),
       ('"INDEX"', 0),
       ('"INDEXED"', 0),
       ('"INDICATOR"', 0),
       ('"INITIALLY"', 0),
       ('"INNER"', 0),
       ('"INOUT"', 0),
       ('"INPUT"', 0),
       ('"INSENSITIVE"', 0),
       ('"INSERT"', 0),
       ('"INTERSECT"', 0),
       ('"INTO"', 0),
       ('"ISOLATION"', 0),
       ('"JOIN"', 0),
       ('"KEY"', 0),
       ('"LANGUAGE"', 0),
       ('"LAST"', 0),
       ('"LEAVE"', 0),
       ('"LEVEL"', 0),
       ('"LOCAL"', 0),
       ('"LONGATTRIBUTE"', 0),
       ('"LOOP"', 0),
       ('"MODIFIES"', 0),
       ('"MODULE"', 0),
       ('"NAMES"', 0),
       ('"NATIONAL"', 0),
       ('"NATURAL"', 0),
       ('"NEXT"', 0),
       ('"NULLIF"', 0),
       ('"ON"', 0),
       ('"ONLY"', 0),
       ('"OPEN"', 0),
       ('"OPTION"', 0),
       ('"ORDER"', 0),
       ('"OUT"', 0),
       ('"OUTER"', 0),
       ('"OUTPUT"', 0),
       ('"OVERLAPS"', 0),
       ('"OWNER"', 0),
       ('"PARTIAL"', 0),
       ('"PATH"', 0),
       ('"PRECISION"', 0),
       ('"PREPARE"', 0),
       ('"PRESERVE"', 0),
       ('"PRIMARY"', 0),
       ('"PRIOR"', 0),
       ('"PRIVILEGES"', 0),
       ('"PROCEDURE"', 0),
       ('"PUBLIC"', 0),
       ('"READ"', 0),
       ('"READS"', 0),
       ('"REFERENCES"', 0),
       ('"RELATIVE"', 0),
       ('"REPEAT"', 0),
       ('"RESIGNAL"', 0),
       ('"RESTRICT"', 0),
       ('"RETURN"', 0),
       ('"RETURNS"', 0),
       ('"REVOKE"', 0),
       ('"ROLLBACK"', 0),
       ('"ROUTINE"', 0),
       ('"ROW"', 0),
       ('"ROWS"', 0),
       ('"SCHEMA"', 0),
       ('"SCROLL"', 0),
       ('"SECTION"', 0),
       ('"SELECT"', 0),
       ('"SEQ"', 0),
       ('"SEQUENCE"', 0),
       ('"SESSION"', 0),
       ('"SESSION_USER"', 0),
       ('"SESSION_USERID"', 0),
       ('"SET"', 0),
       ('"SIGNAL"', 0),
       ('"SOME"', 0),
       ('"SPACE"', 0),
       ('"SPECIFIC"', 0),
       ('"SQL"', 0),
       ('"SQLCODE"', 0),
       ('"SQLERROR"', 0),
       ('"SQLEXCEPTION"', 0),
       ('"SQLSTATE"', 0),
       ('"SQLWARNING"', 0),
       ('"STATEMENT"', 0),
       ('"STRINGATTRIBUTE"', 0),
       ('"SUM"', 0),
       ('"SYSACC"', 0),
       ('"SYSHGH"', 0),
       ('"SYSLNK"', 0),
       ('"SYSNIX"', 0),
       ('"SYSTBLDEF"', 0),
       ('"SYSTBLDSC"', 0),
       ('"SYSTBT"', 0),
       ('"SYSTBTATT"', 0),
       ('"SYSTBTDEF"', 0),
       ('"SYSUSR"', 0),
       ('"SYSTEM_USER"', 0),
       ('"SYSVIW"', 0),
       ('"SYSVIWCOL"', 0),
       ('"TABLE"', 0),
       ('"TABLETYPE"', 0),
       ('"TEMPORARY"', 0),
       ('"TRANSACTION"', 0),
       ('"TRANSLATE"', 0),
       ('"TRANSLATION"', 0),
       ('"TRIGGER"', 0),
       ('"UNDO"', 0),
       ('"UNION"', 0),
       ('"UNIQUE"', 0),
       ('"UNTIL"', 0),
       ('"UPDATE"', 0),
       ('"USAGE"', 0),
       ('"USER"', 0),
       ('"USING"', 0),
       ('"VALUE"', 0),
       ('"VALUES"', 0),
       ('"VIEW"', 0),
       ('"WHERE"', 0),
       ('"WHILE"', 0),
       ('"WITH"', 0),
       ('"WORK"', 0),
       ('"WRITE"', 0),
       ('"ALLSCHEMAS"', 0),
       ('"ALLTABLES"', 0),
       ('"ALLVIEWS"', 0),
       ('"ALLVIEWTEXTS"', 0),
       ('"ALLCOLUMNS"', 0),
       ('"ALLINDEXES"', 0),
       ('"ALLINDEXCOLS"', 0),
       ('"ALLUSERS"', 0),
       ('"ALLTBTS"', 0),
       ('"TABLEPRIVILEGES"', 0),
       ('"TBTPRIVILEGES"', 0),
       ('"MYSCHEMAS"', 0),
       ('"MYTABLES"', 0),
       ('"MYTBTS"', 0),
       ('"MYVIEWS"', 0),
       ('"SCHEMAVIEWS"', 0),
       ('"DUAL"', 0),
       ('"SCHEMAPRIVILEGES"', 0),
       ('"SCHEMATABLES"', 0),
       ('"STATISTICS"', 0),
       ('"USRTBL"', 0),
       ('"STRINGTABLE"', 0),
       ('"LONGTABLE"', 0),
       ('"DOUBLETABLE"', 0),
       ('"SELECT * FROM"', 0);
