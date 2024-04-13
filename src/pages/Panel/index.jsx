import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import server_url from "../../site"


function Panel({cookie, setCookie}) {
    const to = useNavigate()

    useEffect(() => {
        if (!cookie) {
            to("/login")
        } 
        fetch(server_url+`users/admin`, {
            method: 'get',
    
            // 👇 Set headers manually for single file upload
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json',
              'Authorization': 'Bearer ' + cookie, 
              'Access-Control-Allow-Origin': '*',
              "ngrok-skip-browser-warning": "1",
            },
          })
            .then((res) => 
                res.json()
            ).then((data)=>{
                console.log(data)
                if (data.detail) {
                    setCookie(""); 
                    to("/login");
                }
            })
    }
    )

    return <div><a href="/users">Пользователи</a><a href="/prices">Ценники</a><a href="/stores">Магазины</a></div>
}

export default Panel;