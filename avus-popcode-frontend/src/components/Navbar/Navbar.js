import "./Navbar.css";
import { Link } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";

export default function Navbar() {
  const { token, logout } = useAuth();
  const { role } = useAuth();

  if (!token) {
    return (
      <div className="navbar">
        <Link to="/news" className="link">
          News
        </Link>
        <Link to="/login" className="link">
          Login
        </Link>
        <Link to="/register" className="link">
          Register
        </Link>
      </div>
    );
  }

  if (role === "USER") {
    return (
      <div className="navbar">
        <Link to="/news" className="link">
          News
        </Link>
        <Link to="/vehicles" className="link">
          Vehicles
        </Link>
        <Link to="/rents" className="link">
          Rents
        </Link>
        <Link to="/cart" className="link">
          Cart
        </Link>
        <Link to="/profile" className="link">
          Profile
        </Link>
        <button type="button" onClick={logout}>
          Logout
        </button>
      </div>
    );
  }

  if (role === "ADMIN") {
    return (
      <div className="navbar">
        <Link to="/admin/news" className="link">
          News
        </Link>
        <Link to="/admin/vehicles" className="link">
          Vehicles
        </Link>
        <Link to="/admin/profile" className="link">
          Profile
        </Link>
        <button type="button" onClick={logout}>
          Logout
        </button>
      </div>
    );
  }
}
