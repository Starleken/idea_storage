import type { Rect } from "../domain/rect";

export function SelectionWindow({ width, height, x, y }: Rect) {
  return (
    <div
      className="absolute inset-0 bg-blue-500/30 outline outline-2 outline-blue-500"
      style={{
        transform: `translate(${x}px, ${y}px)`,
        width: `${width}px`,
        height: `${height}px`,
      }}
    ></div>
  );
}
