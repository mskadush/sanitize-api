package com.example.sanitize.adapters.`in`.internal

import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWord
import com.example.sanitize.adapters.`in`.internal.dtos.UpdateSensitiveWordRequest
import com.example.sanitize.adapters.`in`.web.dtos.SanitzationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class InternalSantizationController {

  // TODO: Rest annotations
  // TODO: Swagger annotation
  @PostMapping("/words")
  fun addSensitiveWords(
    @RequestBody request: List<SensitiveWord>
  ): ResponseEntity<SanitzationResponse> {
    sensitiveWords.addAll(request.map { it.text })
    TODO()
  }

  @DeleteMapping("/words")
  fun removeSensitiveWords(
    @RequestBody request: List<SensitiveWord>
  ): ResponseEntity<SanitzationResponse> {
    sensitiveWords.removeIf { it in request.map { it.text } }
    return ResponseEntity.noContent().build()
  }

  @GetMapping("/words")
  fun getSensitiveWords(
  ): ResponseEntity<List<SensitiveWord>> {
    return ResponseEntity.ok(sensitiveWords.map {
      SensitiveWord(text = it)
    })
  }

  @PutMapping("/words")
  fun updateSensitiveWords(
    @RequestBody request: UpdateSensitiveWordRequest
  ): ResponseEntity<Unit> {
    val removed = sensitiveWords.removeIf { it == request.currentValue.text }
    if (removed) {
      sensitiveWords.add(request.newValue.text)
    }
    return ResponseEntity.noContent().build()
  }

  val sensitiveWords = mutableSetOf(
    "ACTION"
    ,"ADD"
    ,"ALL"
    ,"ALLOCATE"
    ,"ALTER"
    ,"ANY"
    ,"APPLICATION"
    ,"ARE"
    ,"AREA"
    ,"ASC"
    ,"ASSERTION"
    ,"ATOMIC"
    ,"AUTHORIZATION"
    ,"AVG"
    ,"BEGIN"
    ,"BY"
    ,"CALL"
    ,"CASCADE"
    ,"CASCADED"
    ,"CATALOG"
    ,"CHECK"
    ,"CLOSE"
    ,"COLUMN"
    ,"COMMIT"
    ,"COMPRESS"
    ,"CONNECT"
    ,"CONNECTION"
    ,"CONSTRAINT"
    ,"CONSTRAINTS"
    ,"CONTINUE"
    ,"CONVERT"
    ,"CORRESPONDING"
    ,"CREATE"
    ,"CROSS"
    ,"CURRENT"
    ,"CURRENT_PATH"
    ,"CURRENT_SCHEMA"
    ,"CURRENT_SCHEMAID"
    ,"CURRENT_USER"
    ,"CURRENT_USERID"
    ,"CURSOR"
    ,"DATA"
    ,"DEALLOCATE"
    ,"DECLARE"
    ,"DEFAULT"
    ,"DEFERRABLE"
    ,"DEFERRED"
    ,"DELETE"
    ,"DESC"
    ,"DESCRIBE"
    ,"DESCRIPTOR"
    ,"DETERMINISTIC"
    ,"DIAGNOSTICS"
    ,"DIRECTORY"
    ,"DISCONNECT"
    ,"DISTINCT"
    ,"DO"
    ,"DOMAIN"
    ,"DOUBLEATTRIBUTE"
    ,"DROP"
    ,"EACH"
    ,"EXCEPT"
    ,"EXCEPTION"
    ,"EXEC"
    ,"EXECUTE"
    ,"EXTERNAL"
    ,"FETCH"
    ,"FLOAT"
    ,"FOREIGN"
    ,"FOUND"
    ,"FULL"
    ,"FUNCTION"
    ,"GET"
    ,"GLOBAL"
    ,"GO"
    ,"GOTO"
    ,"GRANT"
    ,"GROUP"
    ,"HANDLER"
    ,"HAVING"
    ,"IDENTITY"
    ,"IMMEDIATE"
    ,"INDEX"
    ,"INDEXED"
    ,"INDICATOR"
    ,"INITIALLY"
    ,"INNER"
    ,"INOUT"
    ,"INPUT"
    ,"INSENSITIVE"
    ,"INSERT"
    ,"INTERSECT"
    ,"INTO"
    ,"ISOLATION"
    ,"JOIN"
    ,"KEY"
    ,"LANGUAGE"
    ,"LAST"
    ,"LEAVE"
    ,"LEVEL"
    ,"LOCAL"
    ,"LONGATTRIBUTE"
    ,"LOOP"
    ,"MODIFIES"
    ,"MODULE"
    ,"NAMES"
    ,"NATIONAL"
    ,"NATURAL"
    ,"NEXT"
    ,"NULLIF"
    ,"ON"
    ,"ONLY"
    ,"OPEN"
    ,"OPTION"
    ,"ORDER"
    ,"OUT"
    ,"OUTER"
    ,"OUTPUT"
    ,"OVERLAPS"
    ,"OWNER"
    ,"PARTIAL"
    ,"PATH"
    ,"PRECISION"
    ,"PREPARE"
    ,"PRESERVE"
    ,"PRIMARY"
    ,"PRIOR"
    ,"PRIVILEGES"
    ,"PROCEDURE"
    ,"PUBLIC"
    ,"READ"
    ,"READS"
    ,"REFERENCES"
    ,"RELATIVE"
    ,"REPEAT"
    ,"RESIGNAL"
    ,"RESTRICT"
    ,"RETURN"
    ,"RETURNS"
    ,"REVOKE"
    ,"ROLLBACK"
    ,"ROUTINE"
    ,"ROW"
    ,"ROWS"
    ,"SCHEMA"
    ,"SCROLL"
    ,"SECTION"
    ,"SELECT"
    ,"SEQ"
    ,"SEQUENCE"
    ,"SESSION"
    ,"SESSION_USER"
    ,"SESSION_USERID"
    ,"SET"
    ,"SIGNAL"
    ,"SOME"
    ,"SPACE"
    ,"SPECIFIC"
    ,"SQL"
    ,"SQLCODE"
    ,"SQLERROR"
    ,"SQLEXCEPTION"
    ,"SQLSTATE"
    ,"SQLWARNING"
    ,"STATEMENT"
    ,"STRINGATTRIBUTE"
    ,"SUM"
    ,"SYSACC"
    ,"SYSHGH"
    ,"SYSLNK"
    ,"SYSNIX"
    ,"SYSTBLDEF"
    ,"SYSTBLDSC"
    ,"SYSTBT"
    ,"SYSTBTATT"
    ,"SYSTBTDEF"
    ,"SYSUSR"
    ,"SYSTEM_USER"
    ,"SYSVIW"
    ,"SYSVIWCOL"
    ,"TABLE"
    ,"TABLETYPE"
    ,"TEMPORARY"
    ,"TRANSACTION"
    ,"TRANSLATE"
    ,"TRANSLATION"
    ,"TRIGGER"
    ,"UNDO"
    ,"UNION"
    ,"UNIQUE"
    ,"UNTIL"
    ,"UPDATE"
    ,"USAGE"
    ,"USER"
    ,"USING"
    ,"VALUE"
    ,"VALUES"
    ,"VIEW"
    ,"WHERE"
    ,"WHILE"
    ,"WITH"
    ,"WORK"
    ,"WRITE"
    ,"ALLSCHEMAS"
    ,"ALLTABLES"
    ,"ALLVIEWS"
    ,"ALLVIEWTEXTS"
    ,"ALLCOLUMNS"
    ,"ALLINDEXES"
    ,"ALLINDEXCOLS"
    ,"ALLUSERS"
    ,"ALLTBTS"
    ,"TABLEPRIVILEGES"
    ,"TBTPRIVILEGES"
    ,"MYSCHEMAS"
    ,"MYTABLES"
    ,"MYTBTS"
    ,"MYVIEWS"
    ,"SCHEMAVIEWS"
    ,"DUAL"
    ,"SCHEMAPRIVILEGES"
    ,"SCHEMATABLES"
    ,"STATISTICS"
    ,"USRTBL"
    ,"STRINGTABLE"
    ,"LONGTABLE"
    ,"DOUBLETABLE"
    ,"SELECT * FROM"
  )
}