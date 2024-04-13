import React, { useEffect, useState, useReducer } from "react";
import site_url from '../site'
import "../styles/lecture.scss"
import ContentLoader from "react-content-loader";


function LectureElement({id, nav, theme, date, title, short_desc, data}) {
    const func = ()=>{fetch(site_url+"get_image/?id="+data.id, {
        method: 'GET',
          
        // 👇 Set headers manually for single file upload
        headers: {
          // 'content-type': 'multipart/form-data',
          'Accept': 'image/png',
          "ngrok-skip-browser-warning": "1",
          'Access-Control-Allow-Origin': '*',
        },
      })
        .then((res) => res.blob())
        .then((blob) => {console.log(blob);
            var reader = new FileReader();
            reader.readAsDataURL(blob);
            reader.onloadend = function() {
                // console.log(base64data);
                setImg(<div  className="lecImgWrapper"><img className="lecImg" src={URL.createObjectURL(blob)} alt=""/></div>)
}
        })
        .catch((err) => console.error(err));
    }
    const [img, setImg] = useState(<ContentLoader speed={2}
        backgroundColor="#f3f3f3"
        foregroundColor="#ecebeb" className="lecImgWrapper"> 
                <rect x="0" y="0" rx="0" ry="0" width="100%" height="100%" />
            </ContentLoader>)
    const [, forceUpdate] = useReducer(x => x + 1, 0);

    useEffect(()=>
    {func();forceUpdate()}, id)

    return <div onClick={()=>{func();nav('/lecture?id='+id)}} className="lecWrapper" key={data.id}>
        {img}
        <div className="lecDesc">
            <div className="lecTitle">{title}</div>
            <div className="lecShort light-text">{short_desc}</div>
            <div className="lecThemeDate light-text">
                {theme} · {date}
            </div>
        </div>
    </div>
}

export default LectureElement;