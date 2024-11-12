# import key store
export KEYSTORE_PATH=../src/main/resources/ssl/custom-truststore.jks
keytool -importcert -file docker/certs/mssql-selfsigned.crt -alias my-server-cert -keystore $KEYSTORE_PATH -storepass changeit

# validate key store
keytool -list -keystore $KEYSTORE_PATH -storepass changeit
