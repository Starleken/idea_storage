import "react-router-dom";

export const Routes = {
  HOME: "/",
  LOGIN: "/login",
  REGISTER: "/signup",
  BOARDS: "/boards",
  BOARD: "/board/:boardId",
} as const;

export type PathParams = {
  [Routes.BOARD]: {
    boardId: string;
  };
};

declare module "react-router-dom" {
  interface Register {
    params: PathParams;
  }
}
