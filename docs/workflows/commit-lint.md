# Commit Lint Workflow

## Overview

The **Commit Lint** workflow ensures that all commit messages follow conventional commit format standards. This workflow helps maintain a clean, readable commit history and enables automated changelog generation.

## Configuration

**File**: `.github/workflows/commit_lint.yml`

### Triggers

The workflow runs on pull request events:
- **opened**: When a pull request is first created
- **edited**: When pull request details are modified
- **synchronize**: When new commits are pushed to the pull request

### Runtime Environment
- **OS**: Ubuntu Latest
- **Scope**: Pull requests only (not direct pushes to main)

## Workflow Steps

### 1. Repository Checkout
- **Action**: `actions/checkout@v4`
- **Purpose**: Downloads the repository to access commit history and configuration

### 2. Commit Message Linting
- **Action**: `wagoid/commitlint-github-action@v3`
- **Purpose**: Validates commit messages against conventional commit standards
- **Configuration**: Uses project's `commitlint.config.js` file

## Workflow File

```yaml
name: Lint Commit Messages

on:
  pull_request:
    types: [opened, edited, synchronize]

jobs:
  commitlint:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - uses: wagoid/commitlint-github-action@v3
```

## Commit Message Standards

The workflow enforces conventional commit format based on your project's `commitlint.config.js` configuration.

### Standard Format
```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

### Common Types
- **feat**: New features
- **fix**: Bug fixes
- **docs**: Documentation changes
- **style**: Code style changes (formatting, etc.)
- **refactor**: Code refactoring
- **test**: Test additions or modifications
- **chore**: Build process or auxiliary tool changes
- **ci**: Continuous integration changes
- **perf**: Performance improvements
- **build**: Build system changes

### Examples

#### Valid Commit Messages
```
feat: add user authentication system
fix: resolve login validation bug
docs: update API documentation
style: format code according to prettier rules
refactor: simplify user service logic
test: add unit tests for workout controller
chore: update dependencies
ci: add CodeQL security scanning
perf: optimize database queries
build: configure Docker multi-stage builds
```

#### With Scope
```
feat(auth): implement JWT token validation
fix(ui): correct responsive layout issues
docs(api): add endpoint documentation
test(user): add integration tests
```

#### With Body and Footer
```
feat: add user workout tracking

Implement comprehensive workout tracking system including:
- Exercise logging
- Progress tracking  
- Performance analytics

Closes #123
Breaking-change: Updated user model schema
```

## Configuration Integration

The workflow automatically uses your project's commitlint configuration:

**Location**: `commitlint.config.js` (project root)

### Example Configuration
```javascript
module.exports = {
  extends: ['@commitlint/config-conventional'],
  rules: {
    'type-enum': [
      2,
      'always',
      [
        'feat',
        'fix',
        'docs',
        'style',
        'refactor',
        'test',
        'chore',
        'ci',
        'perf',
        'build'
      ]
    ],
    'subject-case': [2, 'never', ['upper-case']],
    'subject-empty': [2, 'never'],
    'subject-full-stop': [2, 'never', '.'],
    'header-max-length': [2, 'always', 72]
  }
};
```

## Validation Process

### Pull Request Validation
1. **All Commits Checked**: Every commit in the PR is validated
2. **Real-time Feedback**: Results appear as PR status checks
3. **Blocking Behavior**: Invalid commits prevent PR merging (if configured)

### Validation Rules
- **Type Required**: Commit must start with valid type
- **Description Required**: Descriptive text after colon
- **Length Limits**: Header typically limited to 72 characters
- **Case Sensitivity**: Usually lowercase for types and descriptions
- **Format Consistency**: Strict adherence to conventional format

## Benefits

### Automated Changelog
Well-formatted commits enable:
- Automatic changelog generation
- Version bump automation
- Release note creation
- Semantic versioning

### Team Collaboration
- **Consistency**: Uniform commit message format
- **Readability**: Easy to understand commit history
- **Searchability**: Better git log filtering and searching
- **Documentation**: Commits serve as development documentation

### CI/CD Integration
- **Automated Releases**: Trigger releases based on commit types
- **Version Management**: Semantic versioning from commit messages
- **Deployment Decisions**: Deploy based on change types

## Status Checks

### Success Indicators
✅ **Pass**: All commit messages follow conventional format
- Green checkmark in PR status checks  
- PR can be merged (if no other blocking checks)

### Failure Indicators
❌ **Fail**: One or more commits violate format rules
- Red X in PR status checks
- Detailed error messages showing violations
- PR blocked from merging until fixed

## Error Resolution

### Common Issues and Fixes

#### 1. Missing Type
**Error**: `subject may not be empty`
**Fix**: Add commit type
```bash
# Bad
git commit -m "update user service"

# Good  
git commit -m "refactor: update user service"
```

#### 2. Invalid Type
**Error**: `type must be one of [feat, fix, docs, ...]`
**Fix**: Use valid type
```bash
# Bad
git commit -m "update: add new feature"

# Good
git commit -m "feat: add new feature"
```

#### 3. Header Too Long
**Error**: `header must not be longer than 72 characters`
**Fix**: Shorten description or use body
```bash
# Bad
git commit -m "feat: implement comprehensive user authentication system with JWT tokens and password validation"

# Good
git commit -m "feat: implement user authentication system

Add JWT token validation and password security features"
```

#### 4. Wrong Case
**Error**: `subject must not be sentence-case`
**Fix**: Use lowercase
```bash
# Bad
git commit -m "feat: Add New User Feature"

# Good
git commit -m "feat: add new user feature"
```

### Fixing Commit Messages

#### For Unpushed Commits
```bash
# Fix last commit message
git commit --amend -m "feat: corrected commit message"

# Fix multiple commits interactively
git rebase -i HEAD~3
```

#### For Pushed Commits
```bash
# Rewrite and force push (use carefully)
git rebase -i HEAD~3
git push --force-with-lease
```

## Best Practices

### Writing Good Commit Messages
1. **Be Specific**: Clearly describe what changed
2. **Use Present Tense**: "add feature" not "added feature"
3. **Be Concise**: Keep headers under 72 characters
4. **Explain Why**: Use body for context when needed
5. **Reference Issues**: Include issue numbers when applicable

### Team Workflow
1. **Education**: Train team on conventional commits
2. **Tools**: Use commitizen or similar tools for assistance
3. **Pre-commit Hooks**: Add local validation before push
4. **Documentation**: Maintain team guidelines for commit standards

## Integration with Other Workflows

### Semantic Release
Commit messages can trigger:
- Automatic version bumps
- Changelog generation
- Release creation
- Package publishing

### Branch Protection
Configure branch protection rules to:
- Require status checks to pass
- Block merging with invalid commits
- Enforce workflow compliance

## Monitoring and Maintenance

### Regular Review
- Monitor commit message quality trends
- Update configuration as team needs evolve
- Review and update allowed commit types
- Adjust length limits based on project needs

### Tool Updates
- Update commitlint action version regularly
- Keep commitlint configuration current
- Review new conventional commit standards
- Integrate with development tools and IDEs
