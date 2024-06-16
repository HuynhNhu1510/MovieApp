const AccountModel = require("../model/account.model");
const RefreshToken = require("../model/refresh_token.model");
const { generateAccessToken, generateRefreshToken } = require("../utils/jwt");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const nodemailer = require('nodemailer');
const {createJWT,verifyAcessToken,verifyRefreshToken} = require("../middleware/JWTActions");

let random;

// register
// register
exports.registered = async (req, res) => {
  try {
    const { email, phone, password } = req.body;

    // checking if it already exists in db
    const existAccount = await AccountModel.findOne({ email });
    const existPhone = await AccountModel.findOne({phone});

    if (existAccount) {
      return res.status(400).json({ code:400, message: "Email address already exists" });
    }

    if(existPhone){
      return res.status(400).json({ code:400, message: "Phone address already exists" });

    }

    // hash
    const salt = await bcrypt.genSalt(10);
    const hash = await bcrypt.hash(password, salt);

    // create new account
    const newAccount = await new AccountModel({
      email,
      phone,
      password: hash,
      
    });

    const account = await newAccount.save();
    console.log(account);

    // res.status(200).json({ success: true, message: "Create successfully", account: account });
    res.status(200).json({ success: true ,code:200, message: "Create successfully" });
  } catch (error) {
    console.log(error.message);
    res.status(500).json({ code:500, message: error.message });
  }
};

// login
exports.login = async (req, res) => {
  try {
    // const account = await AccountModel.findOne({ email: req.body.email });
    const {email,password} = req.body;
    const account = await AccountModel.findOne({ email: req.body.email });
    // console.log(req.body);
    // console.log(req.body.email);
    console.log(email);
    // console.log(account);

    // check email if it exists
    if (!account) {
    return res.status(404).json({code: 404, message:"Invalid email! Please enter again"});
    }

    const validPassword = await bcrypt.compare(req.body.password, account.password);
    // console.log(req.body.password);
    // console.log(account.password);

    if (!validPassword) {
    return  res.status(404).json({  code:404, message: "Invalid password! Please enter again" });
    }

    // login success
    if (account && validPassword) {
      
      // kiem tra refreshToken hien co
      let existingRefreshToken = await RefreshToken.findOne({account_id: account.id});

      // Tái sử dụng refreshToken nếu có
      let refreshToken = existingRefreshToken ? existingRefreshToken.token : generateRefreshToken(account);
      // luu refreshToken
      if(refreshToken){
        account.refreshToken = refreshToken ;
        await account.save();
      }
      const veri = verifyRefreshToken(refreshToken);
      // console.log(veri);
      // console.log(refreshToken);


      


      if(!existingRefreshToken) {
        await RefreshToken.create({token: refreshToken, account_id: account.id});
      }

      const accessToken = generateAccessToken(account);
      
      const {password, ...others} = account._doc;
      
    return res.status(200).json({
        success: true,
        message: "Logged in successfully",
        code : 200,
        accessToken: accessToken,
        refreshToken: refreshToken,
      
        ...others,
      });
    }
  } catch (error) {
    console.log(error.message);
    res.status(500).json({
      success: false,
      code : 500,
      message: "Internal Server Error!!",
    });
  }
};
// refresh token
exports.requestRefreshToken = async (req, res) => {

  const { refreshToken } = req.body;
  console.log(req.body);

  try {
    const refreshTokenDoc = await RefreshToken.findOne({ token: refreshToken });
    if (!refreshTokenDoc) {
      return res.status(403).json({ success: false, message: "Invalid refresh token or expired" });
    }

    jwt.verify(refreshToken, process.env.REFRESH_KEY, async (error, decoded) => {

      if (error) {
        const deleteResult = await RefreshToken.deleteOne({ token: refreshToken });

        if (deleteResult.deletedCount === 0) {
          console.warn("Refresh token not found for deletion:", refreshToken);
        }
        return res
          .status(403)
          .json({ success: false, message: "Invalid refresh token or expired" });
      }

      const account = await AccountModel.findById(decoded.account.id);

      if (!account) {
        return res.status(404).json({ success: false, message: "Cannot find any account" });
      }

      const newAccessToken = generateAccessToken(account);
      const newRefreshToken = generateRefreshToken(account);
      await RefreshToken.findByIdAndUpdate(refreshTokenDoc.id, { token: newRefreshToken });

      return res.status(200).json({ accessToken: newAccessToken, refreshToken: newRefreshToken });
    });
  } catch (error) {
    console.log(error.message);

    res.status(500).json({ message: "Server error" });
  }
};

exports.getAccount = async (req, res) => {
  try {
    const accountId = req.params.id;

    const account = await AccountModel.findById(accountId);

    if (!account) {
      return res.status(404).json({ error: "Acount not found" });
    }

    res.status(200).json(account);
  } catch (error) {
    res.status(500).json(error);
  }
};

exports.changeInfoAccount = async (req, res) => {
  try {
    const { token,newPhone } = req.body;

    const verify = verifyAcessToken(token);
    if(verify==null){
      return res.status(404).json({message: "invalid token"});
    }

    const account = await AccountModel.findById(verify.account.id);

    if (!account) {
      return res.status(404).json({ error: "Acount not found" });
    }

    if(newPhone === account.phone){
   return res.status(404).json({message: " newPhone the same wwith phone "});

    }
    const existPhone = await AccountModel.findOne({phone:newPhone});
    if(existPhone){
      return res.status(404).json({message: " Phone is existed "});

    }

    account.phone = newPhone;
    await account.save();



    res.status(200).json({message: "change success"});
  } catch (error) {
    res.status(500).json(error);
  }
};
exports.changeAvatar = async (req, res) => {
  try {
    const { token,newAvatar } = req.body;

    const verify = verifyAcessToken(token);
    if(verify==null){
      return res.status(404).json({message: "invalid token"});
    }

    const account = await AccountModel.findById(verify.account.id);

    if (!account) {
      return res.status(404).json({ error: "Acount not found" });
    }

    account.imageUrl = newAvatar;
    await account.save();



    res.status(200).json({message: "change success"});
  } catch (error) {
    res.status(500).json(error);
  }
};

async function sendEmail(from, to, subject, text, html) {
  try {
    // Create a secure SMTP transporter for Gmail
    const transporter = nodemailer.createTransport({
      host: process.env.HOST,
      port: process.env.EMAIL_PORT,
      secure: false,
      auth: {
        user: 'thao16068@gmail.com', // Replace with your actual email address
        pass: 'vuas tnke hton mkrc' // Replace with your actual password (store securely)
      }
    });

    // Create the email options
    const mailOptions = {
      from: from,
      to: to,
      subject: subject,
      text: text, // Plain text content
      html: html // Optional HTML content
    };

    // Send the email
    const info = await transporter.sendMail(mailOptions);

    console.log("Email sent: " + info.response);
  } catch (error) {
    console.error("Error sending email:", error);
  }
}

// Function to handle forgot password request (assuming AccountModel is defined)
exports.forgotPass = async (req, res) => {
  try {
    const { email } = req.body; // Destructure email from request body
    
    // const payLoadToken  = await verifyAcessToken(token);
    // let id = payLoadToken.account.id;
    // console.log(id);
    // // check account 
    // const Account = await AccountModel.findById(id);
    // console.log(Account.email);

    // Check if email exists
    const existEmail= await AccountModel.findOne({ email });
    if (!existEmail) {
      return res.status(400).json({ code: 400, message: "Email not found" });
    }
   random = (Math.floor(Math.random() * 900000) + 100000);

    // Generate a random password reset token (implementation omitted)
    //const resetToken = generateRandomPasswordResetToken(); // Replace with your logic

    // Prepare email content (replace with appropriate placeholders)
    const subject = "Required Reset Password!";
    const text = `hihihihi`
    const html = `
    <html>
    <head>
      <style>
        body {
          font-family: Arial, sans-serif;
          background-color: #f4f4f4;
          padding: 20px;
        }
        .container {
          max-width: 600px;
          margin: 0 auto;
          background-color: #fff;
          border-radius: 5px;
          padding: 20px;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
          color: #333;
        }
        p {
          color: #666;
        }
        .code {
          color: #007bff;
          font-weight: bold;
          font-size: 24px;
        }
      </style>
    </head>
    <body>
      <div class="container">
        <h1>MoVie App</h1>
        <p>Hi ${email} ,</p>
        <p>You recently requested a password reset for your account.</p>
        <p>Your code valid time: 3 minutes to reset your password:</p>
        <p class="code">${random}</p>
        <p>If you didn't request a password reset, please ignore this email.</p>
        <p>Sincerely,</p>
        <p>MoVie App Team</p>
      </div>
    </body>
    </html>
    `;

    // Send the password reset email
    await sendEmail('Movie App', email, subject, text, html); // No HTML needed
    res.status(200).json({ random: random, code: 200, message: "Password reset email sent" });
    console.log(random);
    
    // this.resetPass()

  } catch (error) {
    console.error("Error processing forgot password request:", error);
    res.status(500).json({ code: 500, message: "Internal server error" });
  }
};



// exports.resetPass = async(req,res)=>{
// try{



//   const {email,oldPass,newPass} = req.body;

//   console.log(req.body.oldPass);
//     // create token for resetpass
//     const accessToken = createJWT(email);
//     console.log(accessToken); 
   
 
//   const account = await AccountModel.findOne({ email: req.body.email });

//   if (!account) {
//   return res.status(404).json({code: 404, message:"Invalid email! Please enter again"});
//   }
 

//   const validPassword = await bcrypt.compare(oldPass, account.password);
//   if(!validPassword){
//   return res.status(404).json({code: 404, message:"Invalid password! Please enter again"});
//   }
//    // tao token de thay doi mat khau
//   //  const accessToken = generateAccessToken(account);
//   //  console.log(accessToken);
//   //    let veri = verifyAcessToken(accessToken);
//   //    console.log(veri);
//    // hash
//    const salt = await bcrypt.genSalt(10);
//    const hash = await bcrypt.hash(newPass, salt);

//     account.password = hash; 

//    await account.save();

//    return res.status(200).json( {message :"Change password success"});



// } catch (error) {
//   console.error("Error processing forgot password request:", error);
//   res.status(500).json({ code: 500, message: "Internal server error" });
// }
// }


exports.resetPass = async(req,res)=>{
  try{
  
  
  
    const {token,oldPass,newPass} = req.body;
   
    const verify = verifyAcessToken(token);
    console.log(token);

    if(verify == null){
      return res.status(404).json({ message:"Invalid token"});
    }
   
    // const validToken = await AccountModel.findById (verify.account.id);
   
    // if(!validToken)
    //   {
    //     return res.status(404).json({ message:"Invalid token"});
    //   }
    
     
      // create token for resetpass
      // const accessToken = createJWT(email);
      // console.log(accessToken); 
     
   
    const account = await AccountModel.findById(verify.account.id);
    // tao token de resset pass
   
    // console.log(validToken);
   
    if (!account) {
    return res.status(404).json({code: 404, message:"Invalid token! Please enter again"});
    }
   
    

    const validPassword = await bcrypt.compare(oldPass, account.password);
    console.log(oldPass);
    if(!validPassword){
    return res.status(404).json({code: 404, message:"Invalid password! Please enter again"});
    }
     // hash
     const salt = await bcrypt.genSalt(10);
     const hash = await bcrypt.hash(newPass, salt);
  
      account.password = hash; 
  
     await account.save();
  
     return res.status(200).json( {message :"Change password success"});
  
  
   
    // if(resetCode == random){
    //   // todo
      
    // }
    // return res.status(400).json({message: "nhucc"});
  
  } catch (error) {
    console.error("Error processing forgot password request:", error);
    res.status(500).json({ code: 500, message: "Internal server error" });
  }
  }
  exports.resetForgotPass = async(req,res)=>{
    try{
    
    
    
      const {email,newPass} = req.body;
    
      // const validToken =  verifyAcessToken(req.body.token);

      // if(!validToken)
      //   {
      //     return res.status(404).json({ message:"invalid input token"});
      //   }
      
        // create token for resetpass
        // const accessToken = createJWT(email);
        // console.log(accessToken); 
       
     
      const account = await AccountModel.findOne({email:req.body.email});
     
      // tao token de resset pass


      // console.log(validToken);
     
      if (!account) {
      return res.status(404).json({code: 404, message:"Invalid account"});
      }
     
    
  
       // hash
       const salt = await bcrypt.genSalt(10);
       const hash = await bcrypt.hash(newPass, salt);
    
        account.password = hash; 
    
       await account.save();
    
       return res.status(200).json( {message :"Change password success"});
    
    
     
      // if(resetCode == random){
      //   // todo
        
      // }
      // return res.status(400).json({message: "nhucc"});
    
    } catch (error) {
      console.error("Error processing forgot password request:", error);
      res.status(500).json({ code: 500, message: "Internal server error" });
    }
    }