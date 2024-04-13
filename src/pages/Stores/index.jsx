import React, { useState, useEffect } from "react";
import Header from "../../components/Header";
import Stores from "../../components/Stores";
import server_url from "../../site";
import { useNavigate } from "react-router-dom";
import CleanView from "../../components/CleanView";
import StoreView from "../../components/StoreView";
import CreateStore from "../../components/CreateStore";
import CleanStore from "../../components/CleanStore";

function StoresPage({ cookie, setCookie, is_create }) {
  const to = useNavigate();
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

  return [
    <div className={"left " + (window.location.pathname === "/" ? "aaal" : "")}>
      <Header />
      <Stores stores={stores} is_create={is_create}/>
    </div>,
    <div
      className={"right " + (window.location.pathname === "/" ? "aaar" : "")}
    >
      { is_create?
      <CreateStore
        add_store={update_stores}
        nav={to}
        stores={stores}
        cookie={cookie}
      /> : <CleanStore/>}
    </div>,
  ];
}

export default StoresPage;
