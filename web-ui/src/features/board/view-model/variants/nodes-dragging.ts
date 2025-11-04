import {
  addPoints,
  getDifferencePoints,
  isRelativePoint,
  type Point,
} from "../../domain/point";
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
  windowPositionModel,
  canvasRect,
}: ViewModelParams) {
  const getNodes = (state: NodesDraggingViewState) => {
    return nodeModel.nodes.map((node) => {
      if (state.nodesToMove.has(node.id)) {
        const diff = getDifferencePoints(state.start, state.end);

        if (node.type === "arrow") {
          return {
            ...node,
            start: isRelativePoint(node.start)
              ? node.start
              : addPoints(node.start, diff),
            end: isRelativePoint(node.end)
              ? node.end
              : addPoints(node.end, diff),
            isSelected: true,
          };
        }

        return {
          ...node,
          ...addPoints(node, diff),
          isSelected: true,
        };
      }

      return node;
    });
  };

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
            windowPositionModel.position,
            canvasRect,
          );
          setViewState({
            ...viewState,
            end: currentPoint,
          });
        },
        onMouseUp() {
          const nodesToMove = nodes
            .filter((node) => viewState.nodesToMove.has(node.id))
            .flatMap((node) => {
              if (node.type === "arrow") {
                return [
                  {
                    id: node.id,
                    point: {
                      ...node.start,
                    },
                    type: "start" as const,
                  },
                  {
                    id: node.id,
                    point: {
                      ...node.end,
                    },
                    type: "end" as const,
                  },
                ];
              }

              return [
                {
                  id: node.id,
                  point: {
                    x: node.x,
                    y: node.y,
                  },
                },
              ];
            });
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
