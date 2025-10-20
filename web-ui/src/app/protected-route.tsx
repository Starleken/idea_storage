import { enableMocking } from "@/shared/api/mocks";
import { Routes } from "@/shared/model/routes";
import { useSession } from "@/shared/model/session";
import { Navigate, Outlet, redirect } from "react-router-dom";

export function ProtectedRoute() {
  const { session } = useSession();
  if (!session) {
    return <Navigate to={Routes.LOGIN} />;
  }

  return <Outlet />;
}

export async function protectedLoader() {
  await enableMocking();
  const token = await useSession.getState().refreshToken();
  if (!token) {
    return redirect(Routes.LOGIN);
  }

  return null;
}
