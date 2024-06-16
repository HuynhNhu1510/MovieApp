require("dotenv").config();

const jwt = require("jsonwebtoken");


const createJWT = (payload) =>{
    // let payload = {name: 'Thao', expiresIn: "10s"};
    let key = process.env.SECRET_KEY;
    let token = null;
    try {
        token = jwt.sign(payload,key);
        // console.log(token);
    }catch(err){
        console.log(err);
    }
    return token;
    }

const verifyAcessToken = (token) =>{
    let key = process.env.SECRET_KEY;
    let data = null;
    jwt.verify(token,key,function(err,decoded){
        if(err){
            console.log(err);
            return data;

        }
        // console.log(decoded);
        data = decoded;

        return decoded;
    });

    return data;

    


    

}

const verifyRefreshToken = (token) =>{
    let key = process.env.REFRESH_KEY;
    let data = null;
    jwt.verify(token,key,function(err,decoded){
        if(err){
            console.log(err);
            return data;

        }
        data = decoded;
        return decoded;
    });

   return data;
}

module.exports = {
    createJWT,verifyAcessToken,verifyRefreshToken
}