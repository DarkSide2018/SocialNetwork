import logo from './logo.svg';
import './App.css';



function App() {


    function handleClick() {
        alert("It works!!!!!")
    }

    return (
    <div className="App">

            <button onClick={() => handleClick()} type="button" className='btn'>How it Works</button>


    </div>
  );

}

export default App;
