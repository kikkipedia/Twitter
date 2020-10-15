import React from 'react'
import { Redirect } from 'react-router-dom'

class ProtectedRoutes extends React.Component {

    render() {
        const Tweets = this.props.tweets
        const isAuthenticated = true
       
        return (isAuthenticated ? (
            <Tweets />
        ) : (
            <Redirect to={{ pathname: '/login' }} />
        )
        )
    }
}

export default ProtectedRoutes