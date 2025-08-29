import {useDraggable} from "@dnd-kit/core";
import {useEffect} from "react";


function Node({ card, removeCard, canvasScale, isDragging }) {
    const { attributes, listeners, setNodeRef, transform: dragTransform } = useDraggable({
        id: card.id,
    });

    const onClickHandler = () => {
        removeCard(card);
    };

    return (
        <div
            className='card'
            onDoubleClick={onClickHandler}
            style={{
                position: "absolute",
                top: `${card.coordinates.y}px`,
                left: `${card.coordinates.x}px`,
                transform: dragTransform
                    ? `translate3d(${dragTransform.x / canvasScale}px, ${dragTransform.y / canvasScale}px, 0px)`
                    : 'none',
                transformOrigin: "0 0",
                cursor: isDragging ? 'grabbing' : 'grab',
                userSelect: 'none'
            }}
            onPointerDown={(e) => {
                e.stopPropagation();
                listeners?.onPointerDown?.(e);
            }}
            ref={setNodeRef}
            {...listeners}
            {...attributes}
        >
            <img src={card.id} alt={card.id}/>
        </div>
    );
}

export default Node