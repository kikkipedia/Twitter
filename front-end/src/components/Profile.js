import React from 'react'
import {Form, Button, Col, Row, Container, Card} from "react-bootstrap"

export default class Profile extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            id: "",
            username: "",
            bio: ""
        }
    }

    render() {

        return(
            <div>
                <Card>
                    Welcome "USERNAME"
                    <Form.Row>
                        <Form.Group as= {Col}>
                           <Form.Label>Update your bio:</Form.Label>
                           <Form.Control required autoComplete="off"
                                type="text"
                                id = "bio"
                                name = "bio"
                                value=""
                                onChange= {e => this.setState({bio: e.target.value})}
                                className={"bg-dark text-white"}
                                placeholder= "" />
                            <Button>Submit</Button>
                        </Form.Group>
                    </Form.Row>
                </Card>
            </div>
        )
    }
}