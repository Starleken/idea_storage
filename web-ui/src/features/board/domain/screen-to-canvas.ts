import type { Point } from "./point";

export function getPointOnScreentToCanvas(
  point: Point,
  canvasRect?: Point,
): Point {
  if (!canvasRect) return point;
  return {
    x: point.x - canvasRect.x,
    y: point.y - canvasRect.y,
  };
}
