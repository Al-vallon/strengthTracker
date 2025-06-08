# Code Linting Workflow

## Overview

The **Code Linting** workflow maintains code quality standards across the StrengthTracker project by running automated formatting and linting tools for both backend (Java) and frontend (Angular/TypeScript) code.

## Configuration

**File**: `.github/workflows/lint.yml`

### Triggers
- **Push**: All pushes to any branch
- **Pull Request**: All pull request events

### Runtime Environment
- **OS**: Ubuntu Latest
- **Multi-language**: Java 21 and Node.js 22

## Workflow Steps

### 1. Repository Checkout
- **Action**: `actions/checkout@v3`
- **Purpose**: Downloads repository for code analysis

### 2. Java Environment Setup
- **Action**: `actions/setup-java@v4`
- **Java Version**: 21
- **Distribution**: Temurin (Eclipse Adoptium)
- **Purpose**: Provides Java runtime for Maven Spotless

### 3. Backend Code Formatting
- **Working Directory**: `./backend`
- **Tool**: Maven Spotless
- **Command**: `mvn spotless:apply`
- **Purpose**: Automatically formats Java code according to project standards

### 4. Node.js Environment Setup
- **Action**: `actions/setup-node@v3`
- **Node Version**: 22
- **Purpose**: Provides Node.js runtime for npm and ESLint

### 5. Frontend Dependencies Installation
- **Working Directory**: `./frontend`
- **Command**: `npm install`
- **Purpose**: Installs required packages for linting

### 6. Frontend Code Linting
- **Working Directory**: `./frontend`
- **Tool**: ESLint
- **Command**: `npm run lint`
- **Purpose**: Checks TypeScript/Angular code for quality and style issues

## Workflow File

```yaml
name: Lint Code
# This workflow runs ESLint and Checkstyle to ensure code quality.

on: [push, pull_request]

jobs:
  lint:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Set up Java 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Build and run spotless
        run: mvn spotless:apply
        working-directory: ./backend

      - name: Set up Node.js
        uses: actions/setup-node@v3
        with:
          node-version: '22'

      - name: Install dependencies
        run: npm install
        working-directory: ./frontend

      - name: Run ESLint
        run: npm run lint
        working-directory: ./frontend
```

## Code Quality Tools

### Backend: Maven Spotless

#### Purpose
- **Code Formatting**: Automatically formats Java code
- **Consistency**: Ensures uniform code style across the project
- **Integration**: Seamlessly integrates with Maven build process

#### Features
- **Automatic Formatting**: Fixes formatting issues automatically
- **Multiple Formatters**: Supports various Java formatters (Google Java Format, Eclipse, etc.)
- **Custom Rules**: Configurable formatting rules
- **Import Organization**: Automatically organizes import statements

#### Configuration
Spotless configuration is typically defined in `backend/pom.xml`:

```xml
<plugin>
    <groupId>com.diffplug.spotless</groupId>
    <artifactId>spotless-maven-plugin</artifactId>
    <version>2.x.x</version>
    <configuration>
        <java>
            <googleJavaFormat/>
            <removeUnusedImports/>
            <trimTrailingWhitespace/>
            <endWithNewline/>
        </java>
    </configuration>
</plugin>
```

#### Common Formatting Rules
- **Indentation**: Consistent spacing (typically 2 or 4 spaces)
- **Line Length**: Maximum line length limits
- **Import Organization**: Alphabetical ordering and unused import removal
- **Bracket Placement**: Consistent brace positioning
- **Whitespace**: Trailing whitespace removal

### Frontend: ESLint

#### Purpose
- **Code Quality**: Identifies problematic patterns in TypeScript/JavaScript
- **Style Consistency**: Enforces consistent coding style
- **Error Prevention**: Catches potential bugs and issues
- **Angular Best Practices**: Enforces Angular-specific patterns

#### Configuration
ESLint configuration is defined in `frontend/eslint.config.js`:

```javascript
module.exports = {
  extends: [
    '@angular-eslint/recommended',
    '@typescript-eslint/recommended',
    'prettier'
  ],
  rules: {
    // Custom rules specific to the project
    '@typescript-eslint/no-unused-vars': 'error',
    '@angular-eslint/component-selector': 'error',
    'prefer-const': 'error',
    'no-var': 'error'
  }
};
```

#### Rule Categories

##### TypeScript Rules
- **Type Safety**: Ensures proper TypeScript usage
- **Variable Declaration**: Enforces `const` and `let` over `var`
- **Function Declarations**: Consistent function syntax
- **Interface Usage**: Proper interface and type definitions

##### Angular Rules
- **Component Structure**: Proper component organization
- **Template Syntax**: Angular template best practices
- **Service Patterns**: Dependency injection standards
- **Lifecycle Hooks**: Proper hook implementation

##### General JavaScript Rules
- **Code Quality**: Unreachable code detection
- **Performance**: Inefficient patterns identification  
- **Security**: Potential security vulnerabilities
- **Maintainability**: Code complexity analysis

## Code Quality Standards

### Java (Backend) Standards
```java
// Proper formatting example
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
    }
}
```

### TypeScript (Frontend) Standards
```typescript
// Proper linting example
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  private readonly userService = inject(UserService);
  
  user: User | null = null;
  
  ngOnInit(): void {
    this.loadUser();
  }
  
  private loadUser(): void {
    this.userService.getCurrentUser().subscribe({
      next: (user) => this.user = user,
      error: (error) => console.error('Failed to load user:', error)
    });
  }
}
```

## Integration Benefits

### Continuous Quality Assurance
- **Automated Checks**: Every push and PR is automatically checked
- **Early Detection**: Issues caught before code review
- **Consistent Standards**: Uniform code quality across team
- **Reduced Technical Debt**: Prevents accumulation of formatting issues

### Developer Experience
- **Immediate Feedback**: Quick identification of style issues
- **Automatic Fixes**: Spotless automatically applies formatting
- **IDE Integration**: Works with development environment linting
- **Team Consistency**: All developers follow same standards

### Code Review Efficiency
- **Focus on Logic**: Reviewers can focus on functionality, not formatting
- **Reduced Bikeshedding**: Eliminates style debates
- **Cleaner Diffs**: Consistent formatting makes changes easier to review
- **Professional Appearance**: Well-formatted code looks more professional

## Workflow Results

### Success Indicators
✅ **Backend Formatting**: Spotless applies formatting successfully
✅ **Frontend Linting**: ESLint passes without errors
✅ **Code Quality**: All quality checks pass

### Failure Indicators
❌ **Spotless Errors**: Java code formatting issues
❌ **ESLint Errors**: TypeScript/Angular linting violations
❌ **Build Failures**: Configuration or dependency issues

## Error Resolution

### Common Backend Issues

#### 1. Spotless Formatting Violations
```bash
# Error: Code not properly formatted
[ERROR] The following files had format violations:
[ERROR]   src/main/java/com/example/UserService.java

# Solution: Run Spotless locally
mvn spotless:apply
```

#### 2. Import Organization Issues
```java
// Bad: Unused imports
import java.util.List;
import java.util.ArrayList;  // Unused

// Good: Clean imports
import java.util.List;
```

### Common Frontend Issues

#### 1. ESLint Rule Violations
```typescript
// Error: @typescript-eslint/no-unused-vars
const unusedVariable = 'value';  // Remove unused variables

// Error: prefer-const
let constantValue = 'never changes';  // Use const instead

// Fix
const constantValue = 'never changes';
```

#### 2. Angular-Specific Issues
```typescript
// Error: @angular-eslint/component-selector
@Component({
  selector: 'userProfile',  // Should use kebab-case
  // ...
})

// Fix
@Component({
  selector: 'app-user-profile',
  // ...
})
```

## Local Development Integration

### Pre-commit Setup
```bash
# Install pre-commit hooks
npm install --save-dev husky lint-staged

# Configure package.json
{
  "husky": {
    "hooks": {
      "pre-commit": "lint-staged"
    }
  },
  "lint-staged": {
    "*.java": ["mvn spotless:apply"],
    "*.{ts,js}": ["eslint --fix"]
  }
}
```

### IDE Configuration

#### IntelliJ IDEA (Java)
- Enable Spotless plugin
- Configure auto-format on save
- Set up code style to match Spotless rules

#### VS Code (TypeScript)
- Install ESLint extension
- Enable auto-fix on save
- Configure workspace settings for consistency

## Performance Optimization

### Caching Strategies
- Cache Node.js dependencies between runs
- Cache Maven dependencies for faster builds
- Use action caching for improved performance

### Parallel Execution
Consider splitting into separate jobs for better performance:
```yaml
jobs:
  lint-backend:
    # Backend linting only
  lint-frontend:
    # Frontend linting only
```

## Maintenance

### Regular Updates
- **Tool Versions**: Update ESLint, Spotless, and related plugins quarterly
- **Rule Review**: Periodically review and update linting rules
- **Performance Monitoring**: Monitor workflow execution times
- **Team Feedback**: Collect and incorporate developer feedback

### Configuration Management
- **Centralized Config**: Maintain linting configurations in version control
- **Documentation**: Keep linting rules documented and accessible
- **Training**: Ensure team understands quality standards
- **Gradual Updates**: Introduce new rules gradually to avoid disruption

## Best Practices

### Rule Selection
- **Start Conservative**: Begin with basic rules, add complexity gradually
- **Team Consensus**: Ensure team agrees on style decisions
- **Tool Integration**: Choose rules that work well with development tools
- **Performance Impact**: Consider impact on build times

### Error Handling
- **Clear Messages**: Ensure error messages are helpful and actionable
- **Documentation**: Provide clear guidance for fixing common issues
- **Automation**: Use auto-fix capabilities where appropriate
- **Gradual Enforcement**: Introduce strict rules incrementally
