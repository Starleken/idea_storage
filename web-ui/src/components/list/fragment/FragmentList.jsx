import FragmentItem from "./item/FragmentItem";


function FragmentList({list}) {
    return (
        <div className='flex row'>
            {list.map(item => <FragmentItem key={item.id} item={item}/>)}
        </div>
    )
}


export default FragmentList