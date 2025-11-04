import type { Rect } from "./rect";

export type Point = {
  x: number;
  y: number;
  relativeTo?: string;
};

export type RelativePoint = Point & {
  relativeTo: string;
};

export function getDifferencePoints(point1: Point, point2: Point) {
  return {
    x: point2.x - point1.x,
    y: point2.y - point1.y,
  };
}

export function getDistanceFromPoints(point1: Point, point2: Point) {
  const vector = getDifferencePoints(point1, point2);
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

export function createRectFromDimensions(
  point: Point,
  dimensions: { width: number; height: number },
): Rect {
  return {
    x: point.x,
    y: point.y,
    width: dimensions.width,
    height: dimensions.height,
  };
}

export function addPoints(point1: Point, point2: Point): Point {
  return {
    x: point1.x + point2.x,
    y: point1.y + point2.y,
  };
}

export type RelativeBase = Record<string, Point>;

export function resolveRelativePoint(base: RelativeBase, point: Point): Point {
  let relativeTo = point.relativeTo;
  let newPoint = point;
  while (relativeTo) {
    const basePoint = base[relativeTo];

    if (basePoint) {
      newPoint = addPoints(newPoint, basePoint);
    }

    relativeTo = basePoint?.relativeTo;
  }
  return newPoint;
}

export function isRelativePoint(point: Point): point is RelativePoint {
  return "relativeTo" in point;
}
