import { getDistanceFromPoints } from "@/features/board/domain/point";
import { getPointOnScreentToCanvas } from "@/features/board/domain/screen-to-canvas";
import type { IdleViewState } from ".";
import type { ViewModelParams } from "../../view-model-params";
import { goToNodesDragging } from "../nodes-dragging";

export function useGoToNodesDragging({
  canvasRect,
  setViewState,
  windowPositionModel,
}: ViewModelParams) {
  const handleMouseMove = (idleState: IdleViewState, e: MouseEvent) => {
    if (
      !idleState.mouseDown ||
      idleState.mouseDown.type !== "node" ||
      idleState.mouseDown.isRightClick
    )
      return;
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
    const array = [idleState.mouseDown.nodeId];
    if (e.shiftKey) {
      array.push(...idleState.selectedIds);
    }
    setViewState(
      goToNodesDragging({
        start: idleState.mouseDown,
        end: currentPoint,
        nodesToMove: new Set(array),
      }),
    );
  };

  return { handleMouseMove };
}
