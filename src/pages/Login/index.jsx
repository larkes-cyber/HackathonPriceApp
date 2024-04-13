import React, {useState} from "react";
import "./index.scss"
import { useNavigate } from "react-router-dom";
import server_url from "../../site";

function LoginPage({setCookie, setCookieR}) {

    const to = useNavigate()

    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const [mes, setMes] = useState("")

    async function handle_auth() {
            return fetch(server_url+`auth/login`, {
                method: 'POST',
                body: JSON.stringify({"login": login, "password": password}),
      
                // 👇 Set headers manually for single file upload
                headers: {
                  'Content-Type': 'application/json',
                  'Accept': 'application/json',
                  'Access-Control-Allow-Origin': '*',
                },
              })
                .then((res) => res.json()).catch(res => {
                    setMes("Сервер недоступен");
                }).then((data) => {
                    console.log(data);
                    if ('access' in data) {
                        setCookie(data.access);
                        setCookieR(data.refresh);
                        to('/');
                        setMes("");
                    } else {
                        setMes(data.detail)
                    }
                // 
            }).catch(() => {
                setMes("Ошибка");
            })
            
    }
    

    return <div className="page-wrapper">
        <div className="login">
        <div className="main-container">
        <div className="login-wrapper">
            <h1 className="h1">Авторизация</h1>
            <div className="auth">
            <input className="cta-btn primary h3" type="phone | email" onChange={(e)=>{setLogin(e.target.value)}} placeholder="Телефон или почта"/>
            <input  className="cta-btn primary h3"  type="password"   onChange={(e)=>{setPassword(e.target.value)}} placeholder="Пароль"/>
            <button onClick={() => {handle_auth()}} className="cta-btn  h3">Войти</button>
            <p className="b4" color="red">{mes}</p>
            </div>
            </div></div>
        </div>
    </div>
}

export default LoginPage;