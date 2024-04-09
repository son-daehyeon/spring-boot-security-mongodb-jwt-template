import axios from 'axios'

const PROXY_METHOD = ['get', 'post', 'put', 'patch', 'delete']

// TODO: Replace authorization header to real token
const axiosInstance = axios.create({
  baseURL: 'http://127.0.0.1:8080',
  timeout: 4000,
  headers: {
    'Authorization': `Bearer ${Cookies.get('access_token')}`
  }
});

const axiosProxy = new Proxy(axiosInstance, {
  get: function (target, property, receiver) {
    const method = target[property];

    if (PROXY_METHOD.includes(property)) {
      return async function (...args) {
        const result = await method.apply(this, args);

        if (result.headers['App-Reissue-Token'] === 1) {
          const accessToken = result.headers['App-New-Access-Token'];
          const refreshToken = result.headers['App-New-Refresh-Token'];

          axiosInstance.defaults.headers.common['Authorization'] = accessToken;

          Cookies.set('access_token', accessToken);
          Cookies.set('refresh_token', refreshToken);
        }

        return result;
      };
    }

    return method;
  }
});

export default axiosProxy;
