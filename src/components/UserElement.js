import React, { useEffect, useState, useReducer } from "react";
import site_url from '../site'
import "../styles/lecture.scss"
import ContentLoader from "react-content-loader";


function UserElement({id, choose, phone, name, email, is_admin}) {
    

    const [, forceUpdate] = useReducer(x => x + 1, 0);


    return <div onClick={()=>{choose(id)}} className="lecWrapper" key={id}>
    
        <div className="lecDesc">
            <div className="lecTitle">{name ? name : "Без имени"}</div>
            <div className="lecShort light-text">{is_admin? "Администратор" : "Обычный пользователь"}</div>
            <div className="lecThemeDate light-text">
                {phone ? phone : "Телефон не указан"} · {email ? email : "Почта не указана"}
            </div>
        </div>
    </div>
}

export default UserElement;