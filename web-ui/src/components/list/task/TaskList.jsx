import TaskItem from "./item/TaskItem";


function TaskList({list}) {
   return (
       <div className='flex column'>
           {list.map(item => <TaskItem key={item.id} item={item}/>)}
       </div>
   )
}

export default TaskList