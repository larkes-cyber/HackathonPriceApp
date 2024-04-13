import React, {useEffect, useState} from "react";
import Header from "../../components/Header";
import Users from "../../components/Users"
import UserSetting from "../../components/UserSetting";
import CleanUser from "../../components/CleanUser";
import { useNavigate } from "react-router-dom";
import server_url from "../../site"

function UsersPage({cookie, }) {

    function fetchUsers() {
        fetch(server_url + `users/users`, {
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
                console.log(data)
              setUsers(data);
            });
    }
    function changeUserLevel() {

    }
    function changeUserSpam() {

    }

    const [users, setUsers] = useState([])
    const [chosen, choose] = useState(-1)
    const to = useNavigate()

    useEffect(()=> {
        if (users.length === 0){
        fetchUsers()}
    })


    return  [
        <div className={"left " + (window.location.pathname === "/" ? "aaal" : "")}>
          <Header />
          <Users users={users} chosen={chosen} choose={choose}/>
        </div>,
        <div
          className={"right " + (window.location.pathname === "/" ? "aaar" : "")}
        >
          { chosen > 0?
          <UserSetting
            update={fetchUsers}
            nav={to}
            users={users}
            cookie={cookie}
            choose={choose}
            id={chosen}
          /> : <CleanUser/>}
        </div>,
      ];
}


export default UsersPage