import React from "react";

import Header from "../../components/Header";
import Users from "../../components/Users"




function UsersPage() {

    function fetchUsers() {

    }
    function changeUserLevel() {

    }
    function changeUserSpam() {

    }

    const [users, setUsers] = useState([])

    



    return  [
        <div className={"left " + (window.location.pathname === "/" ? "aaal" : "")}>
          <Header />
          <Users users={users} is_create={is_create}/>
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


export default UsersPage