import { goToAddArrow } from "../variants/add-arrow";
import { goToSticker } from "../variants/add-sticker";
import { goToIdle } from "../variants/idle";
import type { ViewModelParams } from "../view-model-params";
import type { ViewModel } from "../view-model-types";

export function useCommonActionsDecorator({ setViewState }: ViewModelParams) {
  return (viewState: ViewModel): ViewModel => {
    return {
      ...viewState,
      layout: {
        ...viewState.layout,
        onKeyDown: (e) => {
          viewState.layout?.onKeyDown?.(e);
          if (e.code === "Escape") {
            setViewState(goToIdle());
          }
          if (e.code === "KeyS" && !viewState.actions?.addSticker?.isActive) {
            setViewState(goToSticker());
          }
          if (e.code === "KeyA" && !viewState.actions?.addArrow?.isActive) {
            setViewState(goToAddArrow());
          }
        },
      },
      actions: {
        addArrow: {
          isActive: false,
          onClick: () => {
            setViewState(goToAddArrow());
          },
        },
        addSticker: {
          isActive: false,
          onClick: () => {
            setViewState(goToSticker());
          },
        },
        ...viewState.actions,
      },
    };
  };
}
