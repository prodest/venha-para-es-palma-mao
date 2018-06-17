if (process.env.NODE_ENV !== "production") require("dotenv").load();
import mongodb from "mongodb";

class MongoDB {
  constructor() {
    this.connectionString = process.env.MONGO_CONNECTION;
    this.connection = null;
    this.clients = 0;
  }

  connect(collection) {
    return mongodb.MongoClient.connect(this.connectionString)
    /* conectar */
    .then((conn) => {
      if (process.env.LOGS) console.log("MongoDB: conexão bem sucedida.");
      this.clients += 1;
      this.connection = conn;
      return conn.db(process.env.MONGO_DATABASE);
    })
    .catch((err) => {
      if (process.env.LOGS) console.log("MongoDB: falha de conexão.\n");
      throw err;
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
      try {
        this.connect(collection, (coll) => {
          if (process.env.LOGS) console.log("MongoBD: inserindo dados.");
          coll.insert(data);
          if (process.env.LOGS) console.log("MongoDB: dados inseridos.");
          return true;
        });
      } catch (err) {
        console.log("MongoDB:", err);
      }
    }
  }

  insertMany(collection, data) {
    if (collection && data) {
      try {
        this.connect(collection, (coll) => {
          if (process.env.LOGS) console.log("MongoDB: inserindo array de dados.");
          coll.insertMany(data);
          if (process.env.LOGS) console.log("MongoDB: array de dados inserido.");
          return true;
        });
      } catch (err) {
        console.log("MongoDB:", err);
      }
    }
  }

  find(collection, params = {}) {
    if (collection) {
      return this.connect(collection)
      .then((db) => {
        console.log("MONGO FIND THEN");
        var coll = db.collection(collection);
        return coll.find(params).toArray();
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
