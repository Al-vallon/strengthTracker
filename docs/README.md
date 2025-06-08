# Documentation Overview

This document provides an overview of the complete StrengthTracker documentation system and how to use the automated documentation generation.

## 📁 Documentation Structure

```
docs/
├── github-actions.md          # Overview of CI/CD workflows
├── architecture/              # Application architecture
│   ├── backend.md            # Backend architecture
│   ├── database.md           # Database design
│   └── frontend.md           # Frontend architecture
├── workflows/                 # Individual workflow documentation
│   ├── README.md             # Workflow index
│   ├── buildnsave.md         # Build & Save Artifacts
│   ├── codeql.md             # CodeQL Analysis
│   ├── commit-lint.md        # Commit Linting
│   └── lint.md               # Code Linting
├── diagrams/                  # Architecture diagrams
├── latex/                     # PDF/HTML styling
│   ├── header.tex            # LaTeX header for PDF
│   ├── style.css             # CSS for HTML
│   └── template.html         # HTML template
└── pdfs/                      # Generated individual PDFs
```

## 🔄 Automated Documentation Generation

The documentation workflow (`.github/workflows/documentation.yml`) automatically generates:

### PDF Documents
- **Individual PDFs**: Each markdown file converted to a styled PDF
- **Master PDF**: Complete documentation in a single PDF file
- **Diagram PDFs**: Any images/diagrams converted to PDF format

### HTML Documentation
- **Interactive Website**: Complete documentation as a navigable website
- **GitHub Pages**: Automatically deployed to GitHub Pages
- **Responsive Design**: Mobile-friendly documentation

### Triggers
The workflow runs automatically when:
- Changes are made to any file in the `docs/` directory
- The workflow file itself is modified
- Manual trigger via GitHub Actions interface

## 📊 Generated Output

### PDF Features
- Professional styling with StrengthTracker branding
- Table of contents with clickable links
- Consistent formatting across all documents
- Page numbers and headers
- Optimized for printing

### HTML Features
- Responsive design for all devices
- Interactive table of contents
- Syntax highlighting for code blocks
- Search functionality (via browser)
- Direct links to GitHub repository

## 🚀 Usage

### Viewing Documentation

#### Online (Recommended)
- **GitHub Pages**: Available at `https://[username].github.io/strengthTracker`
- **Repository**: Direct viewing in GitHub interface

#### Downloads
- **Complete PDF**: Download from GitHub Actions artifacts
- **Individual PDFs**: Specific document downloads
- **HTML Archive**: Complete website for offline use

### Updating Documentation

1. **Edit Markdown Files**: Update any `.md` file in the `docs/` directory
2. **Commit Changes**: Push to any branch
3. **Automatic Generation**: Workflow runs automatically
4. **Review Output**: Check generated PDFs and HTML

### Adding New Documentation

1. **Create Markdown File**: Add new `.md` file in appropriate directory
2. **Update Index**: Add reference in relevant README files
3. **Commit Changes**: Documentation automatically included in next build

## 🎨 Customization

### Styling
- **PDF Styling**: Modify `docs/latex/header.tex`
- **HTML Styling**: Update `docs/latex/style.css`
- **HTML Template**: Customize `docs/latex/template.html`

### Workflow Configuration
- **Retention**: Adjust artifact retention days in workflow
- **Deployment**: Configure GitHub Pages settings
- **Triggers**: Modify workflow triggers as needed

## 📋 Best Practices

### Writing Documentation
- **Clear Headers**: Use descriptive section headers
- **Code Examples**: Include practical code samples
- **Cross-References**: Link between related documents
- **Images**: Add diagrams and screenshots when helpful

### File Organization
- **Logical Structure**: Group related documents in directories
- **Consistent Naming**: Use kebab-case for file names
- **Index Files**: Maintain README files for navigation

### Version Control
- **Meaningful Commits**: Use descriptive commit messages for documentation changes
- **Review Changes**: Preview generated output before merging
- **Collaborative Editing**: Use pull requests for major documentation updates

## 🔧 Troubleshooting

### Common Issues

#### PDF Generation Fails
- **Check LaTeX Syntax**: Ensure proper escaping in markdown
- **Image Paths**: Verify all image references are correct
- **File Permissions**: Ensure all files are accessible

#### HTML Generation Issues
- **CSS Loading**: Check CSS file paths and syntax
- **Template Errors**: Validate HTML template syntax
- **Link Verification**: Ensure all internal links are valid

#### Deployment Problems
- **GitHub Pages**: Verify repository settings and permissions
- **Artifact Upload**: Check workflow permissions and retention settings
- **Branch Protection**: Ensure workflow can write to deployment branch

### Getting Help
- **Workflow Logs**: Check GitHub Actions logs for detailed error messages
- **Local Testing**: Test pandoc commands locally before committing
- **Community Support**: Reference pandoc and GitHub Actions documentation

## 📈 Metrics and Monitoring

### Documentation Quality
- **Completeness**: Ensure all features are documented
- **Accuracy**: Keep documentation synchronized with code changes
- **Accessibility**: Verify documentation is clear and comprehensive

### Workflow Performance
- **Build Times**: Monitor documentation generation duration
- **Artifact Sizes**: Track generated file sizes
- **Deployment Success**: Monitor GitHub Pages deployment status

## 🔄 Maintenance

### Regular Tasks
- **Monthly Review**: Check for outdated documentation
- **Quarterly Updates**: Update styling and templates
- **Annual Audit**: Comprehensive documentation review

### Tool Updates
- **Pandoc Version**: Keep pandoc and LaTeX packages updated
- **GitHub Actions**: Update action versions in workflow
- **Dependencies**: Monitor and update styling dependencies
