import { Link } from "react-router-dom";
import instagramLogo from "../../assets/images/social-media-logos/instagram.svg";
import facebookLogo from "../../assets/images/social-media-logos/facebook.svg";
import xLogo from "../../assets/images/social-media-logos/x-logo.svg";
import linkedinLogo from "../../assets/images/social-media-logos/linkedin.svg";
import "./Footer.css";

export default function Footer() {
  return (
    <div className="footer">
      <div className="footer-container">
        <p className="copyright">&#169; 2024 FoxRide</p>

        <div className="footer-links">
          <Link to="/terms">Terms</Link>
          <Link to="/contact">Contact</Link>
        </div>
      </div>

      <div className="social-media">
        <a
          href="https://www.instagram.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img src={instagramLogo} alt="Instagram" />
        </a>
        <a
          href="https://www.facebook.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img src={facebookLogo} alt="Facebook" />
        </a>
        <a
          href="https://www.twitter.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img src={xLogo} alt="Twitter" className="x-logo" />
        </a>
        <a
          href="https://www.linkedin.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img src={linkedinLogo} alt="LinkedIn" />
        </a>
      </div>
    </div>
  );
}
