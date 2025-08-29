import {useEffect, useState} from "react";
import {useSearchParams} from "react-router-dom";
import {findById} from "../../../utils/project-repository";

function ProjectChangerPage() {
    const [search, setSearch] = useSearchParams();
    const [id, setId] = useState(0)
    const [project, setProject] = useState({
        id: 0,
        name: '',
        idea: '',
        shortDescription: '',
        fullDescription: '',
        reasonForPurchase: '',
        price: 0.0,
        isVisible: false
    })
    useEffect(() => {
        if (search.has("id")) {
            setId(parseInt(search.get("id")))
            if(id) {
                const project = findById(id);
                setProject(project)
            }
        }
    }, [id])

    const changeValue = (e) => {
        const {name, value, checked, type} = e.target
        setProject(prev => ({
            ...prev,
                [name]: type === 'checkbox' ? checked : value
        }
    ))}

    return (
        <div className='line-points stretch-vertical'>
            <h1>{id ? 'Обновление проекта' : 'Создание проекта'}</h1>
            <hr/>
            <label className='form_label' htmlFor="name">Введите наименование*</label>
            <input onChange={changeValue} id="name" className='form_input' type='text' name="name" placeholder="наименование" value={project.name}/>

            <label className='form_label' htmlFor="idea">Введите идею*</label>
            <input onChange={changeValue} id="idea" className='form_input' type='text' name="idea" placeholder="идея" value={project.idea}/>

            <label className='form_label' htmlFor="shortDescription">Введите краткое описание идеи*</label>
            <textarea onChange={changeValue} id="shortDescription" className='form_input' name="shortDescription" placeholder="краткое описание идеи" value={project.shortDescription}/>

            <label className='form_label' htmlFor="fullDescription">Введите полное описание идеи*</label>
            <textarea onChange={changeValue} id="fullDescription" className='form_input' name="fullDescription" placeholder="полное описание идеи" value={project.fullDescription}/>

            <label className='form_label' htmlFor="price">Введите цену*</label>
            <input onChange={changeValue} id="price" className='form_input' type='number' name="price" placeholder="цена" value={project.price}/>

            <label className='form_label' htmlFor="reasonForPurchase">Введите причину цены*</label>
            <textarea onChange={changeValue} id="reasonForPurchase" className='form_input' name="reasonForPurchase" placeholder="причина цены" value={project.reasonForPurchase}/>

            <div className='flex row'>
                <input onChange={changeValue} id="visible" className='task__checkbox' type='checkbox' name="visible" value={project.isVisible}/>
                <label className='form_label' htmlFor="visible">Видимость*</label>
            </div>
            <button className='btn_form'>сохранить</button>
        </div>
    )
}

export default ProjectChangerPage;