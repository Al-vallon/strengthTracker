# Workflows Documentation Index

This directory contains detailed documentation for all GitHub Actions workflows used in the StrengthTracker project.

## Available Workflows

### 📦 [Build & Save Artifacts](./buildnsave.md)
**Purpose**: Automated building and archiving of application components  
**Triggers**: Push and PR to main branch  
**Artifacts**: Backend JAR files and frontend distribution  

### 🔒 [CodeQL Analysis](./codeql.md)  
**Purpose**: Static security analysis and vulnerability detection  
**Triggers**: Push, PR, and weekly schedule  
**Languages**: Java and TypeScript  

### 📝 [Commit Lint](./commit-lint.md)
**Purpose**: Enforce conventional commit message standards  
**Triggers**: Pull request events  
**Standards**: Conventional commits with project-specific rules  

### 🧹 [Code Linting](./lint.md)
**Purpose**: Maintain code quality and formatting standards  
**Triggers**: All pushes and pull requests  
**Tools**: Maven Spotless (Java) and ESLint (TypeScript)  

## Quick Reference

| Workflow | File | Languages | Frequency |
|----------|------|-----------|-----------|
| Build & Save | `buildnsave.yml` | Java, TypeScript | On main branch changes |
| CodeQL | `codeql.yml` | Java, TypeScript | Push, PR, Weekly |
| Commit Lint | `commit_lint.yml` | N/A | Pull requests |
| Lint | `lint.yml` | Java, TypeScript | All changes |

## Getting Started

1. **For Developers**: Start with [Code Linting](./lint.md) to understand quality standards
2. **For Security**: Review [CodeQL Analysis](./codeql.md) for security scanning details  
3. **For Commits**: Check [Commit Lint](./commit-lint.md) for message formatting rules
4. **For Deployment**: See [Build & Save Artifacts](./buildnsave.md) for build process

## Integration Overview

All workflows are designed to work together:
- **Linting** ensures code quality before analysis
- **CodeQL** performs security scanning on quality code  
- **Build** creates artifacts from verified code
- **Commit Lint** maintains readable project history

For comprehensive workflow overview, see the main [GitHub Actions documentation](../github-actions.md).
