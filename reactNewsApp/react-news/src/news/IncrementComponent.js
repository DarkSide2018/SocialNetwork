import React, {Component} from 'react';


export default class IncrementComponent extends Component{
    constructor(props) {
        super(props);
        this.state = {
            count: 0,
            messages: ["empty"]
        }
        this.increment = this.increment.bind(this)
        this.decrement = this.decrement.bind(this)
        this.reset = this.reset.bind(this)
    }
    componentDidMount() {

    }

    increment(){
        this.state.messages.push("MSG")
        this.setState(state => (
            {
                count: state.count + 1,


            }
        ))
    }
    decrement(){
        this.setState(state => (
            {
                count: state.count - 1
            }
        ))
    }
    reset(){
        this.setState(state => (
            {
                count: 0
            }
        ))
    }
    render(){
        return (
            <div>
                <button onClick={this.increment}>increment</button>
                <button onClick={this.decrement}>decrement</button>
                <button onClick={this.reset}>reset</button>
                <h1>Current: {this.state.count}</h1>
                <h1>Current Messages: {this.state.messages.toString()}</h1>
            </div>
        )
    }
}





