const router = require("express").Router()
const WatchList = require("../controller/watchlistcontroller")

//add 
router.post("/",WatchList.addWatchList)

//get all
router.get("/all",WatchList.getAllWatchList)

//get all by accountid
router.get("/all/:accountid",WatchList.getAllWatchListByAccountId)

//get by slug
router.get("/:slug",WatchList.getWatchListBySlug)

//update by slug
router.put("/:slug",WatchList.updateWatchList)

//delete by slug
router.delete("/:slug/:accountid",WatchList.deleteWatchList)


module.exports = router

