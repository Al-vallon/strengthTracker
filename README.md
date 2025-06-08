<div align="center">

# Strength Tracker 

![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![SASS](https://img.shields.io/badge/SASS-hotpink.svg?style=for-the-badge&logo=SASS&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

A comprehensive fitness tracking application built with Angular and Spring Boot.

</div>

## 📚 Documentation

- **[GitHub Actions Workflows](./docs/github-actions.md)** - Complete documentation for CI/CD workflows
- **[Architecture](./docs/architecture/)** - Application architecture documentation
- **[API Documentation](./strengthTrackerAPI/)** - Bruno API collection for testing

## 🚀 Quick Start

### Prerequisites
- Java 21
- Node.js 22
- Docker (optional)
- PostgreSQL

### Development Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Al-vallon/strengthTracker.git
   cd strengthTracker
   ```

2. **Backend Setup**
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```

3. **Frontend Setup**
   ```bash
   cd frontend
   npm install
   npm start
   ```

4. **Access the application**
   - Frontend: http://localhost:4200
   - Backend API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html

## 🏗️ Architecture

- **Frontend**: Angular 20 with TypeScript and SCSS
- **Backend**: Spring Boot 3 with Java 21
- **Database**: PostgreSQL
- **API Documentation**: Swagger/OpenAPI
- **Containerization**: Docker
- **CI/CD**: GitHub Actions

## 🔧 Development Workflow

This project uses automated workflows for code quality and deployment:

- **Code Linting**: Automatic formatting with Maven Spotless and ESLint
- **Security Scanning**: CodeQL analysis for vulnerability detection  
- **Build & Deploy**: Automated artifact generation and deployment
- **Commit Standards**: Conventional commit message enforcement

See the [GitHub Actions documentation](./docs/github-actions.md) for detailed workflow information.

## 🧪 Testing

### Backend Testing
```bash
cd backend
mvn test
```

### Frontend Testing  
```bash
cd frontend
npm test
npm run e2e
```

## 📦 Deployment

### Using Docker
```bash
docker-compose up -d
```

### Production Deployment
See individual environment configurations:
- Development: `docker-compose.dev.yml`
- Pre-production: `docker-compose.preprod.yml`  
- Production: `docker-compose.prod.yml`

## 🔐 Security

- JWT-based authentication
- CORS configuration
- Input validation
- SQL injection prevention
- Automated security scanning with CodeQL

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Follow commit message conventions (see [Commit Lint Guide](./docs/workflows/commit-lint.md))
4. Make your changes and ensure tests pass
5. Run linting tools (`mvn spotless:apply` for backend, `npm run lint` for frontend)
6. Submit a pull request

### Code Quality Standards
- Backend: Follows Google Java Format via Maven Spotless
- Frontend: ESLint with Angular recommended rules
- All code is automatically checked in CI/CD pipeline

## 📄 License

This project is licensed under the terms specified in the [LICENSE](./LICENSE) file.
