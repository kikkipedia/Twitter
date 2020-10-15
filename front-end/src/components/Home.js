import React from 'react'
import Signup from './Signup'
import Login from './Login'

export default class Home extends React.Component {
    constructor(props) {
        super(props);

        this.handleSuccessfulAuth = this.handleSuccessfulAuth.bind(this)
    }

    handleSuccessfulAuth(data) {
        this.props.handleLogin(data) 
        this.props.history.push("/tweets")
    }

    render() {
        return(
            <div>
                <h2>welcome to fakeTwitter</h2>
                <p>Status: {this.props.loggedInStatus}</p>
                <Signup handleSuccessfulAuth={this.handleSuccessfulAuth} />
                <br/>
                <Login handleSuccessfulAuth={this.handleSuccessfulAuth} />
            </div>
        )
    }
}