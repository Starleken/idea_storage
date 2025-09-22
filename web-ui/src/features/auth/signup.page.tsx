import { AuthLayout } from "./ui/auth-layout";
import { Link } from "react-router-dom";
import { Routes } from "@/shared/model/routes";
import { SignupForm } from "./ui/signup-form";

export function SignupPage() {
  return (
    <AuthLayout
      title="Зарегистрироваться в системе"
      description="Введите ваш email и пароль для регистрации в системе"
      form={<SignupForm />}
      footerText={
        <>
          Есть аккаунт? <Link to={Routes.LOGIN}>Авторизоваться</Link>
        </>
      }
    />
  );
}

export const Component = SignupPage;
