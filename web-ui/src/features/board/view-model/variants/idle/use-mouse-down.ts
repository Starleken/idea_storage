import { getPointOnScreentToCanvas } from "@/features/board/domain/screen-to-canvas";
import type React from "react";
import type { IdleViewState } from ".";
import type { ViewModelParams } from "../../view-model-params";

export function useMouseDown({ canvasRect, setViewState }: ViewModelParams) {
  const handleOverlayMouseDown = (
    idleState: IdleViewState,
    e: React.MouseEvent,
  ) => {
    setViewState({
      ...idleState,
      mouseDown: getPointOnScreentToCanvas(
        {
          x: e.clientX,
          y: e.clientY,
        },
        canvasRect,
      ),
    });
  };

  return { handleOverlayMouseDown };
}
