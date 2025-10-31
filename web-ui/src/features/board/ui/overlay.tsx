import type React from "react";

export function Overlay({
  onClick,
  onMouseDown,
  onMouseUp,
}: {
  onClick?: React.MouseEventHandler;
  onMouseDown?: React.MouseEventHandler;
  onMouseUp?: React.MouseEventHandler;
}) {
  return (
    <div
      onClick={onClick}
      onMouseDown={onMouseDown}
      onMouseUp={onMouseUp}
      className="absolute inset-0"
    ></div>
  );
}
