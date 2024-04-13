import React, {useRef, useState} from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg"
import Settings from "../icons/Settings.svg"
import Clear from "../icons/CloseBig.svg"
import PriceElement from "./PriceElement";
import server_url from "../site";

import "../styles/createview.scss"
import FileUI from "./FileUI";
import FileLecture from "./PriceUpload";
import { useParams } from 'react-router-dom';


function CreateStore({add_store, nav, stores, cookie}) {

    const[isLoading, setLoading] = useState(false)

    const handleUploadClick = () => {
        setLoading(true)
        let data = JSON.stringify({"location": location, "name": name, "region": region, "email": email})
        // 👇 Uploading the file using the fetch API to the server
        fetch(server_url+`stores/create_store`, {
          method: 'POST',
          body: data,

          // 👇 Set headers manually for single file upload
          headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Access-Control-Allow-Origin': '*',
            "Authorization": "Bearer "+cookie,
          },
        })
          .then((res) => res.json()).then((data) => {console.log(data);setLoading(false);add_store()
          })
      }


  const fileRef = useRef();
  const [email, setEmail] = useState("")
  const [name, setName] = useState("")
  const [region, setRegion] = useState("")
  const [location, setLocation] = useState("")

  return (
    <div className="createViewContainer">
      {isLoading? <div style={{display: "flex", flexDirection: "row", height: "100vh",alignItems: "center", justifyContent: "space-around"}}>Загружаем лекцию..</div> : [<div className="topBarWrapper">
        <div onClick={()=>{nav('/stores')}} className="close"><img src={Clear}/></div>
        <div className="addTitle ">Добавить Магазин</div>
        <div className="c"/>
        </div>,
      
      <div className="container">
        {/* <FileUI file={file} setFile={setFile} fileRef={fileRef}/> */}
        <div className="input">
            Название
            <input onChange = {(e)=>{setName(e.target.value)}}placeholder="Введите название"/>
        </div>
        <div className="input">
            Местоположение
            <input onChange = {(e)=>{setLocation(e.target.value)}}placeholder="Введите местоположение"/>
        </div>
        
        <div className="input">
            Регион
            <input onChange = {(e)=>{setRegion(e.target.value)}}placeholder="Введите регион"/>
        </div>
        <div className="input">
            Почта
            <input onChange = {(e)=>{setEmail(e.target.value)}}placeholder="Введите почту"/>
        </div>
        <div className={"upload button "+ ((!location || !name || !email) ? 'not' : '')} onClick={handleUploadClick}>Готово</div>
      </div>]}
    </div>
  );
}

export default CreateStore;
