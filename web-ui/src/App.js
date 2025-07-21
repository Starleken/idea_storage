import './App.css';
import Header from "./components/header/Header";
import {Route, Routes} from "react-router-dom";
import Footer from "./components/footer/Footer";
import {navigationRoutes} from "./utils/routes";

function App() {
  return (
    <div className='page-wrapper'>
      <Header/>
        <Routes>
            {navigationRoutes.map(route => <Route id={route.path} path={route.path} element={route.element}/>)}
        </Routes>
        <Footer/>
    </div>
  );
}

export default App;
