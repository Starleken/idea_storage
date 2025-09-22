import js from "@eslint/js";
import globals from "globals";
import react from "eslint-plugin-react"; // 🔁 Добавь это
import reactHooks from "eslint-plugin-react-hooks";
import reactRefresh from "eslint-plugin-react-refresh";
import tseslint from "typescript-eslint";
import { eslintBoundariesConfig } from "./eslint.boundaries.js";

export default tseslint.config(
  { ignores: ["dist"] },
  {
    extends: [
      js.configs.recommended,
      ...tseslint.configs.recommended,
      react.configs.recommended, // ✅ Включаем рекомендации от react
    ],
    files: ["**/*.{ts,tsx}"],
    languageOptions: {
      ecmaVersion: 2020,
      globals: globals.browser,
      parserOptions: {
        ecmaFeatures: {
          jsx: true, // ✅ Включаем поддержку JSX
        },
        project: "./tsconfig.app.json", // ✅ Для strict type checking
      },
    },
    settings: {
      react: {
        version: "detect", // ✅ Автоопределение версии React
      },
    },
    plugins: {
      react, // ✅ Добавляем плагин
      "react-hooks": reactHooks,
      "react-refresh": reactRefresh,
    },
    rules: {
      ...reactHooks.configs.recommended.rules,
      "react-refresh/only-export-components": [
        "warn",
        { allowConstantExport: true },
      ],
      "react/prop-types": "off", // ⚠️ Выключаем, если используешь TypeScript
    },
  },
  eslintBoundariesConfig,
);
