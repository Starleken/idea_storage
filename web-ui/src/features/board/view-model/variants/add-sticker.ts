import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";
import { goToIdle } from "./idle";

export type AddStickerViewState = {
  type: "add-sticker";
};

export function useAddStickerViewModel({
  nodeModel,
  setViewState,
  canvasRect,
}: ViewModelParams) {
  return (): ViewModel => ({
    nodes: nodeModel.nodes,
    canvas: {
      onClick(e) {
        if (!canvasRect) return;
        nodeModel.addSticker({
          text: "Default",
          x: e.clientX - canvasRect.x,
          y: e.clientY - canvasRect.y,
        });
        setViewState(goToIdle());
      },
    },
    layout: {
      onKeyDown(e) {
        if (e.code === "Escape") {
          setViewState(goToIdle());
        }
        if (e.code === "KeyS") {
          setViewState(goToIdle());
        }
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
