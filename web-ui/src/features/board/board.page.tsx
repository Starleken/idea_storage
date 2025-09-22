import type { PathParams } from "@/shared/model/routes";
import { Routes } from "@/shared/model/routes";
import { useParams } from "react-router-dom";

export function BoardPage() {
  const params = useParams<PathParams[typeof Routes.BOARD]>();
  return <div>Board page with id {params.boardId}</div>;
}

export const Component = BoardPage;
