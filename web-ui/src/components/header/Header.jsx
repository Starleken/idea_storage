import ProfileButton from "../buttons/ProfileButton";
import {NavLink} from "react-router-dom";

function Header() {
    return (
        <header>
            <NavLink className='logo-text' to='/'>
                ProtoBin
            </NavLink>
            <div>
                <ProfileButton/>
            </div>
        </header>
    )
}

export default Header;