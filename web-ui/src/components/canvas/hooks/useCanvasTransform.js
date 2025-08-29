import { useState, useCallback } from 'react';

export function useCanvasTransform() {
    const [transform, setTransform] = useState({
        x: 0,
        y: 0,
        scale: 1
    });

    const zoomIn = useCallback(() => {
        setTransform(prev => ({
            ...prev,
            scale: Math.min(4, prev.scale + 0.2)
        }));
    }, []);

    const zoomOut = useCallback(() => {
        setTransform(prev => ({
            ...prev,
            scale: Math.max(0.1, prev.scale - 0.2)
        }));
    }, []);

    const resetView = useCallback(() => {
        setTransform({ x: 0, y: 0, scale: 1 });
    }, []);

    const updateTransform = useCallback((newTransform) => {
        setTransform(newTransform);
    }, []);

    return {
        transform,
        zoomIn,
        zoomOut,
        resetView,
        updateTransform
    };
}