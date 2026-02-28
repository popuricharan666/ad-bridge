# AdBridge Campaign Orchestrator

Multi-module Java project organized for clean scaling:
- `ad-bridge-api`: Dropwizard REST API (resources, services, DI config)
- `ad-bridge-dal`: data access layer (DAO contracts and implementations)
- `ad-bridge-commons`: shared campaign and integration domain models

## Build all modules
```bash
mvn clean package
```

## Build with ad platform SDK clients
```bash
mvn -Pplatform-clients clean package
```

## Run API
```bash
java -jar ad-bridge-api/target/ad-bridge-api-0.1-SNAPSHOT.jar server ad-bridge-api/config.yml
```

## API endpoints
- `GET /health`
- `POST /campaigns`
- `GET /campaigns/{campaignId}`
- `POST /campaigns/{campaignId}/insights/sync`
- `POST /campaigns/{campaignId}/optimize`

## Platform dependencies added
- Meta: `com.facebook.business.sdk:facebook-java-business-sdk`
- Google Ads: `com.google.api-ads:google-ads` (managed via Google Ads BOM)
- X: OAuth + HTTP stack (`scribejava` + `okhttp`) for Ads API integration
- Reliability: `resilience4j` retry + rate limiter
