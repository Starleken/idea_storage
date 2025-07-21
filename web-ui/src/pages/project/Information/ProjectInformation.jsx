import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {findById} from "../../../utils/project-repository";


function ProjectInformation() {
    const {id} = useParams();
    const [project, setProject] = useState({
        id: 0,
        name: 'sada',
        idea: '',
        shortDescription: '',
        fullDescription: '',
        reasonForPurchase: '',
        price: 0,
        createdAt: 0,
        expiredAt: 0,
        finishedAt:0,
        isVisible: false
    });

    useEffect(()=>{
        setProject(findById(parseInt(id)))
    }, [id])

    return <div>
        <h6>название</h6>
        <h1>{project.name}</h1>
    </div>
}

export default ProjectInformation;