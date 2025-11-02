import { ArrowRightIcon, StickerIcon } from "lucide-react";
import { useNodes } from "./model/nodes";
import { useCanvasRect } from "./hooks/use-canvas-rect";
import { useLayoutFocus } from "./hooks/use-layout-focus";
import { useViewModel } from "./view-model/use-view-model";
import { useWindowEvents } from "./hooks/use-windows-events";
import { SelectionWindow } from "./ui/selection-window";
import { Layout } from "./ui/layout";
import { Dots } from "./ui/dots";
import { Overlay } from "./ui/overlay";
import { Canvas } from "./ui/canvas";
import { Sticker } from "./ui/sticker";
import { Actions } from "./ui/actions";
import { ActionButton } from "./ui/action-button";
import { useNodesDimensions } from "./hooks/use-nodes-dimensions";

export function BoardPage() {
  const nodeModel = useNodes();
  const { canvasRef, canvasRect } = useCanvasRect();
  const layoutRef = useLayoutFocus();
  const { nodeRef, nodesDimensions } = useNodesDimensions();
  const viewModel = useViewModel({
    canvasRect,
    nodeModel,
    nodesDimensions,
  });
  useWindowEvents(viewModel);

  return (
    <Layout ref={layoutRef} onKeyDown={viewModel.layout?.onKeyDown}>
      <Dots />
      <Canvas ref={canvasRef} onClick={viewModel.canvas?.onClick}>
        <Overlay
          onClick={viewModel.overlay?.onClick}
          onMouseDown={viewModel.overlay?.onMouseDown}
          onMouseUp={viewModel.overlay?.onMouseUp}
        />
        {viewModel.nodes.map((node) => (
          <Sticker key={node.id} ref={nodeRef} {...node} />
        ))}
      </Canvas>
      {viewModel.selectionWindow && (
        <SelectionWindow {...viewModel.selectionWindow} />
      )}
      <Actions>
        <ActionButton
          isActive={viewModel.actions?.addSticker?.isActive}
          onClick={viewModel.actions?.addSticker?.onClick}
        >
          <StickerIcon />
        </ActionButton>
        <ActionButton isActive={false} onClick={(e) => console.log(e)}>
          <ArrowRightIcon />
        </ActionButton>
      </Actions>
    </Layout>
  );
}

export const Component = BoardPage;
