import type React from "react";
import type { IdleViewState } from ".";
import type { ViewModelParams } from "../../view-model-params";
import { goToEditSticker } from "../edit-sticker";

export function useGoToEditSticker({ setViewState }: ViewModelParams) {
  const handleClick = (
    idleState: IdleViewState,
    nodeId: string,
    e: React.MouseEvent,
  ) => {
    if (
      idleState.selectedIds.size === 1 &&
      idleState.selectedIds.has(nodeId) &&
      !e.shiftKey &&
      !e.ctrlKey
    ) {
      setViewState(goToEditSticker({ stickerId: nodeId }));
      return {
        preventNext: true,
      };
    }
    return {
      preventNext: false,
    };
  };

  const handleKeydown = (idleState: IdleViewState, e: React.KeyboardEvent) => {
    if (
      idleState.selectedIds.size === 1 &&
      !e.shiftKey &&
      !e.ctrlKey &&
      !e.altKey &&
      !e.metaKey &&
      e.key !== "Backspace" &&
      e.key !== "Delete"
    ) {
      const [id] = idleState.selectedIds.values();
      setViewState(goToEditSticker({ stickerId: id }));
      return {
        preventNext: true,
      };
    }
    return {
      preventNext: false,
    };
  };
  return {
    handleClick,
    handleKeydown,
  };
}
