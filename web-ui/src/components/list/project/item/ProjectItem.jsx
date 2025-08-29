

function ProjectItem({project, handleClick, active}) {

    return (
        <div onClick={e => handleClick(project.id)} className={'flex column project-item' + ' ' + active}>
            <h3>{project.name}</h3>
            <p>{project.idea}</p>
        </div>
    )
}

export default ProjectItem;