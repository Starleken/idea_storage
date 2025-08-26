import {useCallback, useRef, useState} from 'react';

export function useCanvasInteractions(transform, updateTransform) {
    const [isPanning, setIsPanning] = useState(false);
    const lastMousePos = useRef({ x: 0, y: 0 });

    const handleWheel = useCallback((e) => {
        e.preventDefault();
        if (!e.currentTarget) return;

        const rect = e.currentTarget.getBoundingClientRect();
        const mouseX = e.clientX - rect.left;
        const mouseY = e.clientY - rect.top;

        const zoomIntensity = 0.1;
        const wheel = e.deltaY < 0 ? 1 : -1;
        const zoom = Math.exp(wheel * zoomIntensity);

        updateTransform(prev => {
            const newScale = Math.max(0.1, Math.min(4, prev.scale * zoom));

            const newX = mouseX - (mouseX - prev.x) * (newScale / prev.scale);
            const newY = mouseY - (mouseY - prev.y) * (newScale / prev.scale);

            return {
                x: newX,
                y: newY,
                scale: newScale
            };
        });
    }, [updateTransform]);

    const handleMouseDown = useCallback((e) => {
        if ((e.button === 2 || e.ctrlKey || e.metaKey) && e.target === e.currentTarget) {
            e.preventDefault();
            setIsPanning(true);
            lastMousePos.current = { x: e.clientX, y: e.clientY };
        }
    }, []);

    const handleMouseMove = useCallback((e) => {
        if (isPanning) {
            e.preventDefault();
            const deltaX = e.clientX - lastMousePos.current.x;
            const deltaY = e.clientY - lastMousePos.current.y;

            updateTransform(prev => ({
                ...prev,
                x: prev.x + deltaX,
                y: prev.y + deltaY
            }));

            lastMousePos.current = { x: e.clientX, y: e.clientY };
        }
    }, [isPanning, updateTransform]);

    const handleMouseUp = useCallback(() => {
        setIsPanning(false);
    }, []);

    const handleContextMenu = useCallback((e) => {
        e.preventDefault();
    }, []);

    return {
        isPanning,
        handleWheel,
        handleMouseDown,
        handleMouseMove,
        handleMouseUp,
        handleContextMenu
    };
}