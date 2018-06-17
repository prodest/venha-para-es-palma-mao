if (process.env.NODE_ENV !== "production") require("dotenv").load();
import mongodb from "mongodb";

class MongoDB {
  constructor() {
    this.connectionString = process.env.MONGO_CONNECTION;
    this.connection = null;
    this.db = null;
  }

  connect(callback = ()=>{}, disconnect = false) {
    mongodb.MongoClient.connect(this.connectionString, (err, conn) => {
      if (err) {
        if (process.env.LOGS) console.log("MongoDB: falha de conexão.\n", err);
        return;
      }

      this.connection = conn;
      this.db = conn.db(process.env.MONGO_DATABASE);
      if (process.env.LOGS) console.log("MongoDB: conexão bem sucedida.");
      callback(this.db);
      if (disconnect) this.disconnect();
      return this;
    });
  }

  disconnect() {
    if (this.connection) {
      if (process.env.LOGS) console.log("MongoDB: encerrando conexão.");
      this.connection.close();
    }
    if (process.env.LOGS) console.log("MongoDB: conexão encerrada.");
    return true;
  }

  insert(collection, data) {
    if (collection && data) {
      this.connect((db) => {
        if (process.env.LOGS) console.log("MongoBD: inserindo dados.");
        collection = db.collection(collection);
        collection.insert(data);
        if (process.env.LOGS) console.log("MongoDB: dados inseridos.");
        this.disconnect();
      });
    }
  }

  insertMany(collection, data) {
    if (collection && data) {
      this.connect((db) => {
        if (process.env.LOGS) console.log("MongoDB: inserindo array de dados.");
        collection = db.collection(collection);
        collection.insertMany(data);
        if (process.env.LOGS) console.log("MongoDB: array de dados inserido.");
      });
    }
  }

  find(collection, params = {}, callback = () => {}) {
    if (collection) {
      this.connect((db) => {
        collection = db.collection(collection);
        if (process.env.LOGS) console.log("MongoDB: buscando dados.");
        collection.find(params).toArray((err, docs) => {
          callback(docs);
          if (process.env.LOGS) console.log("MongoDB: busca finalizada.");
          this.disconnect();
        });
      });
    }
  }
}

export default new MongoDB;
