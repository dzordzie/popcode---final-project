import { createContext, useContext, useMemo } from "react";
import { useNavigate } from "react-router-dom";
import useLocalStorage from "./useLocalStorage";

const AuthContext = createContext();

function AuthProvider({ children }) {
  const [token, setToken] = useLocalStorage("token", null);
  const [role, setRole] = useLocalStorage("role", null);
  const navigate = useNavigate();

  const value = useMemo(
    () => ({
      token,
      login: async (_token) => {
        setToken(_token);
        setRole(JSON.parse(atob(_token.split(".")[1])).role);
        navigate("/profile");
      },
      logout: () => {
        setToken(null);
        setRole(null);
        navigate("/", { replace: true });
      },
      role,
    }),
    [token, setToken, role, navigate, setRole],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export const useAuth = () => useContext(AuthContext);
export { AuthProvider };
