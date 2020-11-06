import React, {useState, useContext} from 'react'
import {Form, Button, Col, Card} from "react-bootstrap"
import {authContext} from '../context/AuthContext'

const Profile = () => {

    const [tweet, setTweet] = useState()
    const {auth} = useContext(authContext)

    const submitTweet = (e) => {
        e.preventDefault()
        fetch("https://6z4gbjcjfl.execute-api.eu-north-1.amazonaws.com/default/node-api/users/" + auth.data.id)
        .then(res => res.json())
        .then(data => console.log(data))
    }


        return(
            <div>
                <Card>
                    <Form.Row>
                        <Form.Group as= {Col}>
                           <Form.Label>Send a tweet:</Form.Label>
                           <Form.Control required autoComplete="off"
                                type="text"
                                id = "bio"
                                name = "bio"
                                value=""
                                onChange= {e => setTweet(e.target.value)}
                                className={"bg-dark text-white"}
                                placeholder= "" />
                            <Button onClick={submitTweet}>Tweet!</Button>
                        </Form.Group>
                    </Form.Row>
                </Card>
            </div>
        )
    }
export default Profile