import ProjectItem from "./item/ProjectItem";


function ProjectList({id, projects, handleProject}) {

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