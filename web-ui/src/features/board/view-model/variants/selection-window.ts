import { includeRectInRects, type Point } from "../../domain/point";
import { createRectFromPoints, type Rect } from "../../domain/rect";
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
  nodesDimensions,
}: ViewModelParams) {
  const getNodes = (state: SelectionWindowViewState, selectionRect: Rect) =>
    nodeModel.nodes.map((node) => {
      const nodeDimension = nodesDimensions[node.id];
      const nodeRect = {
        x: node.x,
        y: node.y,
        width: nodeDimension.width,
        height: nodeDimension.height,
      };
      return {
        ...node,
        isSelected:
          includeRectInRects(nodeRect, selectionRect) ||
          state.initialSelectedIds.has(node.id),
      };
    });

  return (viewState: SelectionWindowViewState): ViewModel => {
    const rect = createRectFromPoints(viewState.start, viewState.end);
    const nodes = getNodes(viewState, rect);
    return {
      nodes: nodes,
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
          const nodesIdsInRect = nodes
            .filter((x) => x.isSelected)
            .map((node) => node.id);
          setViewState(
            goToIdle({
              selectedIds: selectItems(
                viewState.initialSelectedIds,
                nodesIdsInRect,
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
