import HotkeyItem from "./item/HotkeyItem";


function HotkeyList({list}) {
    return (
        <div className='flex column'>
            {list.map(item => <HotkeyItem key={item.id} item={item}/>)}
        </div>
    )
}

export default HotkeyList