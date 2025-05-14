// frontend/eslint.config.js
export default [
    {
        files: ['src/**/*.{js,ts,jsx,tsx}'],
        languageOptions: {
        parserOptions: {
            ecmaVersion: 'latest',
            sourceType: 'module',
        },
        },
        rules: {
        semi: ['error', 'always'],
        quotes: ['error', 'single'],
        },
    },
];
