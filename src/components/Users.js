import React, {useState} from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg"
import Settings from "../icons/Settings.svg"
import Clear from "../icons/Close.svg"
import UserElement from "../components/UserElement";

import "../styles/lectures.scss"
import { useNavigate } from "react-router-dom";

function Users({users, choose, chosen}) {

  const [settings, setSettings] = useState(false);
  const [searchProp, setSearchProp] = useState('')
  const nav = useNavigate()

  console.log(users)
  let Regions = [...new Set(users.map((value)=>value.region))]
  const [chosenF, setChosenF] = useState(Regions)

  const lecView = users.map((val) => <UserElement id={val.id} choose={choose} name={val.name} phone={val.phone} email={val.email} is_admin={val.is_admin}/>)
 

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
          <div className="title">Регионы</div>
          <div className="list">
          {Regions.map((v, i)=>{return <div className="el">
              
              <input type="checkbox" checked={chosenF.indexOf(Regions[i]) !== -1} onChange={()=>
              {if (chosenF.indexOf(Regions[i]) !== -1){
                  setChosenF(chosenF.filter(a => a !== v))
                } else {
                  setChosenF([...chosenF, Regions[i]])
                }
            }}/>{[v]}
            </div>})}
          </div>
        </div>
      </div>: ""}
      </div>
      {chosen>0 ? <div onClick={()=>nav("/user_setting")} className="loadButtonWrapper button">
        <img src={addIcon}/>
      </div> : ""}
      <div className="lcsContainer">
        {lecView}
        </div>
    </div>
  );
}

export default Users;
