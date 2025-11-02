import type { IdleViewState } from ".";
import type { ViewModelParams } from "../../view-model-params";

export function useDeleteSelected({
  nodeModel,
  setViewState,
}: ViewModelParams) {
  const deleteSelection = (idleState: IdleViewState) => {
    if (idleState.selectedIds.size <= 0) return;
    nodeModel.deleteNodes({ ids: idleState.selectedIds });
    setViewState({
      ...idleState,
      selectedIds: new Set(),
    });
  };

  const handleKeyDown = (idleState: IdleViewState, e: React.KeyboardEvent) => {
    if (e.code === "Delete" || e.code === "Backspace") {
      deleteSelection(idleState);
    }
  };
  return { handleKeyDown };
}
