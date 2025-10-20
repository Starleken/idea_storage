import { rqClient } from "@/shared/api/instance";
import { useQueryClient } from "@tanstack/react-query";

export function useUpdateFavoriteBoard() {
  const queryClient = useQueryClient();
  const toggleFavoriteMutation = rqClient.useMutation(
    "put",
    "/boards/{boardId}/favorite",
    {
      onSettled: async () => {
        await queryClient.invalidateQueries(
          rqClient.queryOptions("get", "/boards"),
        );
      },
    },
  );
  return {
    toggleFavorite: (boardId: string, isFavorite: boolean) =>
      toggleFavoriteMutation.mutate({
        params: { path: { boardId } },
        body: { isFavorite },
      }),
    isPending: toggleFavoriteMutation.isPending,
  };
}
