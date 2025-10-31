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

  return (idleState: IdleViewState): ViewModel => ({
    nodes: nodeModel.nodes.map((node) => ({
      ...node,
      isSelected: idleState.selectedIds.has(node.id),
      onClick: (e) => {
        if (e.ctrlKey || e.shiftKey) {
          select(idleState, [node.id], "toggle");
        } else {
          select(idleState, [node.id], "replace");
        }
      },
    })),
    layout: {
      onKeyDown(e) {
        if (e.code === "KeyS") {
          setViewState(goToSticker());
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
