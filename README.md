# Sanitization API

## Getting started

- Running in dev mode
- Migration script
- Exception handling is handled at a global level for application errors (WordError) and for general spring errors inside a ControllerAdvisor

## Security considerations
- Setting up certs
- Running in encrypted mode
- SQL input santization
- Authentication
  - Using internal and external with simple in-memory api-key management. These in memory api keys are bound to relevant roles
- Profiles
  - local - used for development while app is running on a dev machine
  - prod - used for live deployment, also hides internal endpoint so swagger can be displayed without exposing internals to public consumers
- 

## Building

- Use GitHub actions
- Build docker image
  - Dockerfile can be found at ..
- Certificates will have to be rotated to maintain TLS

## Deploying
- Once built, docker image can be deployed to EKS/ECS/Lambda
- Instead of docker container for the database, we will use AWS RDS.
  - This removes maintenance of certificates for your SQL server and the maintenance of the instance
  - If necessary, self-signed certificates can be generated using <script name>
- Logging and monitoring will be enabled using OTeL
  - A docker-compose file can be found at <location>
  - OTLP exporter endpoint can be configured to export data to an OTeL collector
#### Docker container
- docker file
- CICD
- generate certs into certs folder
- how to rotate certs
#### OTeL
- OTLP demo
#### MSSQL config
- encrypt = true
- Santize input
- Migration script for setup
#### env vars
- db creds
- active profile
#### AUTH
- Roles & spring security
- Global exception handling