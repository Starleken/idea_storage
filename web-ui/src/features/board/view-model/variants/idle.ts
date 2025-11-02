import { getDistanceFromPoints, type Point } from "../../domain/point";
import { getPointOnScreentToCanvas } from "../../domain/screen-to-canvas";
import {
  selectItems,
  type Selection,
  type SelectionModifier,
} from "../../domain/selection";
import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";
import { goToSticker } from "./add-sticker";
import { goToEditSticker } from "./edit-sticker";
import { goToSelectionWindow } from "./selection-window";

export type IdleViewState = {
  type: "idle";
  selectedIds: Set<string>;
  mouseDown?: Point;
};

export function useIdleViewModel({
  nodeModel,
  setViewState,
  canvasRect,
}: ViewModelParams) {
  const select = (
    lastState: IdleViewState,
    ids: string[],
    mode: SelectionModifier,
  ) => {
    setViewState({
      ...lastState,
      selectedIds: selectItems(lastState.selectedIds, ids, mode),
    });
  };

  const deleteSelection = (idleState: IdleViewState) => {
    if (idleState.selectedIds.size <= 0) return;
    nodeModel.deleteNodes({ ids: idleState.selectedIds });
    setViewState({
      ...idleState,
      selectedIds: new Set(),
    });
  };

  return (idleState: IdleViewState): ViewModel => ({
    nodes: nodeModel.nodes.map((node) => ({
      ...node,
      isSelected: idleState.selectedIds.has(node.id),
      onClick: (e) => {
        if (
          idleState.selectedIds.size === 1 &&
          idleState.selectedIds.has(node.id) &&
          !e.shiftKey &&
          !e.ctrlKey
        ) {
          setViewState(goToEditSticker({ stickerId: node.id }));
          return;
        }
        if (e.ctrlKey || e.shiftKey) {
          select(idleState, [node.id], "toggle");
        } else {
          select(idleState, [node.id], "replace");
        }
      },
    })),
    layout: {
      onKeyDown(e) {
        if (
          idleState.selectedIds.size === 1 &&
          !e.shiftKey &&
          !e.ctrlKey &&
          !e.altKey &&
          !e.metaKey
        ) {
          const [id] = idleState.selectedIds.values();
          setViewState(goToEditSticker({ stickerId: id }));
        }
        if (e.code === "KeyS") {
          setViewState(goToSticker());
        }

        if (e.code === "Delete" || e.code === "Backspace") {
          deleteSelection(idleState);
        }
      },
    },
    overlay: {
      onMouseDown(e) {
        setViewState({
          ...idleState,
          mouseDown: getPointOnScreentToCanvas(
            {
              x: e.clientX,
              y: e.clientY,
            },
            canvasRect,
          ),
        });
      },
      onMouseUp() {
        if (idleState.mouseDown) {
          setViewState({
            ...idleState,
            selectedIds: selectItems(idleState.selectedIds, [], "replace"),
          });
        }
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
        if (!idleState.mouseDown) return;
        const currentPoint = getPointOnScreentToCanvas(
          {
            x: e.clientX,
            y: e.clientY,
          },
          canvasRect,
        );
        if (getDistanceFromPoints(idleState.mouseDown, currentPoint) <= 5) {
          return;
        }
        setViewState(
          goToSelectionWindow({
            start: idleState.mouseDown,
            end: currentPoint,
            initialSelectedIds: e.shiftKey ? idleState.selectedIds : undefined,
          }),
        );
      },
    },
    actions: {
      addSticker: {
        onClick() {
          setViewState(goToSticker());
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
