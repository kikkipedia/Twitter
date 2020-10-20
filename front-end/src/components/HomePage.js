import React from 'react'
import {Card,Form,Button,Col, Row, Container} from "react-bootstrap"

export default class HomePage extends React.Component {

    render() {

        return(
            <>
            <Row>
                <Col>
                Profile & send msg
                    <Container>
                        info here
                        <hr/>
                        input and send
                    </Container>
                    </Col>
                <Col>
                Twitterfeed
                    <Container>
                        here
                    </Container>
                    </Col>
            </Row>
            </>
        )
    }
}