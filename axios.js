import axios from 'axios'

const PROXY_METHOD = ['get', 'post', 'put', 'patch', 'delete']

// TODO: Replace authorization header to real token
const axiosInstance = axios.create({
  baseURL: 'http://127.0.0.1:8080',
  timeout: 4000,
  headers: {
    'Authorization': ""
  }
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
            if (e.response.headers['App-Reissue-Token'] === 1) {
              const accessToken = e.response.headers['App-New-Access-Token'];
              const refreshToken = e.response.headers['App-New-Refresh-Token'];

              axiosInstance.defaults.headers.common['Authorization'] = accessToken;

              // TODO: Set cookie

              return await method.apply(this, args);
            }
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
