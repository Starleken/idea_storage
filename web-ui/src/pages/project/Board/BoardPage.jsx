import {useState} from "react";
import Canvas from "../../../components/canvas/Canvas";
import {DndContext, DragOverlay, useDroppable} from "@dnd-kit/core";
import {Addable} from "../../../components/canvas/node/Addable";
import {useCanvasTransform} from "../../../components/canvas/hooks/useCanvasTransform";
import {trayCardsData} from "../../../api/cards";
import {useHandlerCards} from "../../../hooks/useHandlerCards";


function BoardPage() {
    const [trayCards, setTrayCards] = useState(trayCardsData)
    const [draggedTrayCardId, setDraggedTrayCardId] = useState(null)
    const {transform, updateTransform} = useCanvasTransform()

    const {cards, setCards, removeCard, addDraggedTrayCardToCanvas} = useHandlerCards(setDraggedTrayCardId, transform)

    const { setNodeRef } = useDroppable({
        id: "tray",
    });

    return (
        <DndContext
            onDragStart={({ active }) => setDraggedTrayCardId(active.id)}
            onDragEnd={({active, delta, over}) => addDraggedTrayCardToCanvas(over, active, delta)}
        >
            <div className="tray" ref={setNodeRef}>
                {trayCards.map((trayCard) => {
                    for(let i = 0; i < cards.length; i++) {
                        if (cards[i].id === trayCard.id){
                            return null;
                        }
                    }
                    return <Addable card={trayCard} key={trayCard.id} />;
                })}
            </div>
            <Canvas cards={cards} setCards={setCards} removeCard={removeCard} transform={transform} setTransform={updateTransform}/>
            <DragOverlay>
                <div style={{
                    transformOrigin: "top left",
                    transform: `scale(${transform.scale})`,
                }} className="trayOverlayCard"><img src={draggedTrayCardId}/></div>
            </DragOverlay>
        </DndContext>
    )
}

export default BoardPage