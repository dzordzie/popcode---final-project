import "./App.css";
import "./variables.css";
import { Route, Routes } from "react-router-dom";
import Landing from "./pages/Landing/Landing";
import Profile from "./pages/Profile/Profile";
import Rents from "./pages/Rents/Rents";
import VehiclePage from "./pages/VehiclePage/VehiclePage";
import NewsPage from "./pages/NewsPage/NewsPage";
import SingleNews from "./pages/SingleNews/SingleNews";
import Registration from "./pages/Registration/Registration";
import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";
import Login from "./pages/Login/Login";
import NotFound from "./pages/NotFound/NotFound";
import Cart from "./pages/Cart/Cart";
import ThankYouPage from "./pages/ThankYouPage/ThankYouPage";
import UserProtectedRoute from "./components/ProtectedRoutes/UserProtectedRoute";
import AdminProtectedRoute from "./components/ProtectedRoutes/AdminProtectedRoute";
import AdminVehicles from "./components/AdminVehicles/AdminVehicles";
import AdminNews from "./components/AdminNews/AdminNews";

export default function App() {
  return (
    <>
      <Header />
      <Routes>
        {/* public routes */}
        <Route path="*" element={<NotFound />} />
        <Route path="/" element={<Landing />} />
        <Route path="/news" element={<NewsPage />} />
        <Route path="/news/:id" element={<SingleNews />} />
        <Route path="/register" element={<Registration />} />
        <Route path="/login" element={<Login />} />
        <Route path="/thankyou" element={<ThankYouPage />} />

        {/* userProtected routes */}
        <Route element={<UserProtectedRoute />}>
          <Route path="/rents" element={<Rents />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/vehicles" element={<VehiclePage />} />
          <Route path="/cart" element={<Cart />} />
        </Route>

        {/* adminProtected routes */}
        <Route path="/admin" element={<AdminProtectedRoute />}>
          <Route path="vehicles" element={<AdminVehicles />} />
          <Route path="news" element={<AdminNews />} />
          <Route path="profile" element={<Profile />} />
        </Route>
      </Routes>
      <Footer />
    </>
  );
}
