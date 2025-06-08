# CodeQL Analysis Workflow

## Overview

The **CodeQL Analysis** workflow performs static security analysis on the StrengthTracker codebase to identify potential security vulnerabilities, bugs, and code quality issues in both Java and TypeScript code.

## Configuration

**File**: `.github/workflows/codeql.yml`

### Triggers

- **Push** to `main` branch
- **Pull Request** to `main` branch  
- **Scheduled**: Weekly on Sundays at midnight UTC (`0 0 * * 0`)

### Permissions

The workflow requires specific permissions for security analysis:
- `actions: read` - Read workflow artifacts
- `contents: read` - Read repository contents
- `security-events: write` - Write security findings

## Job Configuration

### Matrix Strategy

The workflow uses a matrix strategy to analyze multiple languages:
- **Languages**: `java`, `typescript`
- **Fail-fast**: Disabled (`false`) to ensure all languages are analyzed even if one fails

### Runtime Environment
- **OS**: Ubuntu Latest
- **Strategy**: Parallel execution for each language

## Workflow Steps

### 1. Repository Checkout
- **Action**: `actions/checkout@v4`
- **Purpose**: Downloads the complete repository for analysis

### 2. Java Environment Setup (Conditional)
- **Condition**: Only when `matrix.language == 'java'`
- **Action**: `actions/setup-java@v4`
- **Java Version**: 21
- **Distribution**: Temurin (Eclipse Adoptium)

### 3. CodeQL Initialization
- **Action**: `github/codeql-action/init@v3`
- **Languages**: Uses matrix language (`${{ matrix.language }}`)
- **Purpose**: Prepares CodeQL analysis environment

### 4. Backend Build (Java)
- **Condition**: Only when `matrix.language == 'java'`
- **Working Directory**: `backend/`
- **Command**: `mvn clean package -DskipTests`
- **Purpose**: Compiles Java code for comprehensive analysis

### 5. Frontend Build (TypeScript)
- **Condition**: Only when `matrix.language == 'typescript'`
- **Working Directory**: `frontend/`
- **Commands**: 
  ```bash
  npm ci
  npm run build
  ```
- **Purpose**: Builds Angular application for complete TypeScript analysis

### 6. CodeQL Analysis
- **Action**: `github/codeql-action/analyze@v3`
- **Purpose**: Performs static analysis and uploads results to GitHub Security tab

## Workflow File

```yaml
name: CodeQL Analysis

on:
  push:
    branches: [main]
  pull_request:
    branches: [main]
  schedule:
    - cron: '0 0 * * 0'  # Weekly (optional)

jobs:
  analyze:
    name: Analyze with CodeQL
    runs-on: ubuntu-latest
    permissions:
      actions: read
      contents: read
      security-events: write

    strategy:
      fail-fast: false
      matrix:
        language: [ 'java', 'typescript' ]  # Angular = TypeScript

    steps:
      - name: Checkout repository
        uses: actions/checkout@v4

      # Install Java only for Java
      - name: Set up Java 21
        if: matrix.language == 'java'
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Initialize CodeQL
        uses: github/codeql-action/init@v3
        with:
          languages: ${{ matrix.language }}

      # Manual build required for Java
      - name: Build backend
        if: matrix.language == 'java'
        working-directory: backend
        run: mvn clean package -DskipTests

      # Manual build for Angular (optional, but useful for complete code)
      - name: Build frontend
        if: matrix.language == 'typescript'
        working-directory: frontend
        run: |
          npm ci
          npm run build

      - name: Perform CodeQL Analysis
        uses: github/codeql-action/analyze@v3
```

## Security Analysis Coverage

### Java Analysis
- **Security Vulnerabilities**: SQL injection, XSS, authentication issues
- **Code Quality**: Null pointer exceptions, resource leaks, performance issues
- **Best Practices**: Proper exception handling, secure coding patterns

### TypeScript Analysis  
- **Security Vulnerabilities**: DOM-based XSS, prototype pollution, unsafe operations
- **Code Quality**: Type safety, unused variables, unreachable code
- **Angular-Specific**: Component security, template injection, routing security

## Key Features

### Comprehensive Coverage
- Analyzes complete codebase including compiled artifacts
- Covers both backend and frontend security aspects
- Includes build-time generated code

### Automated Scheduling
- Weekly analysis ensures continuous security monitoring
- Catches newly introduced vulnerabilities
- Monitors for new security patterns

### Integration with GitHub Security
- Results appear in GitHub Security tab
- Integration with pull request checks
- Automated security alerts

## Results and Reporting

### Security Dashboard
Results are available in:
- **GitHub Security Tab**: Detailed vulnerability reports
- **Pull Request Checks**: Security status for PRs
- **Security Alerts**: Automated notifications for critical issues

### Alert Levels
- **Error**: Critical security vulnerabilities requiring immediate attention
- **Warning**: Potential security issues or code quality problems
- **Note**: Code quality suggestions and best practices

## Best Practices

### Build Requirements
- Java analysis requires compiled code for comprehensive coverage
- TypeScript analysis benefits from complete build artifacts
- Both builds skip tests to focus on source code analysis

### Language-Specific Optimizations
- Java: Uses specific JDK version and Maven build
- TypeScript: Includes Angular-specific patterns and dependencies

### Failure Handling
- `fail-fast: false` ensures all languages are analyzed
- Individual language failures don't block other analyses

## Maintenance

### Regular Updates
- Update CodeQL action versions quarterly
- Review and tune analysis queries as needed
- Monitor for new language-specific security patterns

### Performance Optimization
- Monitor analysis duration and optimize build steps
- Review weekly schedule timing based on development patterns
- Consider caching strategies for dependencies

## Troubleshooting

### Common Issues

1. **Build Failures**:
   - Ensure all dependencies are properly configured
   - Check Java/Node.js version compatibility
   - Verify build commands work locally

2. **Analysis Timeout**:
   - Large codebases may require optimization
   - Consider excluding test files or generated code
   - Review CodeQL configuration

3. **Permission Issues**:
   - Verify security-events write permission
   - Check repository security settings
   - Ensure proper GitHub token permissions

### Debugging Steps
1. Check workflow logs for specific error messages
2. Verify build commands work in local environment
3. Review CodeQL documentation for language-specific requirements
4. Test with minimal codebase to isolate issues
