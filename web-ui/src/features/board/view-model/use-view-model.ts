import {
  goToIdle,
  useIdleViewModel,
  type IdleViewState,
} from "./variants/idle";

import type { ViewModel } from "./view-model-types";
import type { ViewModelParams } from "./view-model-params";
import { useState } from "react";
import {
  useSelectionWindowViewModel,
  type SelectionWindowViewState,
} from "./variants/selection-window";
import {
  useEditStickerViewModel,
  type EditStickerViewState,
} from "./variants/edit-sticker";
import {
  useAddStickerViewModel,
  type AddStickerViewState,
} from "./variants/add-sticker";
import {
  useNodesDraggingViewModel,
  type NodesDraggingViewState,
} from "./variants/nodes-dragging";

export type ViewState =
  | IdleViewState
  | EditStickerViewState
  | AddStickerViewState
  | SelectionWindowViewState
  | NodesDraggingViewState;

export function useViewModel(params: Omit<ViewModelParams, "setViewState">) {
  const [viewState, setViewState] = useState<ViewState>(() => goToIdle());

  const newParams = {
    ...params,
    setViewState,
  };

  const states = {
    idle: useIdleViewModel(newParams),
    "add-sticker": useAddStickerViewModel(newParams),
    "selection-window": useSelectionWindowViewModel(newParams),
    "edit-sticker": useEditStickerViewModel(newParams),
    "nodes-dragging": useNodesDraggingViewModel(newParams),
  };
  let viewModel: ViewModel;
  switch (viewState.type) {
    case "add-sticker":
      viewModel = states["add-sticker"]();
      break;
    case "edit-sticker":
      viewModel = states["edit-sticker"](viewState);
      break;
    case "idle":
      viewModel = states["idle"](viewState);
      break;
    case "selection-window":
      viewModel = states["selection-window"](viewState);
      break;
    case "nodes-dragging":
      console.log(viewState);
      viewModel = states["nodes-dragging"](viewState);
      break;
    default:
      throw new Error("Invalid view state");
  }
  return viewModel;
}
