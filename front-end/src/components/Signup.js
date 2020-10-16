import React from 'react'
import {Card,Form,Button,Col, Container, Row} from "react-bootstrap"

export default class Signup extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            username: "",
            password: ""
        }
    }
    
    handleSend = () => {
        fetch("http://ec2-13-48-47-134.eu-north-1.compute.amazonaws.com:8080/users/add", {
            method: "POST",
            headers: {
                Accept: "application/json", "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: this.state.username,
                password: this.state.password
            })   
        })
        .then((response) => response.json())
        .then(response => {
            //TODO: if-registration = success:
            console.log("response: " + response.data)
            this.props.handleSuccessfulAuth(response.data)
        })
    }
    
    render() {
        return (
            <div>
                <br/>
                <Container>
                    <Row>
                <Col></Col>
                <Col>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>Register</h3></Card.Header>
                    <Form onSubmit={this.handleSend}>
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as= {Col}>
                                    <Form.Label>Username</Form.Label>
                                    <Form.Control required autoComplete="off"
                                    type="text"
                                    value={this.state.username}
                                    onChange= {e => this.setState({username: e.target.value})}
                                    className={"bg-dark text-white"}
                                    placeholder= "Username" />
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control required autoComplete="off"
                                    type="text"
                                    value={this.state.password}
                                    onChange= {e => this.setState({password: e.target.value})}
                                    className={"bg-dark text-white"}
                                    placeholder= "Password" />
                                </Form.Group>
                            </Form.Row>
                            <Card.Footer style={{"textAlign":"right"}}>
                                <Button size="sm" variant="primary" onClick={this.handleSend}>Submit</Button>
                            </Card.Footer>
                        </Card.Body>
                    </Form>
                </Card></Col>
                <Col></Col>
                </Row>
                </Container>
            </div>
        )
    }

}