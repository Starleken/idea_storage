import { getDifferencePoints, type Point } from "../../domain/point";
import { getPointOnScreentToCanvas } from "../../domain/screen-to-canvas";
import type { Selection } from "../../domain/selection";
import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";
import { goToIdle } from "./idle";

export type WindowDraggingViewState = {
  type: "window-dragging";
  start: Point;
  end: Point;
  storedIds: Set<string>;
};

export function useWindowDraggingViewModel({
  nodeModel,
  setViewState,
  canvasRect,
  windowPositionModel,
}: ViewModelParams) {
  return (viewState: WindowDraggingViewState): ViewModel => {
    const diff = getDifferencePoints(viewState.start, viewState.end);
    return {
      nodes: nodeModel.nodes.map((node) => {
        if (viewState.storedIds.has(node.id)) {
          return {
            ...node,
            isSelected: true,
          };
        }
        return node;
      }),
      windowPosition: {
        x: windowPositionModel.position.x - diff.x,
        y: windowPositionModel.position.y - diff.y,
        zoom: windowPositionModel.position.zoom,
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
            end: currentPoint,
          });
        },
        onMouseUp() {
          windowPositionModel.setPosition({
            x: windowPositionModel.position.x - diff.x,
            y: windowPositionModel.position.y - diff.y,
            zoom: windowPositionModel.position.zoom,
          });
          setViewState(
            goToIdle({
              selectedIds: viewState.storedIds,
            }),
          );
        },
      },
    };
  };
}

export function goToWindowDragging({
  start,
  end,
  storedIds,
}: {
  start: Point;
  end: Point;
  storedIds: Selection;
}): WindowDraggingViewState {
  return {
    type: "window-dragging",
    start,
    end,
    storedIds,
  };
}
