import { createContext, useContext, useMemo, useState } from "react";
import NewsApiService from "../services/api/NewsApiService";

const NewsContext = createContext();

export function NewsProvider({ children }) {
  const [news, setNews] = useState([]);

  const fetchNews = () => NewsApiService.get().json((json) => setNews(json));

  const value = useMemo(
    () => ({
      fetchNews,
      news,
    }),
    [news],
  );

  return <NewsContext.Provider value={value}>{children}</NewsContext.Provider>;
}

export const useNews = () => useContext(NewsContext);
