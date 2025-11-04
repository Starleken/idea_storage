import { getDifferencePoints } from "../../domain/point";
import { getPointOnScreentToCanvas } from "../../domain/screen-to-canvas";
import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";

export function useZoomDecorator({
  windowPositionModel,
  canvasRect,
}: ViewModelParams) {
  return (viewState: ViewModel): ViewModel => {
    return {
      ...viewState,
      window: {
        ...viewState.window,
        onMouseWheel(e) {
          viewState.window?.onMouseWheel?.(e);
          const delta = e.deltaY > 0 ? 0.9 : 1.1;
          const positionMouse = {
            x: e.clientX,
            y: e.clientY,
          };
          const currentPoint = getPointOnScreentToCanvas(
            positionMouse,
            windowPositionModel.position,
            canvasRect,
          );
          const newZoom = windowPositionModel.position.zoom * delta;

          const newPoint = getPointOnScreentToCanvas(
            positionMouse,
            {
              ...windowPositionModel.position,
              zoom: newZoom,
            },
            canvasRect,
          );
          const diff = getDifferencePoints(currentPoint, newPoint);
          windowPositionModel.setPosition((prev) => ({
            x: prev.x - diff.x,
            y: prev.y - diff.y,
            zoom: newZoom,
          }));
        },
      },
    };
  };
}
