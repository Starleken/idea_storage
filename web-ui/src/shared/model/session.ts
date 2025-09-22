import { useState } from "react";
import { jwtDecode } from "jwt-decode";
import { createGStore } from "create-gstore";

type Session = {
  userId: string;
  email: string;
  exp: number;
  iat: number;
};

const TOKEN_KEY = "token";

export const useSession = createGStore(() => {
  const [token, setToken] = useState(() => localStorage.getItem(TOKEN_KEY));
  const saveToken = (token: string) => {
    localStorage.setItem(TOKEN_KEY, token);
    setToken(token);
  };
  const removeToken = () => {
    localStorage.removeItem(TOKEN_KEY);
    setToken(null);
  };

  const session: Session | null = token ? jwtDecode<Session>(token) : null;

  return {
    saveToken,
    removeToken,
    session,
  };
});
