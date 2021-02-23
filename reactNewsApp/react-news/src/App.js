import React from 'react'
import './App.css';
import NewsList from './news/NewsList.js'
import IncrementComponent from "./news/IncrementComponent";
import {Dashboard} from "./news/Dashboard";
import ReactEventSourcing from "./news/ReactEventSourcing";


function App() {

    return (
    <div className="App">
        <Dashboard/>
    </div>

  );

}

export default App;
