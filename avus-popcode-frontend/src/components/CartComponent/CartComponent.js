import { useNavigate } from "react-router-dom";
import "./CartComponent.css";
import VehicleDropdown from "../VehicleDropdown/VehicleDropdown";
import CartMessage from "../CartMessage/CartMessage";
import { useRent } from "../../hooks/useRent";

export default function CartComponent({ setRefresh }) {
  const navigate = useNavigate();
  const { updateStatus, rentedVehicles, rents } = useRent();

  const checkout = () => {
    updateStatus(
      rents.map((rent) => rent.id),
      "RENTED",
    );
    setRefresh(true);
    navigate("/thankyou");
  };

  if (rentedVehicles.length === 0) {
    return <CartMessage message="Your cart is empty" />;
  }

  return (
    <div className="cart-container">
      <div className="rent-dropdowns">
        {rentedVehicles.map((rentedVehicle) => (
          <VehicleDropdown key={rentedVehicle.id} vehicle={rentedVehicle} />
        ))}
      </div>

      <button type="button" className="finish-btn" onClick={checkout}>
        Checkout
      </button>
    </div>
  );
}
