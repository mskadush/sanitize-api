#!/bin/bash

# Variables
KEYSTORE_NAME="my-keystore.jks"   # Name of the KeyStore file
KEY_ALIAS="keystore"            # Alias for the key entry in the KeyStore
KEYSTORE_PASSWORD="changeit"      # Password for the KeyStore
DNAME="CN=localhost, OU=MyOrg, O=MyCompany, L=MyCity, S=MyState, C=US" # Distinguished Name
VALIDITY_DAYS=365                 # Validity period for the certificate in days

# Create the KeyStore with a self-signed certificate
keytool -genkeypair \
    -alias "$KEY_ALIAS" \
    -keyalg RSA \
    -keysize 2048 \
    -dname "$DNAME" \
    -validity "$VALIDITY_DAYS" \
    -keystore "$KEYSTORE_NAME" \
    -storepass "$KEYSTORE_PASSWORD" \
    -keypass "$KEYSTORE_PASSWORD"

echo "Self-signed certificate created in KeyStore: $KEYSTORE_NAME"
