import { createBrowserRouter, redirect } from "react-router-dom";
import { App } from "./app";
import { Routes } from "@/shared/model/routes";
import { Providers } from "./providers";
import { protectedLoader, ProtectedRoute } from "./protected-route";
import { AppHeader } from "@/features/header";

export const router = createBrowserRouter([
  {
    element: (
      <Providers>
        <App />
      </Providers>
    ),
    children: [
      {
        loader: protectedLoader,
        element: (
          <>
            <AppHeader />
            <ProtectedRoute />
          </>
        ),
        children: [
          {
            path: Routes.BOARDS,
            lazy: () => import("@/features/board-list/boards-list.page"),
          },
          {
            path: Routes.BOARD,
            lazy: () => import("@/features/board/board.page"),
          },
        ],
      },
      {
        path: Routes.LOGIN,
        lazy: () => import("@/features/auth/login.page"),
      },
      {
        path: Routes.REGISTER,
        lazy: () => import("@/features/auth/signup.page"),
      },
      {
        path: Routes.HOME,
        loader: () => redirect(Routes.BOARDS),
      },
    ],
  },
]);
