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

  const addSticker = ({
    text,
    x,
    y,
  }: {
    text: string;
    x: number;
    y: number;
  }) => {
    setNodes((prevNodes) => [
      ...prevNodes,
      {
        id: crypto.randomUUID(),
        type: "sticker",
        text,
        x: x,
        y: y,
      },
    ]);
  };

  return {
    nodes,
    addSticker,
  };
}

export type NodeModel = ReturnType<typeof useNodes>;
