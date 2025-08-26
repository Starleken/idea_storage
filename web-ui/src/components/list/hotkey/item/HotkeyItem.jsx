function HotkeyItem({item}) {
    return (
        <div className='flex row hotkey'>
            <h3>{item.name}</h3>
            <p>{item.description}</p>
        </div>
    )
}

export default HotkeyItem