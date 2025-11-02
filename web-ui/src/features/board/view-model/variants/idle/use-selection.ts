import {
  selectItems,
  type SelectionModifier,
} from "@/features/board/domain/selection";
import type { IdleViewState } from ".";
import type { ViewModelParams } from "../../view-model-params";
import type React from "react";

export function useSelection({ setViewState }: ViewModelParams) {
  const select = (
    lastState: IdleViewState,
    ids: string[],
    mode: SelectionModifier,
  ) =>
    setViewState({
      ...lastState,
      selectedIds: selectItems(lastState.selectedIds, ids, mode),
    });
  const handleNodeClick = (
    idleState: IdleViewState,
    nodeId: string,
    e: React.MouseEvent,
  ) => {
    if (e.ctrlKey || e.shiftKey) {
      select(idleState, [nodeId], "toggle");
    } else {
      select(idleState, [nodeId], "replace");
    }
  };

  const isSelected = (idleState: IdleViewState, nodeId: string) =>
    idleState.selectedIds.has(nodeId);

  const clearSelection = (idleState: IdleViewState) => {
    if (idleState.mouseDown) {
      setViewState({
        ...idleState,
        selectedIds: selectItems(idleState.selectedIds, [], "replace"),
      });
    }
  };
  return { handleNodeClick, clearSelection, isSelected };
}
