import React, {useRef, useReducer, useState, useEffect} from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg"
import Settings from "../icons/Settings.svg"
import Clear from "../icons/CloseBig.svg"
import site_url from "../site"

import "../styles/lectureview.scss"
import FileUI from "./FileUI";
import FileLecture from "./LectureUpload";
import { useParams } from "react-router-dom";

function LectureView({setLectures, lectures, nav}) {
  let {id} = useParams();
  let lecture = lectures[id]
  const [isG, setG] = useState(false)
  const [, forceUpdate] = useReducer(x => x + 1, 0);
  console.log(lecture, id)
  const [img_, setImg] = useState(<img alt="" src={""}/>)
  const [title, setTitle] = useState(lectures[id].title)
  const [FIO, setFIO] = useState(lectures[id].FIO)
  const [theme, setTheme] = useState(lectures[id].theme)
  const [short, setShort] = useState(lectures[id].data.short_descr)
  useEffect(()=>
    {
        if (id >= lectures.length || id < 0) {
            nav('/')
            return
        }
        if (!isG) {
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
            setG(true)
            })
        .catch((err) => console.error(err));
        }
    }, [id]
  )
  if (id >= lectures.length || id < 0) {
    nav('/')
    return
}

  
  return (
    <div className="lectureViewContainer">
      <div className="topBarWrapper">
        <div onClick={()=>{nav(`/lecture?id=${id}`)}} className="close c"><img src={Clear}/></div>
        <div className="input-"><input className="addTitle " onChange={(e)=>{setTitle(e.target.value)}} defaultValue={title}/>
        </div><div className="spacer"/>
        </div>
      
      <div className="container">
        <div className="imgWrapper" >{img_}</div>
        <div className="upinfo">
            <div className="up">
                <div className="input-"><div>ФИО</div><input className="fio"   onChange={(e)=>{setFIO(e.target.value)}} defaultValue={FIO}/></div>
                <div className="input-"><div>Тема</div><input className="theme"  onChange={(e)=>{setTheme(e.target.value)}} defaultValue={theme}/>
                </div>
                </div>
            
        </div>
        <textarea  onChange={(e)=>{setShort(e.target.value)}} defaultValue={short}></textarea>
        <div onClick={()=>{
            lecture.title = title
            lecture.FIO = FIO
            lecture.theme = theme
            lecture.data.short_descr = short
            lectures[id] = lecture
            setLectures(lectures)
            nav(`/lecture/?id=${id}`)}}className="glosWrapper button">Сохранить изменения</div>
            <div onClick={()=>{
              lectures.splice(id, 1)
                nav(`/`)
                forceUpdate()}}className="remove button">Удалить</div>
     </div>
        
        
       </div>
  );
}

export default LectureView;
