import { rqClient } from "@/shared/api/instance";
import type { ApiSchemas } from "@/shared/api/schema";
import { Routes } from "@/shared/model/routes";
import { useSession } from "@/shared/model/session";
import { useNavigate } from "react-router-dom";

export function useLogin() {
  const navigate = useNavigate();
  const session = useSession();
  const loginMutation = rqClient.useMutation("post", "/auth/login", {
    onSuccess(data) {
      session.saveToken(data.accessToken);
      navigate(Routes.HOME);
    },
  });

  const login = (data: ApiSchemas["LoginRequest"]) => {
    loginMutation.mutate({ body: data });
  };

  const errorMessage = loginMutation.isError
    ? loginMutation.error.message
    : undefined;

  return {
    login,
    isPending: loginMutation.isPending,
    errorMessage,
  };
}
