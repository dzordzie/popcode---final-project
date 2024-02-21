import "./RegisterForm.css";
import { Link, useNavigate } from "react-router-dom";
import { useState, useRef } from "react";
import AuthApiService from "../../services/api/AuthApiService";

export default function RegisterForm() {
  const [user, setUser] = useState({
    name: "",
    email: "",
    password: "",
  });

  const [terms, setTerms] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [messageForUser, setMessageForUser] = useState("");
  const formRef = useRef(null);
  const navigate = useNavigate();

  function handleUser(input) {
    const { name, value } = input.target;

    setUser({
      ...user,
      [name]: value,
    });
  }

  function handleTerms(input) {
    setTerms(input.target.checked);
  }

  function resetStates() {
    setUser({
      name: "",
      email: "",
      password: "",
    });
    setTerms(false);
  }

  function isFormEmpty() {
    return user.name === "" || user.email === "" || user.password === "";
  }

  async function handleSubmit(event) {
    event.preventDefault();
    const isFormValid = formRef.current.checkValidity();
    if (isFormValid && !isFormEmpty() && terms) {
      await AuthApiService.register(user)
        .badRequest((error) => setMessageForUser(error.text))
        .json((json) => {
          if (json.error) {
            setMessageForUser(json.error);
          } else {
            setMessageForUser(json.successMsg);
            formRef.current.reset();
            resetStates();
            navigate("/login");
          }
        });
    } else if (isFormEmpty()) {
      setMessageForUser("Please fill the form!");
    } else if (!terms) {
      setMessageForUser("Please agree to Terms and Conditions!");
    }
  }

  return (
    <div className="register-form">
      <div>
        <h1>New User Registration</h1>
      </div>
      <form ref={formRef} onSubmit={handleSubmit}>
        <label className="label" htmlFor="name">
          Username
          <input
            className="input"
            id="name"
            type="text"
            name="name"
            pattern="[A-Za-z0-9]{3,16}"
            onChange={handleUser}
            value={user.name}
            placeholder="Username"
          />
          <span className="validation-message">
            Username must be 3 - 16 characters long
          </span>
        </label>
        <label className="label" htmlFor="email">
          Email
          <input
            className="input"
            id="email"
            type="email"
            name="email"
            pattern="[a-z0-9][a-z0-9\.\-]+@[a-z0-9]+\.[a-z]+(\.[a-z]+)?"
            onChange={handleUser}
            value={user.email}
            placeholder="e-mail"
          />
          <span className="validation-message">
            Please enter a valid email address.
          </span>
        </label>
        <label className="label" htmlFor="password">
          Password
          <input
            className="input"
            id="password"
            type={showPassword ? "text" : "password"}
            name="password"
            pattern="(?=.*[A-Z].*[A-Z])(?=.*\d.*\d)(?=.*[!@#$%^&*-\.].*[!@#$%^&*-\.])(?!.*\s).{8,16}"
            onChange={handleUser}
            value={user.password}
            placeholder="Password"
          />
          <span className="validation-message">
            Password must contain at least:
            <br />
            - two numbers
            <br />
            - two uppercase letters
            <br />
            - two special characters
            <br />
            and must be 8 -16 characters long
          </span>
        </label>
        <label className="label" htmlFor="showpswd">
          Show Password
          <input
            className="showpswd"
            id="showpswd"
            type="checkbox"
            name="showpswd"
            onChange={() => setShowPassword((show) => !show)}
            value={showPassword}
          />
        </label>
        <label className="label" htmlFor="terms">
          Please accept the terms of use.*
          <input
            className="checkbox"
            id="terms"
            type="checkbox"
            name="terms"
            onChange={handleTerms}
            value={terms}
          />
        </label>
        <span className="submit-message">{messageForUser}</span>
        <button className="submit-button" type="submit">
          Registration
        </button>

        <Link to="/login" className="link">
          Already have an account? Click here.
        </Link>
      </form>
    </div>
  );
}
