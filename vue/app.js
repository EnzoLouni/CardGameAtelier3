const http = require("http");
const express = require("express");
const cors = require("cors");
const path = require("path");

const hostname = "127.0.0.1";
const port = 3001;

const app = express();

app.use(cors());
app.use(express.json({ limit: "50mb" }));

app.get("/", function (req, res) {
  res.sendFile(path.join(__dirname, "/public/index.html"));
});

app.get("/cards", function (req, res) {
  res.sendFile(path.join(__dirname, "/public/card.html"));
});

app.get("/home", function (req, res) {
  res.sendFile(path.join(__dirname, "/public/home.html"));
});

app.get("/newCard", function (req, res) {
  res.sendFile(path.join(__dirname, "/public/newCard.html"));
});

app.get("/newRoom", function (req, res) {
  res.sendFile(path.join(__dirname, "/public/newRoom.html"));
});

app.get("/register", function (req, res) {
  res.sendFile(path.join(__dirname, "/public/register.html"));
});

app.get("/rooms", function (req, res) {
  res.sendFile(path.join(__dirname, "/public/room.html"));
});

app.get("/shop", function (req, res) {
  res.sendFile(path.join(__dirname, "/public/shop.html"));
});

app.listen(port, () => {
  console.log(`Application exemple à l'écoute sur le port ${port}!`);
});
