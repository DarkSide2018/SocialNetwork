import logo from './logo.svg';
import './App.css';
import NewsList from './news/NewsList.js'


function App() {


    function handleClick() {
        alert("It works!!!!!")
    }

    return (
    <div className="App">

            <button onClick={() => handleClick()} type="button" className='btn'>How it Works</button>



        <NewsList/>
    </div>

  );

}

export default App;
