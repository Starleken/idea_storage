import type { Dispatch, SetStateAction } from "react";
import type { CanvasRect } from "../hooks/use-canvas-rect";
import type { NodeModel } from "../model/nodes";
import type { ViewState } from "./use-view-model";

export type ViewModelParams = {
  setViewState: Dispatch<SetStateAction<ViewState>>;
  nodeModel: NodeModel;
  canvasRect: CanvasRect | undefined;
};
