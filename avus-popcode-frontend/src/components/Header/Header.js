import { Link } from "react-router-dom";
import Navbar from "../Navbar/Navbar";
import logo from "../../assets/images/logo-placeholder.png";
import "./Header.css";

export default function Header() {
  return (
    <div className="header-container">
      <div className="header-logo">
        <Link to="/">
          <img src={logo} alt="logo" />
        </Link>
      </div>

      <Navbar />
    </div>
  );
}
