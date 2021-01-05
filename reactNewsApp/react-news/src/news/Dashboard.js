import React, { useEffect, useState } from 'react'


export const Dashboard = () => {

    const [listeningTopic, setListeningTopic] = useState(false);
    const [valueFromTopic, setValueFromTopic] = useState("0");

    let eventSource = undefined;
    let eventSourceTopic = undefined;

    useEffect(() => {
        if (!listeningTopic) {
            eventSourceTopic = new EventSource("http://localhost:8080/event/news");
            eventSourceTopic.onmessage = (event) => {
                const news = JSON.parse(event.data);
                setValueFromTopic(news.content)
            }
            eventSourceTopic.onerror = (err) => {
                console.error("EventSource failed:", err);
                eventSource.close();
            }
            setListeningTopic(true)
        }
        return () => {
            eventSourceTopic.close();
            console.log("event closed")
        }

    }, [])


    return (
        <div style={{ "marginTop": "20px", "textAlign": "center" }}>
            <h1>Dashboard</h1>
            <div>
                {valueFromTopic}
            </div>
        </div>

    )
}