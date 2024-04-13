import React, { useEffect, useState, useReducer } from "react";
import site_url from '../site'
import "../styles/lecture.scss"
import ContentLoader from "react-content-loader";


function StoreElement({id, changeId, location, region, name, email}) {
    

    const [, forceUpdate] = useReducer(x => x + 1, 0);


    return <div onClick={()=>{ }} className="lecWrapper" key={id}>
    
        <div className="lecDesc">
            <div className="lecTitle">{name}</div>
            <div className="lecShort light-text">{email}</div>
            <div className="lecThemeDate light-text">
                {region} · {location}
            </div>
        </div>
    </div>
}

export default StoreElement;