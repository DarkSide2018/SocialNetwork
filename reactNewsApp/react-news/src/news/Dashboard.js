import React, {useEffect, useState} from 'react'

export const Dashboard = () => {

    const [listeningTopic, setListeningTopic] = useState(false);
    const [valueFromTopic, setValueFromTopic] = useState([]);

    let eventSourceTopic = undefined;

    useEffect(() => {
        if (!listeningTopic) {
            eventSourceTopic = new EventSource("http://localhost:8080/event/news");
            eventSourceTopic.onmessage = (event) => {
                const news = JSON.parse(event.data);
                setValueFromTopic((valueFromTopic) => valueFromTopic.concat(news.content))
            }
            eventSourceTopic.onerror = (err) => {
                console.error("EventSource failed:", err);
                eventSourceTopic.close();
            }
            setListeningTopic(true)
        }

        return () => {
            eventSourceTopic.close();
            console.log("event closed")
        }

    }, [])
    let from = Array.from(valueFromTopic);
    if(from.length>4){
        valueFromTopic.splice(0,1)
    }else if(from.length === 0){
        fetch("http://localhost:8080/event/last/news")
            .then(res => res.json())
            .then(
                (result) => {
                    result.forEach((el)=>{
                        setValueFromTopic((valueFromTopic) => valueFromTopic.concat(el.content))
                    })
                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    return (
        <div style={{"marginTop": "20px", "textAlign": "center"}}>
            {
            }
            <h1>Dashboard</h1>
            <div>
                <ol>
                    {
                        from.map(el=>{
                            return <li value={el} key={uuidv4()} >{el}</li>
                        })
                    }
                </ol>

            </div>
        </div>
    )
}

function uuidv4() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        const r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}
