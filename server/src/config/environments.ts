import { config as dotEnvConfig } from 'dotenv';

// .env config
dotEnvConfig();

/**
 * @description get environment or return default value if not exists
 * @author David Vilaça
 * @date 2019-03-23
 * @param {string} name
 * @param {string} [defaultVal='']
 * @returns
 */
const getEnv = (name: string, defaultVal: string = '') =>
  process.env[name] || defaultVal;

/**
 * Environments variables
 */
export const ENVIRONMENTS = {
  DB: {
    DB_URI: getEnv('DB_URI')
  }
};
