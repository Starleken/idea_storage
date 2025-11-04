import type React from "react";
import type { ViewModelParams } from "../../view-model-params";
import { goToSticker } from "../add-sticker";

export function useGoToAddSticker({ setViewState }: ViewModelParams) {
  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.code === "KeyS") {
      setViewState(goToSticker());
    }
  };

  const moveToStickerState = () => {
    setViewState(goToSticker());
  };

  return { handleKeyDown, moveToStickerState };
}
