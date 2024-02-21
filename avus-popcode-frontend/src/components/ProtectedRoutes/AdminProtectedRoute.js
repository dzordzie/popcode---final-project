import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";

export default function AdminProtectedRoute() {
  const { token, role } = useAuth();

  if (!token && role !== "ADMIN") {
    return <Navigate to="/" />;
  }

  return <Outlet />;
}
