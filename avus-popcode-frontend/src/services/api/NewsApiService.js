import wretch from "wretch";

class NewsApiService {
  // eslint-disable-next-line
  get() {
    return wretch("http://localhost:4000/news").get();
  }
}

export default new NewsApiService();
