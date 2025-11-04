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
import { useWindowPositionModel } from "./model/window-position";

export function BoardPage() {
  const nodeModel = useNodes();
  const windowPositionModel = useWindowPositionModel();
  const { canvasRef, canvasRect } = useCanvasRect();
  const layoutRef = useLayoutFocus();
  const { nodeRef, nodesDimensions } = useNodesDimensions();
  const viewModel = useViewModel({
    canvasRect,
    nodeModel,
    nodesDimensions,
    windowPositionModel,
  });
  useWindowEvents(viewModel);
  const windowPosition =
    viewModel.windowPosition ?? windowPositionModel.position;
  return (
    <Layout ref={layoutRef} onKeyDown={viewModel.layout?.onKeyDown}>
      <Dots windowPosition={windowPosition} />
      <Canvas
        ref={canvasRef}
        overlay={
          <Overlay
            onClick={viewModel.overlay?.onClick}
            onMouseDown={viewModel.overlay?.onMouseDown}
            onMouseUp={viewModel.overlay?.onMouseUp}
          />
        }
        windowPosition={windowPosition}
        onClick={viewModel.canvas?.onClick}
      >
        {viewModel.nodes.map((node) => (
          <Sticker key={node.id} ref={nodeRef} {...node} />
        ))}
        {viewModel.selectionWindow && (
          <SelectionWindow {...viewModel.selectionWindow} />
        )}
      </Canvas>

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
