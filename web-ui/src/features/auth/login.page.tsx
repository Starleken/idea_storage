import { Link } from "react-router-dom";
import { AuthLayout } from "./ui/auth-layout";
import { Routes } from "@/shared/model/routes";
import { LoginForm } from "./ui/login-form";

export function LoginPage() {
  return (
    <AuthLayout
      title="Вход в систему"
      description="Введите ваш email и пароль для входа в систему"
      form={<LoginForm />}
      footerText={
        <>
          Нет аккаунта? <Link to={Routes.REGISTER}>Зарегистрироваться</Link>
        </>
      }
    />
  );
}

export const Component = LoginPage;
