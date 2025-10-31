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
