import "./LoginForm.css";
import { Link } from "react-router-dom";
import { useState, useRef } from "react";
import AuthApiService from "../../services/api/AuthApiService";
import { useAuth } from "../../hooks/useAuth";

export default function LoginForm() {
  const { login } = useAuth();
  const [user, setUser] = useState({
    email: "",
    password: "",
  });
  const [showPassword, setShowPassword] = useState(false);
  const [messageForUser, setMessageForUser] = useState("");
  const formRef = useRef(null);

  function handleUser(input) {
    const { name, value } = input.target;

    setUser({
      ...user,
      [name]: value,
    });
  }

  function resetStates() {
    setUser({
      email: "",
      password: "",
    });
  }

  async function handleSubmit(event) {
    event.preventDefault();
    await AuthApiService.login(user)
      .notFound((error) => setMessageForUser(error.text))
      .json((json) => {
        formRef.current.reset();
        resetStates();
        login(json.token);
      });
  }

  return (
    <div className="login-container">
      <h1>Login</h1>
      <form ref={formRef} onSubmit={handleSubmit}>
        <div className="input-box">
          <input
            id="email"
            type="email"
            required
            name="email"
            onChange={handleUser}
            value={user.email}
            placeholder="Email"
          />
        </div>
        <div className="input-box">
          <input
            type={showPassword ? "text" : "password"}
            placeholder="Password"
            id="password"
            required
            name="password"
            onChange={handleUser}
            value={user.password}
          />
        </div>
        <label className="label" htmlFor="showpswd">
          Show Password
          <input
            className="showpswd"
            id="showpswd"
            type="checkbox"
            name="showpswd"
            onChange={() => setShowPassword((show) => !show)}
            checked={showPassword}
          />
        </label>
        <span className="submit-message">{messageForUser}</span>
        <button type="submit">Login</button>

        <Link to="/register" className="register-link">
          {/* eslint-disable-next-line react/no-unescaped-entities */}
          Don't have an account? Click here to register.
        </Link>
      </form>
    </div>
  );
}
