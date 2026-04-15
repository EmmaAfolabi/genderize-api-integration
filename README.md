# Genderize API Integration

This is a RESTful API built with Java 21 and Spring Boot that integrates with the external [Genderize API](https://genderize.io/) to predict gender based on a name query parameter.

## Features
* Secure Input Validation
* External API Integration with Error Handling
* Processing Logic (Probability & Confidence checks)
* Standardized JSON responses for Data and Errors
* Cross-Origin Resource Sharing (CORS) Enabled for all origins.

## Prerequisites
* Java 21 (or you can use Docker)

## Local Development
Run the application using the Gradle wrapper:
```bash
./gradlew bootRun
```
Or build from scratch:
```bash
./gradlew build
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

## API Documentation

### GET `/api/classify`
Predict the gender for a given name.

**Query Parameters:**
* `name` (required): A string representing the name.

**Success Response (200 OK):**
```json
{
 "status": "success",
 "data": {
  "name": "peter",
  "gender": "male",
  "probability": 0.99,
  "sample_size": 1234,
  "is_confident": true,
  "processed_at": "2026-04-15T12:00:00Z"
 }
}
```

**Error Validation Response (400 Bad Request):**
```json
{
  "status": "error",
  "message": "Name query parameter is required"
}
```

## Deployment (Render)
1. Push this repository to GitHub.
2. Go to Render.com and create a new **Web Service**.
3. Connect your GitHub repository.
4. Render will automatically detect the `Dockerfile` and build/deploy your API!
