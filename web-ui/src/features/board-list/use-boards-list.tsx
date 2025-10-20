import { rqClient } from "@/shared/api/instance";
import { useCallback, type RefCallback } from "react";

type useBoardsListParams = {
  limit?: number;
  isFavorite?: boolean;
  search?: string;
  sort?: "createdAt" | "updatedAt" | "lastOpenedAt" | "name";
};

export function useBoardsList({
  limit = 10,
  search,
  isFavorite,
  sort,
}: useBoardsListParams) {
  const { data, fetchNextPage, isFetchingNextPage, isPending, hasNextPage } =
    rqClient.useInfiniteQuery(
      "get",
      "/boards",
      {
        params: {
          query: {
            page: 1,
            limit,
            search,
            isFavorite,
            sort,
          },
        },
      },
      {
        initialPageParam: 1,
        pageParamName: "page",
        getNextPageParam: (lastPage: any, _: any, lastPageParams: any) =>
          Number(lastPageParams) < lastPage.totalPages
            ? Number(lastPageParams) + 1
            : null,
      },
    );

  const cursorRef: RefCallback<HTMLDivElement> = useCallback(
    (el) => {
      const observer = new IntersectionObserver(
        (entries) => {
          if (entries[0].isIntersecting) {
            fetchNextPage();
          }
        },
        { threshold: 0.5 },
      );
      if (el) {
        observer.observe(el);
        return () => {
          observer.disconnect();
        };
      }
    },
    [fetchNextPage],
  );
  const boards = data?.pages.flatMap((page) => page.list) ?? [];
  return {
    boards,
    cursorRef,
    isFetchingNextPage,
    isPending,
    hasNextPage,
  };
}
