if (process.env.NODE_ENV !== "production") require("dotenv").load();
import mongodb from "mongodb";

class MongoDB {
  constructor() {
    this.connectionString = process.env.MONGO_CONNECTION;
    this.connection = null;
    this.clients = 0;
  }

  connect() {
    return mongodb.MongoClient.connect(this.connectionString)
    .then((conn) => {
      if (process.env.LOGS) console.log("MongoDB: conexão bem sucedida.");
      this.clients += 1;
      this.connection = conn;
      return conn.db(process.env.MONGO_DATABASE);
    })
    .catch((err) => {
      if (process.env.LOGS) console.log("MongoDB: falha de conexão.\n");
      throw(err);
    });
  }

  disconnect() {
    if (this.clients) {
      if (process.env.LOGS) console.log("MongoDB: encerrando conexão.");
      if (this.clients > 1) {
        this.clients -= 1;
        return true;
      }
      this.connection.close();
    }
    if (process.env.LOGS) console.log("MongoDB: conexão encerrada.");
    return true;
  }

  insert(collection, data) {
    if (collection && data) {
      return this.connect()
      .then((db) => {
        var coll = db.collection(collection);
        return coll.insert(data);
      })
      .catch((err) => {
        throw(err);
      })
      .then((docs) => {
        if (process.env.LOGS) console.log("MongoDB: inserção finalizada.");
        this.disconnect();
        return true;
      });
    }
  }

  insertMany(collection, data) {
    if (collection && data) {
      return this.connect()
      .then((db) => {
        var coll = db.collection(collection);
        return coll.insertMany(data);
      })
      .catch((err) => {
        throw(err);
      })
      .then((docs) => {
        if (process.env.LOGS) console.log("MongoDB: inserção múltipla finalizada.");
        this.disconnect();
        return true;
      });
    }
  }

  find(collection, params = {}) {
    if (collection) {
      return this.connect()
      .then((db) => {
        console.log("MONGO FIND THEN");
        var coll = db.collection(collection);
        return coll.find(params).toArray();
      })
      .catch((err) => {
        throw(err);
      })
      .then((docs) => {
        if (process.env.LOGS) console.log("MongoDB: busca finalizada.");
        this.disconnect();
        return docs;
      });
    }
  }
}

export default MongoDB;
