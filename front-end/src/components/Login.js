import React from 'react'
import {Card,Form,Button,Col, Row, Container} from "react-bootstrap"

export default class Login extends React.Component {
    constructor(props) {
        super(props)
        this.handleSuccessfulAuth = this.handleSuccessfulAuth.bind(this)
        this.state = {
            users: [],
            username: "",
            password: "",
            id: "",
        }
    }

    componentDidMount = () => {
        fetch("http://ec2-13-48-47-134.eu-north-1.compute.amazonaws.com:8080/users/all")
        .then(res => res.json())
        .then((res) => {
            this.setState ({ users: res })
        })
    }

    handleSuccessfulAuth(data) {
        //TODO update parent component app.js
        this.props.history.push("/tweets") //after login success
    }



    // function(s) that serarches users for correct and matches password
    handleLogin = (e) => {
        console.log(this.state.users)
        e.preventDefault()
        var users = this.state.users
        var username = this.state.username
        var passwordInput = this.state.password

        var user = users.find(el => el.username === username && el.password === passwordInput)
        if (user === undefined) {
            console.log("error")
        }
        else {
            console.log(user)
            this.props.handleSuccessfulAuth(user.data)
        }
        
    }


    render() {


        return(
            <div>
        <Container>
          <Row>
            <Col></Col>
            <Col>
            <br/>
            <br/>
            <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>Login</h3></Card.Header>
                    <Card.Body>
                            <Form.Row>
                                <Form.Group as= {Col}>
                                    <Form.Label>Username</Form.Label>
                                    <Form.Control required autoComplete="off"
                                    type="text"
                                    id = "username"
                                    name = "username"
                                    value={this.state.value}
                                    onChange= {e => this.setState({username: e.target.value})}
                                    className={"bg-dark text-white"}
                                    placeholder= "Username" />
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control required autoComplete="off"
                                    type="text"
                                    id = "password"
                                    name = "password"
                                    value={this.state.value}
                                    onChange= {e => this.setState({password: e.target.value})}
                                    className={"bg-dark text-white"}
                                    placeholder= "Password" />
                                </Form.Group>
                            </Form.Row>
                            <Card.Footer style={{"textAlign":"right"}}>
                                <Button size="sm" variant="primary" onClick={this.handleLogin}>Login</Button>
                            </Card.Footer>
                        </Card.Body>
                    
                </Card>
            
            </Col>
            <Col></Col>
          </Row>
          </Container>
            
            </div>
    
        )
    }
}