const mongoose = require("mongoose");

const watchlistSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true
  },
  slug: {
    type: String,
    required: true
  },
  thumbUrl: {
    type: String,
    required: true
  },
  accountid: {
    type: String,
    required: true
  },
});

let WatchList = mongoose.model("WatchList", watchlistSchema);

module.exports = WatchList;
