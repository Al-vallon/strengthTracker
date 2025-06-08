# Build & Save Artifacts Workflow

## Overview

The **Build & Save Artifacts** workflow is responsible for building both the backend (Java/Maven) and frontend (Angular) components of the StrengthTracker application and saving the build artifacts for later use.

## Configuration

**File**: `.github/workflows/buildnsave.yml`

### Triggers

- **Push** to `main` branch
- **Pull Request** to `main` branch

### Jobs

#### 1. Build Backend (`build-backend`)

**Runtime Environment**: 
- Ubuntu Latest
- Maven 3.9.9 with Amazon Corretto 21 Alpine container

**Steps**:
1. **Checkout Code**: Uses `actions/checkout@v4`
2. **Build Backend**: 
   - Working directory: `backend/`
   - Command: `mvn clean package -DskipTests`
   - Skips tests for faster builds
3. **Upload Artifact**:
   - Artifact name: `backend-jar`
   - Path: `backend/target/*.jar`
   - Retention: 7 days

#### 2. Build Frontend (`build-frontend`)

**Runtime Environment**: 
- Ubuntu Latest
- Node.js 22

**Steps**:
1. **Checkout Code**: Uses `actions/checkout@v4`
2. **Setup Node**: Uses `actions/setup-node@v4` with Node.js version 22
3. **Install Dependencies**:
   - Working directory: `frontend/`
   - Command: `npm ci` (clean install for reproducible builds)
4. **Build Frontend**:
   - Working directory: `frontend/`
   - Command: `npm run build`
5. **Upload Artifact**:
   - Artifact name: `frontend-dist`
   - Path: `frontend/dist/`
   - Retention: 7 days

## Workflow File

```yaml
name: Build & Save Artifacts

on:
  push:
    branches: [main]
  pull_request:
    branches: [main]

jobs:
  build-backend:
    name: Build Backend
    runs-on: ubuntu-latest
    container: maven:3.9.9-amazoncorretto-21-alpine
    steps:
      - uses: actions/checkout@v4
      - name: Build backend
        working-directory: backend
        run: mvn clean package -DskipTests
      - name: Upload backend artifact
        uses: actions/upload-artifact@v4
        with:
          name: backend-jar
          path: backend/target/*.jar
          retention-days: 7
  build-frontend:
    name: Build Frontend
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Setup Node
        uses: actions/setup-node@v4
        with:
          node-version: '22'
      - name: Install dependencies
        working-directory: frontend
        run: npm ci
      - name: Build frontend
        working-directory: frontend
        run: npm run build
      - name: Upload frontend artifact
        uses: actions/upload-artifact@v4
        with:
          name: frontend-dist
          path: frontend/dist/
          retention-days: 7
```

## Artifacts

### Backend Artifact
- **Name**: `backend-jar`
- **Contents**: Compiled JAR files from Maven build
- **Location**: `backend/target/*.jar`
- **Use Cases**: Deployment, testing, distribution

### Frontend Artifact
- **Name**: `frontend-dist`
- **Contents**: Built Angular application (HTML, CSS, JS)
- **Location**: `frontend/dist/`
- **Use Cases**: Static file serving, deployment

## Key Features

### Parallel Execution
Both backend and frontend builds run in parallel, reducing overall build time.

### Container Usage
The backend build uses a specific Maven container with Amazon Corretto 21, ensuring consistent Java environment.

### Clean Builds
- Backend: Uses `mvn clean package` for fresh builds
- Frontend: Uses `npm ci` for reproducible dependency installation

### Artifact Management
- 7-day retention policy balances storage costs with debugging needs
- Separate artifacts for backend and frontend allow independent deployment

## Best Practices

1. **Skip Tests**: Tests are skipped in build for speed - they should run in separate test workflows
2. **Clean Install**: `npm ci` ensures reproducible frontend builds
3. **Specific Versions**: Node.js and Maven versions are pinned for consistency
4. **Working Directories**: Explicit directory specification prevents path issues

## Troubleshooting

### Common Issues

1. **Build Failures**:
   - Check Maven dependencies in `pom.xml`
   - Verify Node.js compatibility with `package.json`

2. **Artifact Upload Failures**:
   - Ensure build produces files in expected locations
   - Check file path patterns in upload configuration

3. **Container Issues**:
   - Verify Maven container availability
   - Check Java version compatibility

## Monitoring

- Monitor build times and optimize as needed
- Review artifact storage usage monthly
- Update action versions quarterly for security and features
