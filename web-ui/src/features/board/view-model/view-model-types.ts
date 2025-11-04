import type { Rect } from "../domain/rect";
import type { WindowPosition } from "../model/window-position";

type ViewModelNode = {
  id: string;
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
  actions?: {
    addSticker?: {
      onClick?: (e: React.MouseEvent) => void;
      isActive?: boolean;
    };
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
};
