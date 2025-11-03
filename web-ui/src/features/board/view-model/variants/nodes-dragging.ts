import { getVectorFromPoints, type Point } from "../../domain/point";
import { getPointOnScreentToCanvas } from "../../domain/screen-to-canvas";
import { type Selection } from "../../domain/selection";
import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";
import { goToIdle } from "./idle";

export type NodesDraggingViewState = {
  type: "nodes-dragging";
  start: Point;
  end: Point;
  nodesToMove: Set<string>;
};

export function useNodesDraggingViewModel({
  nodeModel,
  setViewState,
  canvasRect,
}: ViewModelParams) {
  const getNodes = (state: NodesDraggingViewState) =>
    nodeModel.nodes.map((node) => {
      if (state.nodesToMove.has(node.id)) {
        const diff = getVectorFromPoints(state.start, state.end);

        return {
          ...node,
          x: node.x + diff.x,
          y: node.y + diff.y,
          isSelected: true,
        };
      }

      return node;
    });

  return (viewState: NodesDraggingViewState): ViewModel => {
    const nodes = getNodes(viewState);
    return {
      nodes: nodes,
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
          const nodesToMove = nodes.filter((node) =>
            viewState.nodesToMove.has(node.id),
          );
          nodeModel.updateNodesPositions(nodesToMove);
          setViewState(
            goToIdle({
              selectedIds: viewState.nodesToMove,
            }),
          );
        },
      },
    };
  };
}

export function goToNodesDragging({
  start,
  end,
  nodesToMove,
}: {
  start: Point;
  end: Point;
  nodesToMove?: Selection;
}): NodesDraggingViewState {
  return {
    type: "nodes-dragging",
    start,
    end,
    nodesToMove: nodesToMove ?? new Set(),
  };
}
