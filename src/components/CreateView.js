import React, {useRef, useState} from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg"
import Settings from "../icons/Settings.svg"
import Clear from "../icons/CloseBig.svg"
import LectureElement from "./LectureElement";
import server_url from "../site";

import "../styles/createview.scss"
import FileUI from "./FileUI";
import FileLecture from "./LectureUpload";
import { useParams } from 'react-router-dom';
function CreateView({add_lecture, nav, lectures}) {

    const[isLoading, setLoading] = useState(false)

    const handleUploadClick = () => {
        if (!lecFile) {
            console.log('111')
          return;
        }
        setLoading(true)
        let formData = new FormData();
        formData.append('file', lecFile)
        // 👇 Uploading the file using the fetch API to the server
        fetch(server_url+`upload_lecture?subject=${theme}&title=${title}`, {
          method: 'POST',
          body: formData,

          // 👇 Set headers manually for single file upload
          headers: {
            // 'content-type': 'multipart/form-data',
            'Accept': '*/*',
            'Access-Control-Allow-Origin': '*',
          },
        })
          .then((res) => res.json())
          .then((data) => {
            if (!file) {
                add_lecture(data, FIO, title, theme);console.log(data);setLoading(false)
              return;
            }
            let formData = new FormData();
            formData.append('file', file)
            // formData.append('id', data.id)
            // 👇 Uploading the file using the fetch API to the server
            fetch(`${server_url}upload_image?id=${data.id}`, {
              method: 'POST',
              body: formData,
                
              // 👇 Set headers manually for single file upload
              headers: {
                // 'content-type': 'multipart/form-data',
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
              },
            })
              .then((res) => res.json())
              .then((_) => {add_lecture(data, FIO, title, theme);console.log(data, `/lecture?id=${lectures.length-1}`);
                setLoading(false);
              nav(`/lecture?id=${lectures.length}`)})
              .catch((err) => {console.error(err);setLoading(false)});
            })
          .catch((err) => {console.error(err);setLoading(false)});
      };


  const fileRef = useRef();
  const [file, setFile] = useState("");
  const [lecFile, setLecFile] = useState("")
  const [FIO, setFIO] = useState("")
  const [title, setTitle] = useState("")
  const [theme, setTheme] = useState("")

  return (
    <div className="createViewContainer">
      {isLoading? <div style={{display: "flex", flexDirection: "row", height: "100vh",alignItems: "center", justifyContent: "space-around"}}>Загружаем лекцию..</div> : [<div className="topBarWrapper">
        <div onClick={()=>{nav('/')}} className="close"><img src={Clear}/></div>
        <div className="addTitle ">Добавить Лекцию</div>
        <div className="c"/>
        </div>,
      
      <div className="container">
        <FileUI file={file} setFile={setFile} fileRef={fileRef}/>
        <div className="input">
            ФИО
            <input onChange = {(e)=>{setFIO(e.target.value)}}placeholder="Введите лектора"/>
        </div>
        <div className="input">
            Название
            <input onChange = {(e)=>{setTitle(e.target.value)}}placeholder="Введите название лекции"/>
        </div>
        
        <div className="input">
            Тема
            <input onChange = {(e)=>{setTheme(e.target.value)}}placeholder="Выберите тему"/>
        </div>
        <FileLecture file={lecFile} setFile={setLecFile} />
        <div className={"upload button "+ ((!lecFile || !FIO || !theme || !title) ? 'not' : '')} onClick={handleUploadClick}>Готово</div>
      </div>]}
    </div>
  );
}

export default CreateView;
