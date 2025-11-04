import { useState } from "react";
import type { Point } from "../domain/point";

type NodeBase = {
  id: string;
  type: string;
};

type StickerNode = NodeBase & {
  type: "sticker";
  text: string;
  x: number;
  y: number;
};

type ArrowNode = NodeBase & {
  type: "arrow";
  start: Point;
  end: Point;
};

export type Node = StickerNode | ArrowNode;

export function useNodes() {
  const [nodes, setNodes] = useState<Node[]>([
    {
      id: "1",
      type: "sticker",
      text: "Hello",
      x: 100,
      y: 100,
    },
    {
      id: "2",
      type: "sticker",
      text: "Hello2",
      x: 200,
      y: 200,
    },
    {
      id: "3",
      type: "arrow",
      start: {
        x: 10,
        y: 10,
        relativeTo: "1",
      },
      end: {
        x: 20,
        y: 20,
        relativeTo: "2",
      },
    },
  ]);

  const addSticker = (data: { text: string; x: number; y: number }) => {
    const id = crypto.randomUUID();
    setNodes((prevNodes) => [
      ...prevNodes,
      {
        ...data,
        id: id,
        type: "sticker",
      },
    ]);
    return { id };
  };

  const addArrow = (data: { start: Point; end: Point }) => {
    const id = crypto.randomUUID();
    setNodes((prevNodes) => [
      ...prevNodes,
      {
        ...data,
        id: id,
        type: "arrow",
      },
    ]);
    return { id };
  };

  const updateSticker = (data: { id: string; text: string }) => {
    setNodes((lastNodes) =>
      lastNodes.map((node) =>
        node.id === data.id ? { ...node, text: data.text } : node,
      ),
    );
  };

  const deleteNodes = ({ ids }: { ids: Set<string> }) => {
    setNodes((lastNodes) => {
      const arrowsRelativeIds = lastNodes
        .filter(
          (node) =>
            (node.type === "arrow" &&
              node.start.relativeTo &&
              ids.has(node.start.relativeTo)) ||
            (node.type === "arrow" &&
              node.end.relativeTo &&
              ids.has(node.end.relativeTo)),
        )
        .map((node) => node.id);

      return lastNodes.filter(
        (node) => !ids.has(node.id) && !arrowsRelativeIds.includes(node.id),
      );
    });
  };

  const updateNodesPositions = (
    positions: {
      id: string;
      point: Point;
      type?: "start" | "end";
    }[],
  ) => {
    const record = Object.fromEntries(
      positions.map((p) => [`${p.id}${p.type ?? ""}`, p]),
    );

    setNodes((lastNodes) =>
      lastNodes.map((node) => {
        if (node.type === "arrow") {
          const newPosition = record[`${node.id}start`];
          const newEndPosition = record[`${node.id}end`];

          return {
            ...node,
            start: newPosition?.point ?? node.start,
            end: newEndPosition?.point ?? node.end,
          };
        }
        if (node.type === "sticker") {
          const newPosition = record[node.id];
          if (newPosition) {
            return { ...node, ...newPosition.point };
          }
        }

        return node;
      }),
    );
  };

  return {
    nodes,
    addSticker,
    deleteNodes,
    updateSticker,
    updateNodesPositions,
    addArrow,
  };
}

export type NodeModel = ReturnType<typeof useNodes>;
