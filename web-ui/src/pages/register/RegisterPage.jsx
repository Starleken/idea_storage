import {NavLink} from "react-router-dom";


function RegisterPage() {
    return (
        <div>
            <div className="login-page">
                <div className="auth-form">
                    <h1>Регистрация</h1>
                    <form>
                        <div className="form-group">
                            <label htmlFor="login" className="required-field">Введите почту</label>
                            <input type="email" id="email" required/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="login" className="required-field">Введите логин</label>
                            <input type="text" id="login" required/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="name" className="required-field">Введите название профиля</label>
                            <input type="text" id="name" required/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="password" className="required-field">Введите пароль</label>
                            <input type="password" id="password" required/>
                        </div>
                        <button type="submit" className="login-btn">Зарегистрироваться</button>
                    </form>
                    <div className="no-account">
                        Есть аккаунт? <NavLink to='/login'>Авторизоваться</NavLink>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default RegisterPage;