import {createSearchParams, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {findById} from "../../../utils/project-repository";
import ticksToDate from "ticks-to-date";
import Markdown from "markdown-to-jsx";


function ProjectInformation() {
    const {id} = useParams();
    const navigate = useNavigate()
    const [project, setProject] = useState({
        id: 0,
        name: 'project',
        idea: '',
        shortDescription: '',
        fullDescription: '',
        reasonForPurchase: '',
        price: 0,
        createdAt: 636976874910235300,
        expiredAt: 0,
        finishedAt:0,
        isVisible: false
    });

    useEffect(()=>{
        setProject(findById(parseInt(id)))
    }, [id])

    const navigateToChangePage = () => {
        navigate(`/projects/handle?id=${id}`)
    }

    return <div className='container-information scroll-container'>
        <div className='tools' style={{float: 'right'}}>
            <button onClick={() => navigateToChangePage()}>✏️</button>
            <button>lll</button>
        </div>
        <div className='flex row'>
            <div className='flex column information'>
                <p>Дата создания: {ticksToDate(project.createdAt).toUTCString()}</p>
                <p>название</p>
                <h1>{project.name}</h1>
                <p>идея</p>
                <h4>{project.idea}</h4>
                <p>краткое описание</p>
                <h4>{project.shortDescription}</h4>
            </div>
            <div className='flex column information'>
                <h3>полное описание</h3>
                <Markdown>{project.fullDescription}</Markdown>
            </div>
        </div>



    </div>
}

export default ProjectInformation;