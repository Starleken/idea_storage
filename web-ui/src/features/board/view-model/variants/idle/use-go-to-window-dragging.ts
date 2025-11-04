import { getDistanceFromPoints } from "@/features/board/domain/point";
import { getPointOnScreentToCanvas } from "@/features/board/domain/screen-to-canvas";
import type { IdleViewState } from ".";
import type { ViewModelParams } from "../../view-model-params";
import { goToWindowDragging } from "../window-dragging";

export function useGoToWindowDragging({
  canvasRect,
  setViewState,
  windowPositionModel,
}: ViewModelParams) {
  const handleMouseMove = (idleState: IdleViewState, e: MouseEvent) => {
    if (!idleState.mouseDown || !idleState.mouseDown.isRightClick) return;
    console.log("window-dragging handler");
    const currentPoint = getPointOnScreentToCanvas(
      {
        x: e.clientX,
        y: e.clientY,
      },
      windowPositionModel.position,
      canvasRect,
    );
    if (getDistanceFromPoints(idleState.mouseDown, currentPoint) <= 5) {
      return;
    }
    setViewState(
      goToWindowDragging({
        start: idleState.mouseDown,
        end: currentPoint,
        storedIds: idleState.selectedIds,
      }),
    );
  };

  return { handleMouseMove };
}
