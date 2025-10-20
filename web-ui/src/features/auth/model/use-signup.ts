import { publicRqClient } from "@/shared/api/instance";
import type { ApiSchemas } from "@/shared/api/schema";
import { Routes } from "@/shared/model/routes";
import { useSession } from "@/shared/model/session";
import { useNavigate } from "react-router-dom";

export function useSignup() {
  const navigate = useNavigate();
  const session = useSession();
  const loginMutation = publicRqClient.useMutation("post", "/auth/register", {
    onSuccess({ accessToken }) {
      session.login(accessToken);
      navigate(Routes.HOME);
    },
  });

  const signup = (data: ApiSchemas["RegisterRequest"]) => {
    loginMutation.mutate({ body: data });
  };

  const errorMessage = loginMutation.isError
    ? loginMutation.error.message
    : undefined;

  return {
    signup,
    isPending: loginMutation.isPending,
    errorMessage,
  };
}
