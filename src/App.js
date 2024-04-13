import './styles/App.scss';
import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router';
import {BrowserRouter, Route, Routes, useParams, useSearchParams } from 'react-router-dom';
import CleanView from './components/CleanView';
import CreateView from './components/CreateView';
import PriceView from './components/PriceView';
import Header from './components/Header';
import Prices from './components/Prices';

import PricesPage from './pages/Prices';
import SignUpPage from './pages/SignUp';
import LoginPage from './pages/Login';
import StoresPage from './pages/Stores';
import UsersPage from './pages/Users';
import Panel from './pages/Panel';

function useStoredState(key, defaultValue) {
  // 👇 Load stored state into regular react component state
  const [state, setState] = useState(() => {
    const storedState = localStorage.getItem(key);
    // console.log(storedState)
    if (storedState) {
      // 🚩 Data is stored as string so need to parse
      return JSON.parse(storedState);
    }

    // No stored state - load default value.
    // It could be a function initializer or plain value.
    return defaultValue;
  });

  // 👇 Keeps the exact same interface as setState - value or setter function.
  const setValue = (value) => {
    const valueToStore = value;
    localStorage.setItem(key, JSON.stringify(valueToStore));
    setState(valueToStore);
  };

  // as const tells TypeScript you want tuple type, not array.
  return [state, setValue] ;
}


function App() {

  const [cookie, setCookie] = useStoredState('cookie', "")
  return (
    <div className="App">
      <Routes>
        <Route path='/' element={<Panel cookie={cookie} setCookie={setCookie}/>}/>
        <Route path='/prices' element={<PricesPage cookie={cookie} setCookie={setCookie}/>}/>
        <Route path='/stores' element={<StoresPage cookie={cookie}/>}/>
        <Route path='/login' element={<LoginPage setCookie={setCookie}/>}/>
        <Route path='/signup' element={<SignUpPage setCookie={setCookie}/>}/>
        <Route path='/users' element={<UsersPage/>}/>
      </Routes>
    </div>
  );
}

export default App;
