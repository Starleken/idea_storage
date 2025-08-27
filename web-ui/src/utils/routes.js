import MainPage from "../pages/main/MainPage";
import LoginPage from "../pages/login/LoginPage";
import RegisterPage from "../pages/register/RegisterPage";
import ProjectPage from "../pages/project/ProjectPage";
import BoardPage from "../pages/project/Board/BoardPage";

export const navigationRoutes = [
    {path: '/', element: <MainPage/> },
    {path: '/login', element: <LoginPage/>,},
    {path: '/registration', element: <RegisterPage/>,},
    {path: '/projects/*', element: <ProjectPage/>,},
    {path: '/board/*', element: <BoardPage isFullScreen={true}/>,},
]