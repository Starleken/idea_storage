import { useState } from "react";

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

type Node = StickerNode;

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
  ]);

  const addSticker = (data: { text: string; x: number; y: number }) => {
    setNodes((prevNodes) => [
      ...prevNodes,
      {
        id: crypto.randomUUID(),
        type: "sticker",
        ...data,
      },
    ]);
  };

  const updateSticker = (data: { id: string; text: string }) => {
    setNodes((lastNodes) =>
      lastNodes.map((node) =>
        node.id === data.id ? { ...node, text: data.text } : node,
      ),
    );
  };

  const deleteNodes = ({ ids }: { ids: Set<string> }) => {
    setNodes((lastNodes) => lastNodes.filter((node) => !ids.has(node.id)));
  };

  const updateNodesPositions = (
    positions: { id: string; x: number; y: number }[],
  ) => {
    const record = Object.fromEntries(positions.map((pos) => [pos.id, pos]));
    setNodes((lastNodes) =>
      lastNodes.map((node) => {
        const newPosition = record[node.id];
        if (newPosition) {
          return { ...node, x: newPosition.x, y: newPosition.y };
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
  };
}

export type NodeModel = ReturnType<typeof useNodes>;
