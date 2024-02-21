import { Link } from "react-router-dom";
import "./News.css";

export default function News({ news }) {
  return (
    <div className="container">
      {news.map((element, index) => (
        <div key={element.id} className="news" id={`item-${index}`}>
          <h2 className="news-title">{element.title}</h2>
          <p className="news-summary">{element.summary}</p>
          <Link to={`/news/${element.id}`} className="news-link">
            read more...
          </Link>
        </div>
      ))}
    </div>
  );
}
