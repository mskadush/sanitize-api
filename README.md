# Sanitization API

The **Sanitization API** is a Spring Boot Kotlin application designed to redact text for security and sanitization purposes. This API integrates with a MSSQL database, and provides customised exception handling and structured authentication.

## Developing

### Running in Development Mode
To run the application in development mode:
1. Ensure that you have a connection to a MSSQL database or run the database in `app/deploy/docker/docker-compose.yaml` using `docker-compose up mssql -d`
2. Set up your local environment variables as defined in `application-local.yaml`.
3. Run the application with the `local` profile enabled by setting your active profile in your IDE or executing:
   ```bash
   ./gradlew app:bootRun --args='--spring.profiles.active=local'
   ```
4. The `local` profile enables all Swagger documentation accessible.

### Global Exception Handling
Exception handling is managed at a global level:
- `com.example.sanitize.adapters.out.security.ControllerAdvisor` intercepts and formats errors consistently, handling both application-specific exceptions (`WordError`) and general Spring exceptions.

## Security Considerations

### SQL Input Sanitization
The application uses parameterized queries to sanitize SQL inputs, reducing the risk of SQL injection.

### Authentication and Authorization
The application supports both internal and external API authentication:
- Internal authentication is based on in-memory API keys bound to roles (admin, user, etc.).
- For production, we will move API keys to the database to enhance security and scalability.

## Building

### Continuous Integration with GitHub Actions
GitHub Actions runs the CI pipeline, covering build, test, and Docker image creation. The CI pipeline stages can be simplified into:
1. **Build**: Compiles the code.
2. **Test**: Runs unit and integration tests.
3. **Code Quality Check**: Runs static code analysis on the code to ensure no vulnerable or hard to maintain code is commited and coding standards are maintained.
4. **Dependency Vulnerability Scan**: Review all dependencies used by the project to see if any know vulnerabilities have been reported on them. If reported, an upgrade of the depency will be required.
3. **Package**: Packages the application and builds the Docker image, then publishes it to ECR.

### Building the Docker Image
1. The `Dockerfile` for building the application image can be found at `app/deploy/docker/Dockerfile`.
2. To build the Docker image manually, at `app/deploy/docker` run:
   ```bash
   docker build -t sanitization-api .
   ```

## Deploying

### Application

#### Deploying on AWS EKS, ECS, or Lambda
Once the Docker image is built, it can be deployed on AWS EKS, ECS, or Lambda. Deployment scripts and configurations should be customized based on the chosen platform (e.g. ArgoCD for EKS deployments).

#### Profiles
The application uses two primary profiles:
- **local**: Used for development on a local machine.
- **prod**: For live deployment; hides internal endpoints and only exposes Swagger for public APIs.

#### TLS Certificate Placement
For production deployments, it's recommended to handle TLS certificates at the network edge (e.g. Cloudflare or
internet facing loadbalancer). This approach offloads certificate management from the application, eliminating the added
complexity of rotating application certificates. Certificates should be rotated regularly if stored at the
application layer.

#### Logging and Monitoring with OpenTelemetry
OpenTelemetry (OTeL) is used for logging and tracing:
- A `docker-compose.yaml` file is available in `app/deploy/docker/docker-compose.yaml` for setting up Grafana and an
  - OTeL collector to collect and visualize telemetry data locally for testing.
- Configure the OTLP exporter endpoint in `application.yaml` as `otel.exporter.otlp.endpoint` to send data to an OTeL collector.
- The configured OTeL libraries will send trace and log data to the collector

### MSSQL

#### Setting up MSSQL with AWS RDS
Rather than deploying a database container, this setup uses AWS RDS for MSSQL:
- This minimizes the need for manual certificate management and server maintenance.
- If self-signed certificates are required, they can be generated using the provided `app/deploy/0-generate-cert.sh` script for secure connections.

#### Setting up MSSQL container certificates and running in Encrypted Mode
To run MSSQL with TLS:
- Obtain a valid TLS certificate and private key (e.g., from AWS ACM, LetsEncrypt or generate one using
  - `app/deploy/0-generate-cert.sh`). Ensuring the naming matches the expected values in the `docker-compose` file if
  - you are using it to run MSSQL
- Place the certificate in a location accessible by the MSSQL application.

#### Database Authentication
- Database credentials can be loaded using
  - AWS Secrets Manager which can allow easy rotation when using aws secrets manager with spring cloud
  - Environment variables which can allow easy, but manual, rotation when loading Kubernetes secrets in EKS
  - HashiCorp Vault for easy, automated rotation of database secrets
#### Database Migrations
All database migrations are handled by Flyway. Migration scripts are located in the `app/src/main/resources/db/migration` directory and are applied automatically when the application starts.

---

This README provides a structured overview of the setup, build, and deployment process, along with best practices for security, certificate management, and monitoring.