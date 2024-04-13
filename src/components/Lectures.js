import React, {useState} from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg"
import Settings from "../icons/Settings.svg"
import Clear from "../icons/Close.svg"
import LectureElement from "./LectureElement";
import server_url from "../site"

import "../styles/lectures.scss"

function Lectures({lectures, nav}) {

  const [settings, setSettings] = useState(false);
  const [searchProp, setSearchProp] = useState('')

  let id_ = -1;
      
  let FIOs = [...new Set(lectures.map((value)=>value.FIO))]
  let themes = [...new Set(lectures.map((value)=>value.theme))]
  const [chosenF, setChosenF] = useState(FIOs)
  const [chosenT, setChosenT] = useState(themes)
  const lecView = lectures.filter((e)=>chosenF.includes(e.FIO) && chosenT.includes(e.theme) && e.data.short_descr.includes(searchProp)).map(
    (val) =>
    { 
      id_++;
      console.log(val.data)
      return <LectureElement id={id_} title={val.title} date={val.FIO} theme={val.theme} short_desc={ val.data['short_descr'].length > 25 ?val.data['short_descr'].slice(0, 25)+'...' : val.data['short_descr'] } 
      nav={
        nav} data={val.data}
      />
      
    }

  )

  console.log(FIOs, themes)

  console.log(chosenF, chosenT)
  
  return (
    <div className="lcsViewContainer">
      <div className="search">
      <div className="srchBrWrapper">
        <img src={SearchIcon} alt=""/>
        <input placeholder="Поиск" value={searchProp} onChange={(e)=>{setSearchProp(e.target.value)}}/>
        <img src={Settings} className="c" alt="" onClick={()=>{setSettings(!settings)}}/>
        <img src={Clear} className="c" onClick={()=>{setSearchProp('')}} alt=""/>
      </div>
      {settings ? <div className="filters">
        <div className="fios">
          <div className="title">Авторы</div>
          <div className="list">
          {FIOs.map((v, i)=>{return <div className="el">
              
              <input type="checkbox" checked={chosenF.indexOf(FIOs[i]) !== -1} onChange={()=>
              {if (chosenF.indexOf(FIOs[i]) !== -1){
                  setChosenF(chosenF.filter(a => a !== v))
                } else {
                  setChosenF([...chosenF, FIOs[i]])
                }
            }}/>{[v]}
            </div>})}
          </div>
        </div>
        <div className="themes">
          <div className="title">Темы</div>
          <div className="list">
            {themes.map((v, i)=>{return <div className="el">
              
              <input type="checkbox" checked={chosenT.indexOf(themes[i]) !== -1} onChange={()=>
              {if (chosenT.indexOf(themes[i]) !== -1){
                  setChosenT(chosenT.filter(a => a !== v))
                } else {
                  setChosenT([...chosenT, themes[i]])
                }
            }}/>{[v]}
            </div>})}
          </div>
        </div>
      </div>: ""}
      </div>
      <div onClick={()=>nav("/add")} className="loadButtonWrapper button">
        <img src={addIcon}/>
        Добавить
      </div>
      <div className="lcsContainer">
        {lecView}
        </div>
    </div>
  );
}

export default Lectures;
