import React, {useState, useContext} from 'react'
import {Card,Form,Button,Col, Container, Row} from "react-bootstrap"
import {authContext} from '../context/AuthContext'
import { useHistory } from 'react-router-dom'

const Signup = () => {

    const [username, setUsername] = useState()
    const [password, setPassword] = useState()
    const {setAuthData} = useContext(authContext)
    const history = useHistory()

    
    const handleSend = () => {
        fetch("https://6z4gbjcjfl.execute-api.eu-north-1.amazonaws.com/default/node-api/users/add", {
            method: "POST",
            headers: {
                Accept: "application/json", "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })   
        })
        .then((response) => response.json())
        .then(res => {
            console.log("success")
            setAuthData(username)
            history.replace("/")
        }).catch(error => {
            console.log(error)
        })
    }
    
        return (
            <div>
                <Container>
                    <Row>
                <Col>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>Register</h3></Card.Header>
                    <Form onSubmit={handleSend}>
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as= {Col}>
                                    <Form.Label>Username</Form.Label>
                                    <Form.Control required autoComplete="off"
                                    type="text"
                                    onChange= {e => setUsername(e.target.value)}
                                    className={"bg-dark text-white"}
                                    placeholder= "Username" />
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control required autoComplete="off"
                                    type="text"
                                    onChange= {e => setPassword(e.target.value)}
                                    className={"bg-dark text-white"}
                                    placeholder= "Password" />
                                </Form.Group>
                            </Form.Row>
                            <Card.Footer style={{"textAlign":"right"}}>
                                <Button size="sm" variant="primary" onClick={handleSend}>Submit</Button>
                            </Card.Footer>
                        </Card.Body>
                    </Form>
                </Card></Col>
                </Row>
                </Container>
            </div>
        )
    }
export default Signup