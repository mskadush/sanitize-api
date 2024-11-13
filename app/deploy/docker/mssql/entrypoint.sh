#!/bin/bash

init_db() {
  sleep 20s
  echo "Init words"
  /opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'YourStrong!Passw0rd' -C -d master -i /docker-entrypoint-initdb.d/init.sql
  echo "Init words done"
}

init_db & /opt/mssql/bin/sqlservr