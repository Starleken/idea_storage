import { getPointOnScreentToCanvas } from "../../domain/screen-to-canvas";
import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";
import { goToEditSticker } from "./edit-sticker";
import { goToIdle } from "./idle";

export type AddStickerViewState = {
  type: "add-sticker";
};

export function useAddStickerViewModel({
  nodeModel,
  setViewState,
  canvasRect,
  windowPositionModel,
}: ViewModelParams) {
  return (): ViewModel => ({
    nodes: nodeModel.nodes,
    canvas: {
      onClick(e) {
        const currentPoint = getPointOnScreentToCanvas(
          {
            x: e.clientX,
            y: e.clientY,
          },
          windowPositionModel.position,
          canvasRect,
        );
        const node = nodeModel.addSticker({
          text: "",
          ...currentPoint,
        });
        setViewState(
          goToEditSticker({
            stickerId: node.id,
          }),
        );
      },
    },
    actions: {
      addSticker: {
        onClick() {
          setViewState(goToIdle());
        },
        isActive: true,
      },
    },
  });
}

export function goToSticker(): AddStickerViewState {
  return {
    type: "add-sticker",
  };
}
