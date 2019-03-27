import { Document, Model } from 'mongoose';

export abstract class BaseModelService<T extends Document> {
  /**
   * @description abstract model
   * @protected
   * @abstract
   * @type {Model<T>}
   * @memberof BaseModelService
   */
  protected abstract readonly model: Model<T>;

  /**
   * @description create a document of model
   * @author David Vilaça
   * @date 2019-03-23
   * @abstract
   * @param {*} data
   * @returns {Promise<T>}
   * @memberof BaseModelService
   */
  public abstract create(data: any): Promise<T>;

  /**
   * @description update one document
   * @author David Vilaça
   * @date 2019-03-23
   * @param {T} data
   * @returns {Promise<T>}
   * @memberof BaseModelService
   */
  public update(data: T): Promise<T> {
    return this.model.updateOne({ _id: data._id }, data).exec();
  }

  /**
   * @description save the document
   * create if not exists else update
   * @author David Vilaça
   * @date 2019-03-23
   * @param {*} data
   * @returns {Promise<T>}
   * @memberof BaseModelService
   */
  public save(data: any): Promise<T> {
    if (data._id) {
      return this.update(data);
    }
    return this.create(data);
  }

  /**
   * @description find a document
   * @author David Vilaça
   * @date 2019-03-23
   * @param {*} conditions
   * @param {*} [projection]
   * @param {*} [options]
   * @returns {Promise<T[]>}
   * @memberof BaseModelService
   */
  public find(conditions: any, projection?: any, options?: any): Promise<T[]> {
    return this.model.find(conditions, projection, options).exec();
  }

  /**
   * @description find one document
   * @author David Vilaça
   * @date 2019-03-23
   * @param {string} id
   * @returns {(Promise<T | null>)}
   * @memberof BaseModelService
   */
  public findOne(id: string): Promise<T | null> {
    return this.model.findOne({ _id: id }).exec();
  }

  /**
   * @description delete one document
   * @author David Vilaça
   * @date 2019-03-23
   * @param {string} id
   * @returns {Promise<boolean>}
   * @memberof BaseModelService
   */
  public async delete(id: string): Promise<boolean> {
    const result = await this.model.deleteOne({ _id: id }).exec();
    return !!result.ok;
  }
}
