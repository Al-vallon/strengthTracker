// frontend/eslint.config.js
import plugin from '@typescript-eslint/eslint-plugin';
import parser from '@typescript-eslint/parser';

export default [
    {
        files: ['src/**/*.{ts,tsx}'],
        languageOptions: {
        parser,
        parserOptions: {
            project: ['./tsconfig.json'],
            tsconfigRootDir: process.cwd(),
        },
        },
        plugins: {
        '@typescript-eslint': plugin,
        },
        rules: {
        semi: ['error', 'always'],
        quotes: ['error', 'single'],
        },
    },
];
