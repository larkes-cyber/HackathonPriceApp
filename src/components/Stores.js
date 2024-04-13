import React, {useState} from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg"
import Settings from "../icons/Settings.svg"
import Clear from "../icons/Close.svg"
import StoreElement from "../components/StoreElement";

import "../styles/lectures.scss"
import { useNavigate } from "react-router-dom";

function Stores({stores, changeId, chosen, is_create}) {

  const [settings, setSettings] = useState(false);
  const [searchProp, setSearchProp] = useState('')
  const nav = useNavigate()

  console.log(stores)
  let Regions = [...new Set(stores.map((value)=>value.region))]
  const [chosenF, setChosenF] = useState(Regions)
  // const lecView = prices.filter((e)=>chosenF.includes(e.store) && chosenT.includes(e.category) && e.name.includes(searchProp)).map(
  //   (val) =>
  //   { 
  //     id_++;
  //     console.log("aaaaa")
  //     return <PriceElement id={id_} changeId={changeId} name={val.name} date={""} category={val.category} price={ val.price } 
  //      data={val.data}
  //     />
      
  //   }

  // )
  console.log(stores)
  const lecView = stores.map((val) => <StoreElement id={val.id} changeId={changeId} name={val.name} region={val.region} location={val.location}  />)
  //      data={val.data}
  //     />)

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
      {!is_create ? <div onClick={()=>nav("/create_store")} className="loadButtonWrapper button">
        <img src={addIcon}/>
        Добавить Магазин
      </div> : ""}
      <div className="lcsContainer">
        {lecView}
        </div>
    </div>
  );
}

export default Stores;
