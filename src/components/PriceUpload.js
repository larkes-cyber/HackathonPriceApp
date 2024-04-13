import React, {useState, useRef} from "react";
import addIcon from "../icons/add_24.svg"

function FileUploadSingle({file, setFile}) {
    // const [file, setFile] = useState(File());
  
    const handleFileChange = (e) => {
      if (e.target.files) {
        setFile(e.target.files[0]);
      }
    };
    const fileRef = useRef()
    
  
    let fileUI = (
        <div className="lecUpload" onClick={() => fileRef.current.click()}>
          
          <div  className="lecUpBtn"><img src={addIcon}/>Выбрать файл</div>
          {/* <div style={{width:"100%", marginTop: "0.5em", textAlign: "center"}}>Загрузите обложку </div> */}
          <input
                accept='audio/*'
            ref={fileRef}
            onChange={handleFileChange}
            multiple={false}
            type="file"
            hidden
          />
        </div>
      );
      if (file) {
        // let alt = "";
        // if (fileType !== "image") {
        //   console.log();
        //   alt = (
        //     <div className="altimg" onClick={() => fileRef.current.click()}>
        //       <h3 className="alt">{fileType}</h3>
        //       <p className="filename">{fileName}</p>
        //     </div>
        //   );
        // }
        fileUI = (
          <div className="lecUpload">
          
              {/* <img src={file} alt="error" onClick={() => fileRef.current.click()} /> */}
              <div  className="lecUpBtn"><img src={addIcon}/>Выбрать файл</div><div className="changebutton">
              <input
                accept='audio/*'
                ref={fileRef}
                onChange={handleFileChange}
                multiple={false}
                type="file"
                hidden
              />
              <div>{file.name}</div>
              {/* <p className="uploadeddesc">Нажмите на изображение для изменения</p> */}
            </div>
          </div>
        );
      }
      return fileUI
  }
  
  export default FileUploadSingle;