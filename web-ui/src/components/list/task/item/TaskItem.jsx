
function TaskItem({item}) {
    return (
        <div className='flex row task__item'>
            <div className='flex row task__content'>
                <input type='checkbox' className='task__checkbox'/>
                <div>
                    <h3>{item.header}</h3>
                    <p>{item.description}</p>
                    <div className='flex row'>
                        <p className='task_date'>{new Date(item.createdAt).toLocaleString()}</p>
                        <p className='task_steps'>{item.countCompletedSteps}/{item.countAllSteps} шагов</p>
                    </div>
                </div>
            </div>
            <button>▼</button>
        </div>
    )
}

export default TaskItem;
