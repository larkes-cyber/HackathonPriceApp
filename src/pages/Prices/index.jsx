import React, { useState, useEffect } from "react";
import Header from "../../components/Header";
import Prices from "../../components/Prices";
import server_url from "../../site";
import { useNavigate } from "react-router-dom";
import CleanView from "../../components/CleanView";
import PriceView from "../../components/PriceView";

function PricesPage({ cookie, setCookie }) {
  const to = useNavigate();
  const [chosen, setChosen] = useState(-1);
  const [prices, setPrices] = useState([]);

  useEffect(() => {
    if (!cookie) {
      to("/login");
    } else {
        if (prices.length === 0){
      fetch(server_url + `prices/prices`, {
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
          setPrices(data);
          console.log(1)
        });
    }
  }});

  return [
    <div className={"left " + (window.location.pathname === "/" ? "aaal" : "")}>
      <Header />
      <Prices changeId={setChosen} chosen={chosen} prices={prices} cookie={cookie} />
    </div>,
    <div
      className={"right " + (window.location.pathname === "/" ? "aaar" : "")}
    >
      {chosen === -1 ? 
        <CleanView />
       : 
        <PriceView nav={to} setPrices={setPrices} prices={prices} id={chosen} setId={setChosen}/>
      }
    </div>,
  ];
}

export default PricesPage;
