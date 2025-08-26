import {DndContext, useDroppable} from "@dnd-kit/core";
import Node from "./node/Node"
import {useCallback, useEffect, useLayoutEffect, useMemo, useRef, useState} from "react";
import Toolbar from "./toolbar/Toolbar";
import {useCanvasInteractions} from "./hooks/useCanvasInteractions";
import {useCanvasDnD} from "./hooks/useCanvasDnD";


function Canvas({cards = [], setCards, removeCard, transform, setTransform}) {
    const { setNodeRef } = useDroppable({
        id: "canvas",
    });
    const lastMousePos = useRef({ x: 0, y: 0 });
    const { isPanning, ...interactionHandlers } = useCanvasInteractions(transform, setTransform);
    const { isDragging, saveDragEndPosition, setIsDragging } =
        useCanvasDnD(cards, setCards, transform);
    const canvasRef = useRef(null);
    const containerRef = useRef(null);

    useLayoutEffect(() => {
        const container = containerRef.current;
        if (!container) return;

        const { handleWheel, handleMouseDown, handleMouseMove, handleMouseUp } = interactionHandlers;

        container.addEventListener('wheel', handleWheel, { passive: false });
        container.addEventListener('mousedown', handleMouseDown);
        document.addEventListener('mousemove', handleMouseMove);
        document.addEventListener('mouseup', handleMouseUp);

        return () => {
            container.removeEventListener('wheel', handleWheel);
            container.removeEventListener('mousedown', handleMouseDown);
            document.removeEventListener('mousemove', handleMouseMove);
            document.removeEventListener('mouseup', handleMouseUp);
        };
    }, [interactionHandlers]);

    useLayoutEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) return;

        const { handleWheel, handleMouseDown } = interactionHandlers;

        canvas.addEventListener('wheel', handleWheel, { passive: false });
        canvas.addEventListener('mousedown', handleMouseDown);

        return () => {
            canvas.removeEventListener('wheel', handleWheel);
            canvas.removeEventListener('mousedown', handleMouseDown);
        };
    }, [interactionHandlers]);

    const handleContextMenu = useCallback((e) => {
        e.preventDefault();
    }, []);
    const setRef = (div) => {
        canvasRef.current = div
        setNodeRef(div)
    }

    return (
        <div
            ref={containerRef}
            className="canvas-container"
            style={{
                width: '100%',
                height: '100%',
                overflow: 'hidden',
                cursor: isPanning ? 'grabbing' : 'default',
                position: 'relative',
                backgroundPositionY: transform.y,
                backgroundPositionX: transform.x,
                backgroundSize: `auto ${transform.scale*100}%`,
            }}
            onContextMenu={handleContextMenu}
        >
            <div
                className="canvas"
                ref={setRef}
                style={{
                    transformOrigin: '0 0',
                    transform: `translate(${transform.x}px, ${transform.y}px) scale(${transform.scale})`,
                    width: '100%',
                    height: '100%',
                    position: 'absolute',
                    top: 0,
                    left: 0
                }}
            >
                <DndContext
                    onDragStart={() => setIsDragging(true)}
                    onDragEnd={({ delta, active }) => {
                        saveDragEndPosition(active, delta);
                    }}
                    onDragCancel={() => setIsDragging(false)}
                >
                    {cards.map(card => (
                        <Node
                            card={card}
                            key={card.id}
                            removeCard={removeCard}
                            canvasScale={transform.scale}
                            isDragging={isDragging}
                        />
                    ))}
                </DndContext>
            </div>
            <Toolbar
                transform={transform}
                setTransform={setTransform}
            />
        </div>
    );
};

export default Canvas;