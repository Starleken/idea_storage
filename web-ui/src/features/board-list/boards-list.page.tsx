import { rqClient } from "@/shared/api/instance";
import { Routes } from "@/shared/model/routes";
import { Button } from "@/shared/ui/kit/button";
import { Card, CardContent, CardHeader } from "@/shared/ui/kit/card";
import { useQueryClient } from "@tanstack/react-query";
import { href, Link } from "react-router-dom";

export function BoardsListPage() {
  const queryClient = useQueryClient();
  const boardsQuery = rqClient.useQuery("get", "/boards");
  const createBoardMutation = rqClient.useMutation("post", "/boards", {
    onSettled: async () => {
      await queryClient.invalidateQueries(
        rqClient.queryOptions("get", "/boards"),
      );
    },
  });
  const deleteBoardMutation = rqClient.useMutation(
    "delete",
    "/boards/{boardId}",
    {
      onSettled: async () => {
        await queryClient.invalidateQueries(
          rqClient.queryOptions("get", "/boards"),
        );
      },
    },
  );
  return (
    <div className="container mx-auto p-4">
      <form
        onSubmit={(e) => {
          e.preventDefault();
          const formData = new FormData(e.target as HTMLFormElement);
          createBoardMutation.mutate({
            body: {
              name: formData.get("name") as string,
            },
          });
        }}
      >
        <input type="text" name="name" />
        <Button variant="default" disabled={createBoardMutation.isPending}>
          Submit
        </Button>
      </form>
      <h1>Boards list</h1>
      <div className="grid grid-cols-3 gap-4">
        {boardsQuery.data?.map((board) => (
          <Card key={board.id}>
            <CardHeader>
              <Button asChild variant="link">
                <Link to={href(Routes.BOARD, { boardId: board.id })}>
                  {board.name}
                </Link>
              </Button>
            </CardHeader>
            <CardContent>
              <Button
                variant="destructive"
                disabled={deleteBoardMutation.isPending}
                onClick={() =>
                  deleteBoardMutation.mutate({
                    params: { path: { boardId: board.id } },
                  })
                }
              >
                Delete
              </Button>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
}

export const Component = BoardsListPage;
