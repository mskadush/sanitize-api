#!/bin/bash
# Generate certs
# Variables
CERT_DIR="./docker/certs"
CERT_NAME="mssql-selfsigned"
DAYS_VALID=365

# Create the certificate directory if it doesn't exist
mkdir -p "$CERT_DIR"

# Generate a private key
openssl genrsa -out "$CERT_DIR/$CERT_NAME.key" 2048

# Generate a certificate signing request (CSR)
openssl req -new -key "$CERT_DIR/$CERT_NAME.key" -out "$CERT_DIR/$CERT_NAME.csr" \
  -subj "/C=SA/ST=WC/L=CPT/O=FlashHomework/OU=Product/CN=localhost"

# Generate the self-signed certificate
openssl x509 -req -days "$DAYS_VALID" -in "$CERT_DIR/$CERT_NAME.csr" -signkey "$CERT_DIR/$CERT_NAME.key" -out "$CERT_DIR/$CERT_NAME.crt"

# Generate a combined .pfx file (for use with SQL Server if needed)
openssl pkcs12 -export -out "$CERT_DIR/$CERT_NAME.pfx" -inkey "$CERT_DIR/$CERT_NAME.key" -in "$CERT_DIR/$CERT_NAME.crt" -passout pass:

# Display output paths
echo "Self-signed certificate and key generated:"
echo "  Private Key: $CERT_DIR/$CERT_NAME.key"
echo "  Certificate: $CERT_DIR/$CERT_NAME.crt"
echo "  PFX Bundle:  $CERT_DIR/$CERT_NAME.pfx (useful for SQL Server)"
