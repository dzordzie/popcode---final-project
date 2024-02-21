import { useEffect } from "react";
import { useNews } from "../../hooks/useNews";
import News from "../../components/News/News";

export default function NewsPage() {
  const { news, fetchNews } = useNews();

  useEffect(() => {
    fetchNews();
  }, []);

  return (
    <div>
      <News news={news} />
    </div>
  );
}
