import {useState} from "react";


export function useHandlerCards(setDraggedTrayCardId, transform) {
    const [cards, setCards] = useState([]);
    const removeCard = (card) => {
        const newCards = []
        for (let i = 0; i < cards.length; i++) {
            if (cards[i].id !== card.id) {
                newCards.push(cards[i])
            }
        }
        setCards(newCards)
    }

    const addDraggedTrayCardToCanvas = (
        over,
        active,
        delta
    ) => {
        setDraggedTrayCardId(null);
        if (over?.id !== "canvas") return;
        if (!active.rect.current.initial) return;
        const canvasRect = over.rect;
        const activeRect = active.rect.current.initial;
        const x = (activeRect.left - canvasRect.left + delta.x) / transform.scale - transform.x / transform.scale;
        const y = (activeRect.top - canvasRect.top + delta.y) / transform.scale - transform.y / transform.scale;
        const newCards = [...cards]
        const card = {
            id: active.id,
            coordinates: {
                x,y
            },
            type: 'image',
            text: active.id.toString()
        }
        newCards.push(card)
        setCards(
            newCards
        )
    }
    return {
        cards,
        setCards,
        removeCard,
        addDraggedTrayCardToCanvas
    }
}