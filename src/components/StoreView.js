import React, { useRef, useReducer, useState, useEffect } from "react";
import addIcon from "../icons/Add.svg";
import SearchIcon from "../icons/SearchIcon.svg";
import Settings from "../icons/Settings.svg";
import Clear from "../icons/CloseBig.svg";
import Edit from "../icons/Edit.svg";
import FileIcon from "../icons/FileIcon";
import Heart from "../icons/Union.svg";
import HeartFill from "../icons/Union3.svg";
import site_url from "../site";

import "../styles/priceview.scss";
import FileUI from "./FileUI";
import FilePrice from "./PriceUpload";
import { useParams } from "react-router-dom";
import ContentLoader from "react-content-loader";

function StoreView({ prices, nav, id, setPrices, setId}) {
  let price = prices.filter(v=>v.id===id)[0];
  console.log(price)
  const [, forceUpdate] = useReducer((x) => x + 1, 0);
  // console.log(price, id)
  const [img_, setImg] = useState(
    <ContentLoader
      speed={2}
      backgroundColor="#f3f3f3"
      foregroundColor="#ecebeb"
      style={{
        width: "calc(100% - 0.3em)",
        borderRadius: "10px",
        marginBottom: "1em",
        height: "15em",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        border: "2px dashed #818C99",
      }}
    >
      {" "}
      <rect x="0" y="0" rx="0" ry="0" width="100%" height="100%" />
    </ContentLoader>
  );

  console.log(price);
  return (
    <div className="priceViewContainer">
      <div className="topBarWrapper">
        <div
          onClick={() => {
            setId(-1)
          }}
          className="close c"
        >
          <img src={Clear} />
        </div>
        <div className="addTitle ">{price.name}</div>
        <div className="spacer"/>
      </div>

      <div className="container">
        <div className="imgWrapper">{img_}</div>
        <div className="upinfo">
          <div className="up">
            {price.store} · {price.user}{" "}
            {/* <img
              className="c"
              onClick={() => {
                price.featured = !price.featured;
                let y = prices;
                y[id] = price;
                setPrices(y);
              }}
              src={price.featured ? Heart : HeartFill}
              alt=""
            /> */}
          </div>
          <div>
            <div className="button document">{price.category}</div>
            {/* <a download={price.title+".pdf"}href={site_url+"get_pdf?id="+price.data.id} className="button document">PDF</a> */}
          </div>
        </div>
      </div>
    </div>
  );
}

export default StoreView;
