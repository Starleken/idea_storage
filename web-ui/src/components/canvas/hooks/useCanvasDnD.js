import {useCallback, useState} from "react";

export function useCanvasDnD(cards, setCards, transform) {
    const [isDragging, setIsDragging] = useState(false);

    const saveDragEndPosition = useCallback((active, delta) => {
        if (!delta.x && !delta.y) return;
        setCards(prevCards =>
            prevCards.map(card => {
                if (card.id === active.id) {
                    const canvasDeltaX = delta.x / transform.scale;
                    const canvasDeltaY = delta.y / transform.scale;

                    return {
                        ...card,
                        coordinates: {
                            x: card.coordinates.x + canvasDeltaX,
                            y: card.coordinates.y + canvasDeltaY
                        }
                    };
                }
                return card;
            })
        );
        setIsDragging(false);
    }, [transform.scale, setCards]);

    return {
        isDragging,
        saveDragEndPosition,
        setIsDragging
    };
}