import {useState} from "react";


function ProjectItem({key, project, handleClick, active}) {

    return (
        <div key={key} onClick={e => handleClick(project.id)} className={'flex column project-item' + ' ' + active}>
            <h3>{project.name}</h3>
            <p>{project.idea}</p>
        </div>
    )
}

export default ProjectItem;