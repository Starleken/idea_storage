import { getDistanceFromPoints } from "@/features/board/domain/point";
import { getPointOnScreentToCanvas } from "@/features/board/domain/screen-to-canvas";
import type { IdleViewState } from ".";
import type { ViewModelParams } from "../../view-model-params";
import { goToNodesDragging } from "../nodes-dragging";

export function useGoToNodesDragging({
  canvasRect,
  setViewState,
}: ViewModelParams) {
  const handleMouseMove = (idleState: IdleViewState, e: MouseEvent) => {
    if (!idleState.mouseDown || idleState.mouseDown.type !== "node") return;
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
      goToNodesDragging({
        start: idleState.mouseDown,
        end: currentPoint,
        nodesToMove: new Set([
          ...idleState.selectedIds,
          idleState.mouseDown.nodeId,
        ]),
      }),
    );
  };

  return { handleMouseMove };
}
