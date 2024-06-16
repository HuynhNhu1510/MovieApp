const WatchList = require("../model/watchlist")

//get all
exports.getAllWatchList = async(req,res) => {
    try {
        const watchlist = await WatchList.find();
        return res.status(200).json(watchlist)
    } catch (error) {
        return res.status(500).json(error)
    }
}
//get all by accountid
exports.getAllWatchListByAccountId = async(req,res)=>{
    try {
        const watchlist = await WatchList.find({accountid:req.params.accountid})
        return res.status(200).json(watchlist)
    } catch (error) {
        return res.status(500).json(error)
    }
}
//get watchlist by slug
exports.getWatchListBySlug = async(req,res)=>{
    try {
        const watchlist = await WatchList.findOne({slug:req.params.slug})
        return res.status(200).json(watchlist)
    } catch (error) {
        return res.status(500).json(error)
    }
}
//add watchlist 
exports.addWatchList = async(req,res)=>{
    try {
        const check = await WatchList.findOne({slug:req.body.slug,accountid:req.body.accountid})
        if(!check){
            const watchlist = await new WatchList(req.body).save()
            return res.status(200).json(watchlist)
        }
        else{
            return res.status(500).json("Account's slug is exist")
        }
    } catch (error) {
        return res.status(500).json(error)
    }
}
//update watchlist by slug
exports.updateWatchList = async(req,res)=>{
    try {
        const watchlist = await WatchList.findOneAndUpdate({slug:req.params.slug},req.body)
        if (watchlist)  return res.status(200).json("Updated successfully!");
        else return res.status(404).json("Not found"); 
    } catch (error) {
        return res.status(500).json(error)
    }
}
//delete watchlist by slug
exports.deleteWatchList = async(req,res)=>{
    try {
        const watchlist = await WatchList.findOneAndDelete({slug:req.params.slug,accountid:req.params.accountid})
        if (watchlist)  return res.status(200).json("Deleted successfully")
        else return res.status(404).json("Not found")
    } catch (error) {
        return res.status(500).json(error)
    }
}
