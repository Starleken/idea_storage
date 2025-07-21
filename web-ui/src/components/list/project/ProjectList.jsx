import ProjectItem from "./item/ProjectItem";
import {useState} from "react";


function ProjectList({id,setId, projects, handleProject}) {

    function clickOnProjectItem(id) {
        handleProject(id)
    }

    return (
        <div className='flex column'>
            {projects.map(project =>
                <ProjectItem key={project.id} project={project} handleClick={clickOnProjectItem} active={id === project.id ? 'active' : ''}/>
            )}
        </div>
    )
}

export default ProjectList;