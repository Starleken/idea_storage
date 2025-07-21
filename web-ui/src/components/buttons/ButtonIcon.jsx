
function ButtonIcon({icon = '', action, width = 25, heigth = 25, children}) {


    return (<div>
        <button onClick={e => action(e)}>
            <div className='flex row icon-button'>
                <img src={icon} width={width} height={heigth} alt='icon'/>
                {children}
            </div>
        </button>
    </div>)
}

export default ButtonIcon;