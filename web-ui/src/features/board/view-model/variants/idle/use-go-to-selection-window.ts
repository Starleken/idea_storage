import { getDistanceFromPoints } from "@/features/board/domain/point";
import { getPointOnScreentToCanvas } from "@/features/board/domain/screen-to-canvas";
import type { IdleViewState } from ".";
import type { ViewModelParams } from "../../view-model-params";
import { goToSelectionWindow } from "../selection-window";

export function useGoToSelectionWindow({
  canvasRect,
  setViewState,
}: ViewModelParams) {
  const handleMouseMove = (idleState: IdleViewState, e: MouseEvent) => {
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
  };

  return { handleMouseMove };
}
