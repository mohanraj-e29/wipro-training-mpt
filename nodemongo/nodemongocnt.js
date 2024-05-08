require('dotenv').config();
const bodyParser = require('body-parser');
const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');

const app = express();

const conn = process.env.CONN;
const PORT = process.env.PORT || 3000;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cors());

mongoose.connect(conn)
    .then(() => console.log('Connected to MongoDB'))
    .catch(err => console.error('Error connecting to MongoDB:', err));

const demoSchema = new mongoose.Schema({
    id: {
        type: Number,
        required: true
    },
    title: {
        type: String,
        required: true
    },
    description: {
        type: String,
        required: true
    },
    time: {
        type: Date,
        default: Date.now
    }
});

const user = mongoose.model('mymodel', demoSchema, 'apptest');

app.get("/", (req, res) => {
    res.end("Hello world");
});

app.get("/about", (req, res) => {
    res.end("Welcome to the about page");
});

app.get("/name/:myname", (req, res) => {
    res.end("Welcome " + req.params.myname);
});

app.get("/data", async (req, res) => {
    try {
        const data = await user.find();
        res.json(data);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: "Internal server error" });
    }
});

// app.get("/register", async (req, res) => {
//     try {
//         const user = await user.find();
//         res.json(user);
//     } catch (error) {
//         console.error(error);
//         res.status(500).json({ error: "Internal server error" });
//     }
// });

app.post("/login", (req, res) => {
    const body = req.body;
    const username = body.username;
    const pass = body.pass;

    if (username === "aryan" && pass === 123)
        res.json({ data: "success" });
    else
        res.end("Incorrect credentials");
});

app.post('/create', async (req, res) => {
    const body = req.body;
    const { id, title, description, time } = body;

    try {
        const insertedUser = await user.create({ id, title, description, time });
        res.json({ msg: "User inserted successfully", data: insertedUser });
    } catch (error) {
        console.error('Error creating user:', error);
        res.status(500).json({ error: 'Internal server error' });
    }
});

const userSchema = new mongoose.Schema({
    username: { type: String, required: true },
    password: { type: String, required: true }
});

const User = mongoose.model('User', userSchema);

app.post('/register', async (req, res) => {
    const { username, password } = req.body;

    try {
        const newUser = await User.create({ username, password });
        res.json({ message: 'User registered successfully', data: newUser });
    } catch (error) {
        console.error('Error registering user:', error);
        res.status(500).json({ error: 'Internal server error' });
    }
});

app.get('/id/:name', async (req, res) => {
    const name = req.params.name;

    try {
        const namedUser = await user.find({ name });
        res.json({ msg: "success", data: namedUser });
    } catch (error) {
        console.error('Error fetching user by name:', error);
        res.status(500).json({ error: 'Internal server error' });
    }
});


//https://nodejs-mongodb-cnts.onrender.com
app.listen(PORT, () => console.log("Application started on PORT " + PORT));
