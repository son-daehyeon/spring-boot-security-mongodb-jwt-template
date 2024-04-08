import axios from 'axios'

const PROXY_METHOD = ['get', 'post', 'put', 'patch', 'delete']

const axiosInstance = axios.create({
  baseURL: 'http://127.0.0.1:8080',
  timeout: 4000,
});

const axiosProxy = new Proxy(axiosInstance, {
  get: function (target, property, receiver) {
    const method = target[property];

    if (PROXY_METHOD.includes(property)) {
      return async function (...args) {
        try {
          return await method.apply(this, args);
        } catch (e) {
          if (axios.isAxiosError(e)) {
            // TODO: 토큰 재발급 로직
          } else {
            throw e;
          }
        }
      };
    }

    return method;
  }
});

export default axiosProxy;
