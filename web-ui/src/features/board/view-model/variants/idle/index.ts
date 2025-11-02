import { type Point } from "@/features/board/domain/point";
import type { ViewModelParams } from "../../view-model-params";
import type { ViewModel } from "../../view-model-types";
import { useSelection } from "./use-selection";
import { type Selection } from "@/features/board/domain/selection";
import { useDeleteSelected } from "./use-delete-selected";
import { useGoToSelectionWindow } from "./use-go-to-selection-window";
import { useMouseDown } from "./use-mouse-down";
import { useGoToAddSticker } from "./use-go-to-add-sticker";
import { useGoToEditSticker } from "./use-go-to-edit-sticker";

export type IdleViewState = {
  type: "idle";
  selectedIds: Set<string>;
  mouseDown?: Point;
};

export function useIdleViewModel(params: ViewModelParams) {
  const { setViewState, nodeModel } = params;
  const deleteSelection = useDeleteSelected(params);
  const selection = useSelection(params);
  const editSticker = useGoToEditSticker(params);
  const addSticker = useGoToAddSticker(params);
  const mouseDown = useMouseDown(params);
  const selectionWindow = useGoToSelectionWindow(params);

  return (idleState: IdleViewState): ViewModel => ({
    nodes: nodeModel.nodes.map((node) => ({
      ...node,
      isSelected: selection.isSelected(idleState, node.id),
      onClick: (e) => {
        const clickEventResult = editSticker.handleClick(idleState, node.id, e);
        if (clickEventResult.preventNext) return;
        selection.handleNodeClick(idleState, node.id, e);
      },
    })),
    layout: {
      onKeyDown(e) {
        const keyDownResult = editSticker.handleKeydown(idleState, e);
        if (keyDownResult.preventNext) return;
        addSticker.handleKeyDown(e);
        deleteSelection.handleKeyDown(idleState, e);
      },
    },
    overlay: {
      onMouseDown(e) {
        mouseDown.handleOverlayMouseDown(idleState, e);
      },
      onMouseUp() {
        selection.clearSelection(idleState);
      },
    },
    window: {
      onMouseUp() {
        setViewState({
          ...idleState,
          mouseDown: undefined,
        });
      },
      onMouseMove(e) {
        selectionWindow.handleMouseMove(idleState, e);
      },
    },
    actions: {
      addSticker: {
        onClick() {
          addSticker.moveToStickerState();
        },
        isActive: false,
      },
    },
  });
}

export function goToIdle({
  selectedIds,
}: {
  selectedIds?: Selection;
} = {}): IdleViewState {
  return {
    type: "idle",
    selectedIds: selectedIds ?? new Set(),
  };
}
