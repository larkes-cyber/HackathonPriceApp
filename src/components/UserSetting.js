import React, { useRef, useReducer, useState, useEffect } from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg";
import Settings from "../icons/Settings.svg";
import Clear from "../icons/CloseBig.svg";
import Edit from "../icons/Edit.svg";
import FileIcon from "../icons/FileIcon";
import Heart from "../icons/Union.svg";
import HeartFill from "../icons/Union3.svg";
import site_url from "../site";

import "../styles/priceview.scss";

function UserSetting({ users, nav, id, update, choose, cookie}) {
  let user = users.filter(v=>v.id===id)[0];
  const [, forceUpdate] = useReducer((x) => x + 1, 0);
  const [loading, setLoading] = useState(false)
  console.log(users, id)

  function make_admin() {
    setLoading(true)
    fetch(site_url+`users/user/update_level/${id}`, {
      method: 'PATCH',
      body: JSON.stringify({user_level: 1}),

      // 👇 Set headers manually for single file upload
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': '*',
        "Authorization": "Bearer "+cookie,
      },
    })
      .then((res) => res.json()).then((data) => {console.log(data);setLoading(false);forceUpdate()
      })
  }
  function make_default() {
    setLoading(true)
    fetch(site_url+`users/user/update_level/${id}`, {
      method: 'PATCH',
      body: JSON.stringify({user_level: 0}),

      // 👇 Set headers manually for single file upload
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': '*',
        "Authorization": "Bearer "+cookie,
      },
    })
      .then((res) => res.json()).then((data) => {console.log(data);setLoading(false);forceUpdate()
      })
  }
  function make_spam() {
    setLoading(true)
    fetch(site_url+`users/user/update_level/${id}`, {
      method: 'PATCH',
      body: JSON.stringify({user_level: 0}),

      // 👇 Set headers manually for single file upload
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': '*',
        "Authorization": "Bearer "+cookie,
      },
    })
      .then((res) => res.json()).then((data) => {console.log(data);setLoading(false);forceUpdate()
      })
  }
  
  
  
  return !loading ? <div className="priceViewContainer">
      <div className="topBarWrapper">
        <div
          onClick={() => {
            choose(-1)
          }}
          className="close c"
        >
          <img src={Clear} />
        </div>
        <div className="addTitle ">{user.name}</div>
        <div className="spacer"/>
      </div>

      <div className="container">
        <div className="upinfo">
          <div className="up">
            {user.email} · {user.phone}{" "}
          </div>
          <div>
            <div className="button document">{user.join_date}</div>
            {/* <a download={price.title+".pdf"}href={site_url+"get_pdf?id="+price.data.id} className="button document">PDF</a> */}
          </div>
        </div>
        <div onClick={() => {user.is_admin ? make_default() : make_admin();
                            update()
                            }} className="message button">
          Сделать {!user.is_admin ? "Администратором" : "обычным пользователем"}
        </div>
        <div onClick={() => {}} className="spam remove button">
          Сообщить о спаме
        </div>
      </div>
    </div> : <div className="cleanview">Обновление...</div>
}

export default UserSetting;
