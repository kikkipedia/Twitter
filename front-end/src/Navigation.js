import React from 'react'
import {Navbar, Nav, Form} from "react-bootstrap"

export default class Navigation extends React.Component {
    constructor(){
        super() 
        this.state = {
            loggedIn: false
        }
    }


    render() {
        return(
            <>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="/">FakeTwitter</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="/profile">Profile</Nav.Link>
                    <Nav.Link href="/tweets">Tweet</Nav.Link>
                </Nav>
                <Form inline>
                    <Nav.Link href="/login">Sign in</Nav.Link>
                </Form>
            </Navbar>
            </>
        )
    }
}