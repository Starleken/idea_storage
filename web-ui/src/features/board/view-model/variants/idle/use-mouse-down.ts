import { getPointOnScreentToCanvas } from "@/features/board/domain/screen-to-canvas";
import type React from "react";
import type { IdleViewState } from ".";
import type { ViewModelParams } from "../../view-model-params";

export function useMouseDown({ canvasRect, setViewState }: ViewModelParams) {
  const handleOverlayMouseDown = (
    idleState: IdleViewState,
    e: React.MouseEvent,
  ) => {
    const point = getPointOnScreentToCanvas(
      {
        x: e.clientX,
        y: e.clientY,
      },
      canvasRect,
    );
    setViewState({
      ...idleState,
      mouseDown: {
        ...point,
        type: "overlay",
      },
    });
  };

  const handleNodeMouseDown = (
    idleState: IdleViewState,
    nodeId: string,
    e: React.MouseEvent,
  ) => {
    const point = getPointOnScreentToCanvas(
      {
        x: e.clientX,
        y: e.clientY,
      },
      canvasRect,
    );
    setViewState({
      ...idleState,
      mouseDown: {
        ...point,
        type: "node",
        nodeId,
      },
    });
  };

  const getIsSticketMouseDown = (idleState: IdleViewState, nodeId: string) => {
    return (
      idleState.mouseDown?.type === "node" &&
      idleState.mouseDown.nodeId === nodeId
    );
  };

  const clearMouseDown = (idleState: IdleViewState) => {
    if (!idleState.mouseDown) return;
    setViewState({
      ...idleState,
      mouseDown: undefined,
    });
  };

  return {
    handleOverlayMouseDown,
    clearMouseDown,
    handleNodeMouseDown,
    getIsSticketMouseDown,
  };
}
