# import key store
keytool -importcert -file docker/certs/mssql-selfsigned.crt -alias my-server-cert -keystore ../src/main/resources/ssl/custom-truststore.jks -storepass changeit

# validate key store
keytool -list -keystore docker/certs/custom-truststore.jks -storepass changeit
