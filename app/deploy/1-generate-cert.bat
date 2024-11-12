@echo off
setlocal

:: Variables
set KEYSTORE_PATH=../src/main/resources/ssl/my-keystore.jks
set KEY_ALIAS=keystore
set KEYSTORE_PASSWORD=changeit
set DNAME="CN=localhost, OU=MyOrg, O=MyCompany, L=MyCity, S=MyState, C=US"
set VALIDITY_DAYS=365

:: Create the KeyStore with a self-signed certificate
keytool -genkeypair ^
    -alias %KEY_ALIAS% ^
    -keyalg RSA ^
    -keysize 2048 ^
    -dname %DNAME% ^
    -validity %VALIDITY_DAYS% ^
    -keystore %KEYSTORE_PATH% ^
    -storepass %KEYSTORE_PASSWORD% ^
    -keypass %KEYSTORE_PASSWORD%

echo Self-signed certificate created in KeyStore: %KEYSTORE_PATH%

endlocal
pause
