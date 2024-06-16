const express = require("express");
const authController = require("../controller/auth.controller");
const auth = require("../middleware/auth");
const route = express.Router();


// post - change Account
route.post("/changeInfo",authController.changeInfoAccount);

// post - change avatar
route.post("/changeInfo",authController.changeAvatar);

//post - resetPass
route.post("/resetPass",authController.resetPass);

//post - resetPass
route.post("/resetForgotPass",authController.resetForgotPass);

// POST - forgotPass
route.post("/forgotPass",authController.forgotPass);

// POST - register
route.post("/registered", authController.registered);

// POST - login
route.post("/login", authController.login);

// POST - refresh 
route.post("/refresh", authController.requestRefreshToken);

// GET - user
route.get("/:id", auth, authController.getAccount);

module.exports = route;
