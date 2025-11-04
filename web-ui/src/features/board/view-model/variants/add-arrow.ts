import { getPointOnScreentToCanvas } from "../../domain/screen-to-canvas";
import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";
import { goToDrawArrow } from "./draw-arrow";
import { goToIdle } from "./idle";

export type AddArrowViewState = {
  type: "add-arrow";
};

export function useAddArrowViewModel({
  nodeModel,
  setViewState,
  canvasRect,
  windowPositionModel,
}: ViewModelParams) {
  const useNavigateToDrawArrow = (
    e: React.MouseEvent,
    startRelativeTo?: string,
  ) => {
    const currentPoint = getPointOnScreentToCanvas(
      {
        x: e.clientX,
        y: e.clientY,
      },
      windowPositionModel.position,
      canvasRect,
    );
    setViewState(goToDrawArrow({ startPoint: currentPoint, startRelativeTo }));
  };

  return (): ViewModel => ({
    nodes: nodeModel.nodes.map((node) => {
      if (node.type === "sticker") {
        return {
          ...node,
          onMouseDown: (e) => useNavigateToDrawArrow(e, node.id),
        };
      }
      return node;
    }),
    canvas: {
      onClick() {},
    },
    overlay: {
      onMouseDown: (e) => useNavigateToDrawArrow(e),

      onMouseUp: () => {
        setViewState(goToIdle());
      },
    },
    actions: {
      addArrow: {
        onClick() {
          setViewState(goToIdle());
        },
        isActive: true,
      },
    },
  });
}

export function goToAddArrow(): AddArrowViewState {
  return {
    type: "add-arrow",
  };
}
