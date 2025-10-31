import { includePointInRect, type Point } from "../../domain/point";
import { createRectFromPoints } from "../../domain/rect";
import { getPointOnScreentToCanvas } from "../../domain/screen-to-canvas";
import { selectItems, type Selection } from "../../domain/selection";
import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";
import { goToIdle } from "./idle";

export type SelectionWindowViewState = {
  type: "selection-window";
  start: Point;
  end: Point;
  initialSelectedIds: Set<string>;
};

export function useSelectionWindowViewModel({
  nodeModel,
  setViewState,
  canvasRect,
}: ViewModelParams) {
  return (viewState: SelectionWindowViewState): ViewModel => {
    const rect = createRectFromPoints(viewState.start, viewState.end);
    return {
      nodes: nodeModel.nodes.map((node) => ({
        ...node,
        isSelected:
          includePointInRect(node, rect) ||
          viewState.initialSelectedIds.has(node.id),
      })),
      selectionWindow: rect,
      window: {
        onMouseMove(e) {
          const currentPoint = getPointOnScreentToCanvas(
            {
              x: e.clientX,
              y: e.clientY,
            },
            canvasRect,
          );
          setViewState({
            ...viewState,
            end: currentPoint,
          });
        },
        onMouseUp() {
          const nodes = nodeModel.nodes
            .filter((node) => includePointInRect(node, rect))
            .map((node) => node.id);
          setViewState(
            goToIdle({
              selectedIds: selectItems(
                viewState.initialSelectedIds,
                nodes,
                "add",
              ),
            }),
          );
        },
      },
    };
  };
}

export function goToSelectionWindow({
  start,
  end,
  initialSelectedIds,
}: {
  start: Point;
  end: Point;
  initialSelectedIds?: Selection;
}): SelectionWindowViewState {
  return {
    type: "selection-window",
    start,
    end,
    initialSelectedIds: initialSelectedIds ?? new Set(),
  };
}
