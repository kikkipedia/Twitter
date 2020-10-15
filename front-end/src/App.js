import React from 'react';
import './App.css';
import { BrowserRouter as Router, Switch, Route} from "react-router-dom"
//import Signup from './components/Signup'
import Login from './components/Login'
import Navigation from './Navigation'
import Tweets from './components/Tweets'
import Profile from './components/Profile'
import Signup from './components/Signup'
import Home from './components/Home'
//import ProtectedRoutes from './components/ProtectedRoutes'
class App extends React.Component {
  constructor() {
    super()
    this.state = {
      loggedInStatus: "Not_logged_in",
      loggedInUser: {}
    }
    this.handleLogin = this.handleLogin.bind(this)
  }

  handleLogin(data) {
    this.setState({
      loggedInStatus: "Logged_in!",
      loggedInUser: data
    })
  }

  render() {

    return (
    
      <div className="App">
        <Router>
        <Navigation />
        <Switch>
          <Route exact path="/" render = {props => (
            <Home {...props} handleLogin={this.handleLogin} loggedInStatus={this.state.loggedInStatus}/>
          )}/>
          <Route exact path="/profile" render={props => (
            <Profile {...props} loggedInStatus={this.state.loggedInStatus} />
          )}/>
          <Route exact path="/tweets" render={props => (
            <Tweets {...props} loggedInStatus={this.state.loggedInStatus} />
          )}/>
          <Route exact path="/login" render={props => (
            <Login {...props} loggedInStatus={this.state.loggedInStatus} />
          )}/>
        </Switch>
      </Router>

        
      </div>
      
      
    )

  }

  
}

export default App;
