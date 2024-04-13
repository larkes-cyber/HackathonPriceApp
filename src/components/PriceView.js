import React, {useRef, useReducer, useState, useEffect} from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg"
import Settings from "../icons/Settings.svg"
import Clear from "../icons/CloseBig.svg"
import Edit from "../icons/Edit.svg"
import FileIcon from "./FileIcon";
import Heart from "../icons/Union.svg"
import HeartFill from "../icons/Union3.svg"
import site_url from "../site"

import "../styles/lectureview.scss"
import FileUI from "./FileUI";
import FileLecture from "./LectureUpload";
import { useParams } from "react-router-dom";
import ContentLoader from "react-content-loader";

function LectureView({lectures, nav, id, setLectures}) {
  let lecture = lectures[id]
  const [, forceUpdate] = useReducer(x => x + 1, 0);
  // console.log(lecture, id)
  const [img_, setImg] = useState(<ContentLoader speed={2}
    backgroundColor="#f3f3f3"
    foregroundColor="#ecebeb" style={{width: "calc(100% - 0.3em)", borderRadius:"10px",marginBottom:"1em",height:"15em", display: "flex", flexDirection: "column", alignItems:"center", justifyContent: "center", border: "2px dashed #818C99"}}> <rect x="0" y="0" rx="0" ry="0" width="100%" height="100%" /></ContentLoader>)
  useEffect(()=>
    {
        if (id >= lectures.length || id < 0) {
            nav('/')
            return
        }
            fetch(site_url+"get_image/?id="+lecture.data.id, {
                method: 'GET',
                headers: {
                    'Accept': 'image/png',
                    "ngrok-skip-browser-warning": "1",
                    'Access-Control-Allow-Origin': '*',
                },
      })
        .then((res) => res.blob())
        .then((blob) => {
            setImg(<img alt="" src={URL.createObjectURL(blob)}/>)
            })
        .catch((err) => console.error(err));
        
    }, [id]
  )
  if (id >= lectures.length || id < 0) {
    nav('/')
    return
}

  console.log(lecture)
  return (
    <div className="lectureViewContainer">
      <div className="topBarWrapper">
        <div onClick={()=>{nav(`/`)}} className="close c"><img src={Clear}/></div>
        <div className="addTitle ">{lecture.title}</div>
        <div className="edit c" onClick={()=>{nav(`/edit_lecture/${id}`)}}>
          <img src={Edit}/>
        </div>
        </div>
      
      <div className="container">
        <div className="imgWrapper" >{img_}</div>
        <div className="upinfo">
            <div className="up">{lecture.FIO} · {lecture.theme} <img className="c" onClick={()=>{
              lecture.featured = !lecture.featured;
              let y = lectures;
              y[id] = lecture; 
              setLectures(y);

            }} src={lecture.featured ? Heart : HeartFill} alt=""/></div>
            <div>
            <div download={lecture.title+".docx"}href={site_url+"get_docx?id="+lecture.data.id} className="button document">category</div>
            {/* <a download={lecture.title+".pdf"}href={site_url+"get_pdf?id="+lecture.data.id} className="button document">PDF</a> */}
            </div>
        </div>
        <div>{lecture.data.short_descr}</div>
        <div onClick={()=>{
            nav(`/glos/${id}`);
            fetch(site_url+"get_image/?id="+id, {
              method: 'GET',
              headers: {
                  'Accept': 'image/png',
                  "ngrok-skip-browser-warning": "1",
                  'Access-Control-Allow-Origin': '*',
              },
    })
      .then((res) => res.blob())
      .then((blob) => {
          setImg(<img alt="" src={URL.createObjectURL(blob)}/>)
          })
      .catch((err) => console.error(err));
      }
            }className="glosWrapper button">Сообщить</div>
            <div onClick={()=>{
              lectures.splice(id, 1)
                nav(`/`)
                forceUpdate()}}className="remove button">Спам</div>
     </div>
        
        
       </div>
  );
}

export default LectureView;
