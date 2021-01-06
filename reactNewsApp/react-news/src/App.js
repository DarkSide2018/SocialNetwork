
import './App.css';
import NewsList from './news/NewsList.js'
import IncrementComponent from "./news/IncrementComponent";
import {Dashboard} from "./news/Dashboard";
import ReactEventSourcing from "./news/ReactEventSourcing";


function App() {


    function handleClick() {
        alert("It works!!!!!")
    }

    return (
    <div className="App">

            <button onClick={() => handleClick()} type="button" className='btn'>How it Works</button>

        <NewsList/>
        <IncrementComponent/>
        <Dashboard/>
    </div>

  );

}

export default App;
