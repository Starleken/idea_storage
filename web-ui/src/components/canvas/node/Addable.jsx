import {useDraggable} from "@dnd-kit/core";

export const Addable = ({ card }) => {
    const { attributes, listeners, setNodeRef, isDragging} = useDraggable({
        id: card.id,
    });


    return (
        <img width={50} src={card.text} className="card" ref={setNodeRef} {...listeners} {...attributes} style={{opacity: isDragging ? 0 : 1}}>

        </img>
    );
};