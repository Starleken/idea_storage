import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";
import { goToIdle } from "./idle";

export type EditStickerViewState = {
  type: "edit-sticker";
  stickerId: string;
  newText?: string;
};

export function useEditStickerViewModel({
  nodeModel,
  setViewState,
}: ViewModelParams) {
  return (viewState: EditStickerViewState): ViewModel => ({
    nodes: nodeModel.nodes.map((node) => {
      if (node.id === viewState.stickerId && node.type === "sticker") {
        return {
          ...node,
          isSelected: true,
          isEditing: true,
          text: viewState.newText ?? node.text,
          onTextChange(text) {
            setViewState((lastState) => ({
              ...lastState,
              newText: text,
            }));
          },
        };
      }
      return node;
    }),
    layout: {
      onKeyDown(e) {
        if (e.code === "Escape") {
          setViewState(goToIdle());
        }
        if (e.code === "Enter" && !e.shiftKey) {
          if (viewState.newText) {
            nodeModel.updateSticker({
              id: viewState.stickerId,
              text: viewState.newText,
            });
          }
          setViewState(goToIdle());
        }
      },
    },
    overlay: {
      onClick() {
        if (viewState.newText) {
          nodeModel.updateSticker({
            id: viewState.stickerId,
            text: viewState.newText,
          });
        }

        setViewState(goToIdle());
      },
    },
  });
}

export function goToEditSticker({
  stickerId,
}: {
  stickerId: string;
}): EditStickerViewState {
  return {
    type: "edit-sticker",
    stickerId,
  };
}
