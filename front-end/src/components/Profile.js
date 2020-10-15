import React from 'react'
import {Form, Button, Col, Row, Container} from "react-bootstrap"

export default class Profile extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            id: "",
            user: [],
            username: "",
            bio: ""
        }
    }
    // user id as props from login
    // hämta user info och möjliggör edit
/*     componentDidMount(props){
        // id från login
        console.log("id: ",this.props.location.state.id)
        this.setState ({id: this.props.location.state.id})
        fetch("http://localhost:8080/users/all")
        .then(res => res.json())
        .then((result) => {
            for(var i = 0; i < result.length; i++) {
                if(result[i].id === this.state.id) {
                    this.setState ({user: result[i]})
                }
            }
            console.log(this.state.user)
        })
    } */

    handleUpdate = () => {
        fetch("http://localhost:8080/users/all/" + this.state.id, {
            method: "PUT",
            headers: {
                Accept: "application/json", "Content-Type": "application/json"
            },
            body: JSON.stringify({
                bio: this.state.bio,
                username: this.state.user.username,
                password: this.state.user.password
            })
        })
        console.log("Updated: " + this.state.bio)
    }

    render() {

        return(
            <div>
                <Container>
                    <Row>
                      <Col></Col>
                      <Col>
                        <h2>hej {this.state.user.username}</h2>
                        <p>Change bio:</p>
                        <p>(max 255 characters)</p>
                        <Form>
                            <Form.Group as= {Col}>
                                <Form.Control required autoComplete="off"
                                    as="textarea"
                                    rows="10"
                                    id = "bio"
                                    name = "bio"
                                    value={this.state.bio}
                                    onChange= {e => this.setState({bio: e.target.value})}
                                    className={"bg-dark text-white"}
                                    maxLength={255}
                                    placeholder= {this.state.user.bio} />
                                    
                            </Form.Group>
                            <Button onClick={this.handleUpdate}>Update</Button>
                        </Form>
                      </Col>
                      <Col></Col>  
                    </Row>
                </Container>
                
            </div>
        )
    }
}