import React, {useState, useContext} from 'react'
import {Card,Form,Button,Col, Row, Container} from "react-bootstrap"
import {authContext} from '../context/AuthContext'
import { useHistory } from 'react-router-dom'

const Login = () => {
    const [username, setUsername] = useState()
    const [password, setPassword] = useState()
    const {setAuthData} = useContext(authContext)
    const history = useHistory()

    // function that serarches users for correct and matches password
    const handleLogin = (e) => {
        e.preventDefault()
        //const users = []
        fetch("http://localhost:8080/users/all")
        .then(res => res.json())
        .then(data => {
            console.log(data)
            var user = data.find(el => el.username === username)
            if (user === undefined) {
                console.log("error")
            }
            else {
                console.log(user)
                setAuthData(username)
                history.replace("/")
            }    
        })
            
    }


        return(
            <div>
        <Container>
          <Row>
            <Col>

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
                                    /* value={this.state.value} */
                                    onChange= {e => setUsername(e.target.value)}
                                    className={"bg-dark text-white"}
                                    placeholder= "Username" />
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control required autoComplete="off"
                                    type="text"
                                    id = "password"
                                    name = "password"
                                    /* value={this.state.value} */
                                    onChange= {e => setPassword(e.target.value)}
                                    className={"bg-dark text-white"}
                                    placeholder= "Password" />
                                </Form.Group>
                            </Form.Row>
                            <Card.Footer style={{"textAlign":"right"}}>
                                <Button size="sm" variant="primary" onClick={handleLogin}>Login</Button>
                            </Card.Footer>
                        </Card.Body>    
                </Card>    
            </Col>
          </Row>
          </Container>
            
            </div>
    
        )
    }
export default Login