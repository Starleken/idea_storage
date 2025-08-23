import {NavLink, Route, Routes, useLocation, useParams} from "react-router-dom";
import ProjectInformation from "../Information/ProjectInformation";
import FragmentPage from "../Fragment/FragmentPage";
import TaskPage from "../Task/TaskPage";


function ProjectIdPage() {
    const {id} = useParams();
    const location = useLocation()

    return (
        <div className='flex column'>
            <div className='line-points'>
                <div className='flex row'>
                    <NavLink className={location.pathname.endsWith('/information') ? 'active-link' : ''} to={'/projects/' + id + '/information'}>Информация</NavLink>
                    <NavLink className={location.pathname.endsWith('/pages') ? 'active-link' : ''} to={'/projects/' + id + '/pages'}>Страницы</NavLink>
                    <NavLink className={location.pathname.endsWith('/tasks') ? 'active-link' : ''} to={'/projects/' + id + '/tasks'}>Задачи</NavLink>
                </div>
            </div>
            <div className='line-points stretch-vertical'>
                <Routes>
                    <Route path='/information' element={<ProjectInformation/>}/>
                    <Route path='/pages' element={<FragmentPage/>}/>
                    <Route path='/tasks' element={<TaskPage/>}/>
                </Routes>
            </div>
        </div>
    )
}

export default ProjectIdPage;