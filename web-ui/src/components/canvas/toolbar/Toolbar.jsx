

function Toolbar({ transform, setTransform }) {
    const zoomIn = () => {
        setTransform(prev => ({
            ...prev,
            scale: Math.min(4, prev.scale + 0.1)
        }));
    };

    const zoomOut = () => {
        setTransform(prev => ({
            ...prev,
            scale: Math.max(0.1, prev.scale - 0.1)
        }));
    };

    const resetView = () => {
        setTransform({ x: 0, y: 0, scale: 1 });
    };
    return (
        <div className='flex column toolbar'>
            <div className='tools'>
                <p>Arrow</p>
            </div>
            <div className='actions'>
                <button onClick={zoomIn}>+</button>
                <button onClick={zoomOut}>-</button>
                <button onClick={resetView}>Reset</button>
                <div>Scale: {transform.scale.toFixed(2)}x</div>
            </div>
        </div>
    )
}

export default Toolbar