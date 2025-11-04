import type { Point } from "../domain/point";
import type { Rect } from "../domain/rect";
import type { WindowPosition } from "../model/window-position";

type ViewModelStickerNode = {
  id: string;
  type: "sticker";
  text: string;
  x: number;
  y: number;
  isSelected?: boolean;
  isEditing?: boolean;
  onClick?: (e: React.MouseEvent) => void;
  onTextChange?: (text: string) => void;
  onMouseDown?: (e: React.MouseEvent) => void;
  onMouseUp?: (e: React.MouseEvent) => void;
};

type ViewModelArrowNode = {
  id: string;
  type: "arrow";
  start: Point;
  end: Point;
  isSelected?: boolean;
  noPointerEvents?: boolean;
  onClick?: (e: React.MouseEvent) => void;
  onMouseDown?: (e: React.MouseEvent) => void;
  onMouseUp?: (e: React.MouseEvent) => void;
};

type ViewModelNode = ViewModelStickerNode | ViewModelArrowNode;

export type ViewModel = {
  nodes: ViewModelNode[];
  selectionWindow?: Rect;
  windowPosition?: WindowPosition;
  layout?: {
    onKeyDown?: (e: React.KeyboardEvent) => void;
  };
  canvas?: {
    onClick?: (e: React.MouseEvent) => void;
  };
  overlay?: {
    onClick?: (e: React.MouseEvent) => void;
    onMouseDown?: (e: React.MouseEvent) => void;
    onMouseUp?: (e: React.MouseEvent) => void;
  };
  window?: {
    onMouseUp?: (e: MouseEvent) => void;
    onMouseMove?: (e: MouseEvent) => void;
    onMouseWheel?: (e: WheelEvent) => void;
  };
  actions?: {
    addSticker?: ViewModelAction;
    addArrow?: ViewModelAction;
  };
};

export type ViewModelAction = {
  onClick?: (e: React.MouseEvent) => void;
  isActive?: boolean;
};
