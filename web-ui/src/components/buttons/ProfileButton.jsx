import {useState} from "react";
import ButtonIcon from "./ButtonIcon";
import {useNavigate} from "react-router-dom";


function ProfileButton() {
    const [isActive, setIsActive] = useState('');
    const navigate = useNavigate();
    function openMenu (e) {
        setIsActive(isActive === '' ? 'active' : '')
    }

    function navigateTo(path) {
        navigate(path)
        setIsActive('')
    }

    function logout() {
        setIsActive('')
    }
    const iconPerson = 'https://i.pinimg.com/736x/3b/94/6e/3b946eb954f03a7eb2bbe6bfa02a22be.jpg';
    return (<div>
        <button className="profile" onClick={e => openMenu(e)}>
            <img src={iconPerson} alt='photo' width={25} height={25}/>
        </button>
        <div className={'sub-menu flex column' + ' '+ isActive}>
            <ButtonIcon icon={iconPerson} action={e => navigateTo('/login')}>Войти</ButtonIcon>
            <ButtonIcon icon={iconPerson} action={e => navigateTo('/registration')}>Зарегистрироваться</ButtonIcon>
            <ButtonIcon icon={iconPerson} action={e => navigateTo('/projects')}>Проекты</ButtonIcon>
            <ButtonIcon icon={iconPerson} action={e => logout()}>Выйти</ButtonIcon>
        </div>
    </div>)
}

export default ProfileButton;