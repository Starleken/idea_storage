



function FragmentItem({item}) {
    return (
        <div className='flex column fragment'>
            <img src={item.picture} alt={item.description}/>
            <p>{item.description}</p>
        </div>
    )
}


export default FragmentItem