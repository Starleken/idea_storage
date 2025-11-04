import { getDifferencePoints, type Point } from "../../domain/point";
import { getPointOnScreentToCanvas } from "../../domain/screen-to-canvas";
import { createRelativeBase } from "../decorator/resolve-relative";
import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";
import { goToIdle } from "./idle";

export type DrawArrowViewState = {
  type: "draw-arrow";
  startPoint: Point;
  endPoint: Point;
  startRelativeTo?: string;
};

export function useDrawArrowViewModel({
  nodeModel,
  setViewState,
  windowPositionModel,
  canvasRect,
}: ViewModelParams) {
  const addArrow = (viewState: DrawArrowViewState, endRelativeTo?: string) => {
    const relativeBase = createRelativeBase(nodeModel.nodes);

    nodeModel.addArrow({
      start: viewState.startRelativeTo
        ? {
            ...getDifferencePoints(
              relativeBase[viewState.startRelativeTo],
              viewState.startPoint,
            ),
            relativeTo: viewState.startRelativeTo,
          }
        : viewState.startPoint,
      end: endRelativeTo
        ? {
            ...getDifferencePoints(
              relativeBase[endRelativeTo],
              viewState.endPoint,
            ),
            relativeTo: endRelativeTo,
          }
        : viewState.endPoint,
    });
  };
  return (viewState: DrawArrowViewState): ViewModel => {
    const newArrow = {
      id: "drawwing-arrow",
      type: "arrow" as const,
      start: viewState.startPoint,
      end: viewState.endPoint,
      noPointersEvents: true,
    };

    const newNodes = [...nodeModel.nodes, newArrow];
    return {
      nodes: newNodes.map((node) => {
        if (node.type === "sticker") {
          return {
            ...node,
            onMouseUp: () => {
              addArrow(viewState, node.id);
            },
          };
        }
        return node;
      }),
      overlay: {
        onMouseUp: () => {
          addArrow(viewState);
        },
      },
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
            endPoint: currentPoint,
          });
        },
        onMouseUp: () => {
          setViewState(goToIdle());
        },
      },
      actions: {
        addArrow: {
          isActive: true,
        },
      },
    };
  };
}

export function goToDrawArrow({
  startPoint,
  startRelativeTo,
}: {
  startPoint: Point;
  startRelativeTo?: string;
}): DrawArrowViewState {
  return {
    type: "draw-arrow",
    startPoint,
    endPoint: startPoint,
    startRelativeTo,
  };
}
