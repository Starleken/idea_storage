import type { Rect } from "./rect";

export type Point = {
  x: number;
  y: number;
};

export function getVectorFromPoints(point1: Point, point2: Point) {
  return {
    x: point2.x - point1.x,
    y: point2.y - point1.y,
  };
}

export function getDistanceFromPoints(point1: Point, point2: Point) {
  const vector = getVectorFromPoints(point1, point2);
  return Math.sqrt(vector.x ** 2 + vector.y ** 2);
}

export function includePointInRect(point: Point, rect: Rect) {
  return (
    point.x >= rect.x &&
    point.x <= rect.x + rect.width &&
    point.y >= rect.y &&
    point.y <= rect.y + rect.height
  );
}

export function includeRectInRects(rect1: Rect, rect2: Rect) {
  return (
    rect1.x <= rect2.x + rect2.width &&
    rect1.x + rect1.width >= rect2.x &&
    rect1.y <= rect2.y + rect2.height &&
    rect1.y + rect1.height >= rect2.y
  );
}
