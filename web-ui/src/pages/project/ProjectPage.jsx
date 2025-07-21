import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import ProjectIdPage from "./ProjectId/ProjectIdPage";
import ProjectList from "../../components/list/project/ProjectList";
import {useEffect, useState} from "react";
import {findAll} from '../../utils/project-repository'

function ProjectPage() {
    const navigate = useNavigate();
    const location = useLocation()
    const [id,setId] = useState(0);
    const [projects, setProjects] = useState(findAll());

    function handleProject(id) {
        navigate('/projects/' + id + '/information')
    }

    useEffect(() => {
        const obj = location.pathname.split('/')[2];
        setId(parseInt(obj))
    }, [location.pathname])
    return (
        <div className='flex row stretch-vertical'>
            <div className='projects'>
                <ProjectList id={id} setId={setId} handleProject={handleProject} projects={projects}/>
            </div>
            <Routes>
                <Route path='/:id/*' element={<ProjectIdPage/>}/>
            </Routes>
        </div>


    )
}

export default ProjectPage;