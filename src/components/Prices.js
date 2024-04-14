import React, {useState, useEffect} from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg"
import Settings from "../icons/Settings.svg"
import Clear from "../icons/Close.svg"
import PriceElement from "../components/PriceElement";
import server_url from "../site"

import "../styles/lectures.scss"
import { useNavigate } from "react-router-dom";

function Prices({prices, changeId, chosen, cookie}) {

  const [settings, setSettings] = useState(false);
  const [searchProp, setSearchProp] = useState('')
  const to = useNavigate()
  let id_ = -1;
  console.log(prices)
  let Stores = [...new Set(prices.map((value)=>value.store))]
  let Categories = [...new Set(prices.map((value)=>value.category))]
  const [chosenF, setChosenF] = useState(Stores)
  const [chosenT, setChosenT] = useState(Categories)
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
  console.log(prices)
  const lecView = prices.map((val) => <PriceElement id={val.id} changeId={changeId} name={val.name} date={""} category={val.category} price={ val.price } />)
  //      data={val.data}
  //     />)

  const [stores, setStores] = useState([]);

  function update_stores() {
    fetch(server_url + `stores/stores`, {
      method: "get",

      // 👇 Set headers manually for single file upload
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: "Bearer " + cookie,
        "Access-Control-Allow-Origin": "*",
        "ngrok-skip-browser-warning": "1",
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setStores(data);

      });
  }

  useEffect(() => {
    if (stores.length === 0){
    update_stores();}
    if (!cookie) {
      to("/login");
    } 
  });

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
          <div className="title">Магазины</div>
          <div className="list">
          {stores.map((v, i)=>{return <div className="el">
              
              <input type="checkbox" checked={chosenF.indexOf(stores[i]) !== -1} onChange={()=>
              {if (chosenF.indexOf(stores[i]) !== -1){
                  setChosenF(chosenF.filter(a => a !== v))
                } else {
                  setChosenF([...chosenF, stores[i]])
                }
            }}/>{[v.name]}
            </div>})}
          </div>
        </div>
        <div className="themes">
          <div className="title">Категории</div>
          <div className="list">
            {Categories.map((v, i)=>{return <div className="el">
              
              <input type="checkbox" checked={chosenT.indexOf(Categories[i]) !== -1} onChange={()=>
              {if (chosenT.indexOf(Categories[i]) !== -1){
                  setChosenT(chosenT.filter(a => a !== v))
                } else {
                  setChosenT([...chosenT, Categories[i]])
                }
            }}/>{[v]}
            </div>})}
          </div>
        </div>
      </div>: ""}
      </div>
      {/* <div onClick={()=>nav("/add")} className="loadButtonWrapper button">
        <img src={addIcon}/>
        Добавить
      </div> */}
      <div className="lcsContainer">
        {lecView}
        </div>
    </div>
  );
}

export default Prices;
