import { HttpResponse } from "msw";
import type { ApiSchemas } from "../../schema";
import { http } from "../http";
import { verifyTokenOrThrow } from "../session";

const boards: ApiSchemas["Board"][] = [
  {
    id: "board-1",
    name: "Marketing company",
  },
  {
    id: "board-2",
    name: "Product roadmap",
  },
];

export const boardsHandlers = [
  http.get("/boards", async ({ request }) => {
    await verifyTokenOrThrow(request);
    return HttpResponse.json(boards);
  }),
  http.post("/boards", async ({ request }) => {
    await verifyTokenOrThrow(request);
    const data = await request.json();
    const board = {
      id: crypto.randomUUID(),
      name: data.name,
    };
    boards.push(board);
    return HttpResponse.json(board);
  }),
  http.delete("/boards/{boardId}", async ({ params, request }) => {
    await verifyTokenOrThrow(request);
    const { boardId } = params;
    const index = boards.findIndex((board) => board.id === boardId);
    if (index === -1) {
      return HttpResponse.json(
        { message: "Board not found", code: "NOT_FOUND" },
        { status: 404 },
      );
    }
    boards.splice(index, 1);
    return HttpResponse.json({ message: "Board deleted", code: "OK" });
  }),
];
