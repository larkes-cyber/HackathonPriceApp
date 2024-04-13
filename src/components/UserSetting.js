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

function UserSetting({ users, nav, id, setUsers, choose}) {
  let user = users.filter(v=>v.id===id)[0];
  const [, forceUpdate] = useReducer((x) => x + 1, 0);
  console.log(users, id)
  
  return (
    <div className="priceViewContainer">
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
        <div onClick={() => {}} className="message button">
          Сделать {user.is_admin ? "Администратором" : "обычным пользователем"}
        </div>
        <div onClick={() => {}} className="spam remove button">
          Сообщить о спаме
        </div>
      </div>
    </div>
  );
}

export default UserSetting;
