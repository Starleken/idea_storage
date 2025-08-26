import HotkeyList from "../../../components/list/hotkey/HotkeyList";

const list = [
    {
        id: 1,
        name: 'W',
        description: 'Walk up'
    },
    {
        id: 2,
        name: 'D',
        description: 'Walk right'
    },
    {
        id: 3,
        name: 'S',
        description: 'Walk down'
    },
    {
        id: 4,
        name: 'A',
        description: 'Walk left'
    }
]


function HotkeyPage() {
    return (
        <div>
            <HotkeyList list={list}/>
        </div>
    )
}

export default HotkeyPage