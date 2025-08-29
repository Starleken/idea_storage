import {useParams} from "react-router-dom";
import TaskList from "../../../components/list/task/TaskList";

const data = [
    {
        id: 1,
        header: 'Task 1',
        description: 'Need make something actions',
        createdAt: 4231421432134,
        updatedAt: null,
        expiredAt: 4231421432134,
        finishedAt: 4231421432134,
        countCompletedSteps: 3,
        countAllSteps: 6
    },
    {
        id: 2,
        header: 'Task 2',
        description: 'Need make something actions',
        createdAt: 4231421432134,
        updatedAt: null,
        expiredAt: 4231421432134,
        finishedAt: 4231421432134,
        countCompletedSteps: 3,
        countAllSteps: 6
    }
]


function TaskPage() {
    const {id} = useParams();

    return(
        <div>
            <TaskList list={data}/>
        </div>
    )
}

export default TaskPage;