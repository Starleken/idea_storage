import { AppHeader } from "@/features/header";
import { Routes } from "@/shared/model/routes";
import { Outlet, useLocation } from "react-router-dom";

export function App() {
  const location = useLocation();

  const isAuthPage =
    location.pathname === Routes.LOGIN || location.pathname === Routes.REGISTER;

  return (
    <div className="min-h-screen flex flex-col">
      {!isAuthPage && <AppHeader />}
      <Outlet />
    </div>
  );
}
