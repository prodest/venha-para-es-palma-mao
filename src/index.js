import express from "express";
import api from "./modules/app.controller";

var app = express();
app.get("/", (req, res) => {
  res.send("OK");
});
app.use("/api", api.router);
app.listen(process.env.PORT);
console.log("Server listening", process.env.PORT);
