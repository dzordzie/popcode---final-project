import { useParams, Link } from "react-router-dom";
import { useNews } from "../../hooks/useNews";
import "./SingleNews.css";

export default function SingleNews() {
  const { news } = useNews();
  const { id } = useParams();

  const oneSingleNews = news.find((oneNews) => oneNews.id === parseInt(id, 10));

  return (
    <div>
      <div className="single-news-container">
        <h2 className="single-news-title">{oneSingleNews.title}</h2>
        <p className="single-news-text">{oneSingleNews.text}</p>
        <Link to="/news" className="single-news-link">
          Back to news
        </Link>
      </div>
    </div>
  );
}
