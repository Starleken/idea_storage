import type { ViewModelParams } from "../../view-model-params";
import type { ViewModel } from "../../view-model-types";
import { useSelection } from "./use-selection";
import { type Selection } from "@/features/board/domain/selection";
import { useDeleteSelected } from "./use-delete-selected";
import { useGoToSelectionWindow } from "./use-go-to-selection-window";
import { useMouseDown } from "./use-mouse-down";
import { useGoToAddSticker } from "./use-go-to-add-sticker";
import { useGoToEditSticker } from "./use-go-to-edit-sticker";
import { useGoToNodesDragging } from "./use-go-to-nodes-dragging";
import { useGoToWindowDragging } from "./use-go-to-window-dragging";

export type IdleViewState = {
  type: "idle";
  selectedIds: Set<string>;
  mouseDown?:
    | {
        type: "overlay";
        x: number;
        y: number;
        isRightClick: boolean;
      }
    | {
        type: "node";
        x: number;
        y: number;
        nodeId: string;
        isRightClick: boolean;
      };
};

export function useIdleViewModel(params: ViewModelParams) {
  const { nodeModel } = params;
  const deleteSelection = useDeleteSelected(params);
  const selection = useSelection(params);
  const editSticker = useGoToEditSticker(params);
  const addSticker = useGoToAddSticker(params);
  const mouseDown = useMouseDown(params);
  const selectionWindow = useGoToSelectionWindow(params);
  const nodesDragging = useGoToNodesDragging(params);
  const windowDragging = useGoToWindowDragging(params);

  return (idleState: IdleViewState): ViewModel => ({
    nodes: nodeModel.nodes.map((node) => ({
      ...node,
      isSelected: selection.isSelected(idleState, node.id),
      onMouseDown: (e) => mouseDown.handleNodeMouseDown(idleState, node.id, e),
      onMouseUp: (e) => {
        if (!mouseDown.getIsSticketMouseDown(idleState, node.id)) {
          return;
        }
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
      onMouseDown: (e) => mouseDown.handleOverlayMouseDown(idleState, e),
      onMouseUp: () => selection.clearSelection(idleState),
    },
    window: {
      onMouseUp: () => mouseDown.clearMouseDown(idleState),
      onMouseMove: (e) => {
        nodesDragging.handleMouseMove(idleState, e);
        selectionWindow.handleMouseMove(idleState, e);
        windowDragging.handleMouseMove(idleState, e);
      },
    },
    actions: {
      addSticker: {
        onClick: () => addSticker.moveToStickerState(),
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
