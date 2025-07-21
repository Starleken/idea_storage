import {NavLink} from "react-router-dom";


function LoginPage() {
    return (
        <div className="login-page">
            <div className="auth-form">
                <h1>Авторизация</h1>
                <form>
                    <div className="form-group">
                        <label htmlFor="login" className="required-field">Введите логин или почту</label>
                        <input type="text" id="login" required/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password" className="required-field">Введите пароль</label>
                        <input type="password" id="password" required/>
                    </div>
                    <button type="submit" className="login-btn">Войти</button>
                </form>
                <div className="no-account">
                    Нет аккаунта? <NavLink to='/registration'>Зарегистрироваться</NavLink>
                </div>
            </div>
        </div>
    )
}

export default LoginPage;