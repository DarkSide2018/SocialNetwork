import {Component} from "react";
import React from 'react';

 class ReactEventSourcing extends Component{
    constructor(props) {
        super(props)
        this.eventSource = new EventSource("http://localhost:8080/event/news");
        this.state = {
            data: []
        };
    }
    componentDidMount() {
        this.eventSource.onmessage = e =>{

            let parse = JSON.parse(e.data);
            console.log("element-> " + parse.content)
            this.updateFlightState(parse);
        }
    }

    updateFlightState(parse) {
        let newData = this.state.data.concat(parse.content)

        this.setState(Object.assign({}, { data: newData }));
    }
     render() {
         return (
             <div>
                 {this.state.data}
             </div>
         );
     }
}
export default ReactEventSourcing;