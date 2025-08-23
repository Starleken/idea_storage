import {useParams} from "react-router-dom";


function TaskPage() {
    const {id} = useParams();
    return(
        <div>tasks: {id}</div>
    )
}

export default TaskPage;