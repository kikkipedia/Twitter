const mysql = require('mysql')
const mysqlURL = "http://ec2-13-48-47-134.eu-north-1.compute.amazonaws.com:8080"

const connection = mysql.createConnection({
    host: mysqlURL,
    user: 'root',
    password: 'TheSuperPassword',
    database: 'twitter'
})

const express = require("express")
const app = express()
const cors = require("cors")
app.use(cors())
app.use(express.json())

exports.handler = (event, context, callback) => {
    context.callbackWaitsForEmptyEventLoop = false;
    
    app.post("/users/add", async(req, res) => {
    try {
        const postUser = {username: req.body.username, password: req.body.password, bio: req.body.bio}
        const {newUser} = await connection.query (
            "INSERT INTO user SET ?", postUser
        );
    } catch (err) {
        console.error(err.message)
    }
})

// get all users
app.get("/users",(req,res) => {
    connection.query('SELECT * from user', (err, rows) => {
        if(err) throw err;
        res.json(rows)
    });
});

// get one user
app.get("/users/:id", async (req, res) => {
    const {id} = req.params
    connection.query("SELECT * FROM user WHERE id = ?", [id], function (err, row) {
        if(err) throw err;
        res.json(row)
    })
})

// update bio
app.put("/users/:id", async (req, res) => {
    const {id} = req.params
    const {bio} = req.body
    connection.query("UPDATE user SET bio = ? WHERE id = ?", [ bio, id], function (err,row) {
        if(err) throw err
        res.json("updated")
    }  )
})

// delete a user
app.delete("/users/:id", async (req, res) => {
    const {id} = req.params
    connection.query("DELETE FROM user WHERE id = ?", [id])
    res.json("user deleted")
})
    
}
