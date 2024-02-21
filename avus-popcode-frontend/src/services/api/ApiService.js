import wretch from "wretch";

export default class ApiService {
  resource;

  constructor(resource = null) {
    this.resource = resource ?? "";
  }

  request(endpoint, headers = {}) {
    return wretch(
      `${process.env.REACT_APP_BACKEND}${this.resource}${endpoint}`,
    ).headers(headers);
  }

  get(endpoint, headers = {}) {
    return this.request(endpoint, headers).get();
  }

  post(endpoint, body = null, headers = {}) {
    return this.request(endpoint, headers).post(body);
  }

  put(endpoint, body = null, headers = {}) {
    return this.request(endpoint, headers).put(body);
  }

  patch(endpoint, body = null, headers = {}) {
    return this.request(endpoint, headers).patch(body);
  }

  delete(endpoint, headers = {}) {
    return this.request(endpoint, headers).delete();
  }
}

export function getToken() {
  return localStorage.getItem("token").replace(/^"(.*)"$/, "$1");
}
