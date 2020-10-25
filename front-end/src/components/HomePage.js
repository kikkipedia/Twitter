import React, { useContext } from 'react'
import {Button,Col, Row, Container} from "react-bootstrap"
import { authContext } from '../context/AuthContext'
import Profile from './Profile'
import Tweets from './Tweets'


const HomePage = () => {

    const {setAuthData, auth} = useContext(authContext)

    const onLogOut = () => {
        setAuthData(null)
    }

        return(
            <>
            <Row>
                <Col>
                    <Container>
                        <h2 className="text-center">{`Welcome ${auth.data}`} </h2>
                        <Profile/>
                        <Button onClick={onLogOut}>Logout</Button>
                    </Container>
                    </Col>
                <Col>
                Twitterfeed
                    <Container>
                        <Tweets/>
                    </Container>
                    </Col>
            </Row>
            </>
        )
    }

export default HomePage