import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import server_url from "../../site";
import "./index.scss";
import pLogo from "../../icons/prices.svg";
import uLogo from "../../icons/users.svg";
import sLogo from "../../icons/stores.svg";

function Panel({ cookie, setCookie }) {
  const to = useNavigate();

  useEffect(() => {
    if (!cookie) {
      to("/login");
    }
    fetch(server_url + `users/admin`, {
      method: "get",

      // 👇 Set headers manually for single file upload
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: "Bearer " + cookie,
        "Access-Control-Allow-Origin": "*",
        "ngrok-skip-browser-warning": "1",
      },
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        if (data.detail) {
          setCookie("");
          to("/login");
        }
      });
  });

  return (
    <div className="panel">
      <h1>Панель админа</h1>
      <div className="links">
        <a href="/users">
            <img src={uLogo}/>
          <p>Пользователи</p>
        </a>
        <a href="/prices">
            <img src={pLogo}/>
          <p>Ценники</p>
        </a>
        <a href="/stores">
            <img src={sLogo}/>
          <p>Магазины</p>
        </a>
      </div>
    </div>
  );
}

export default Panel;
