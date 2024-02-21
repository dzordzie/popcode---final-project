import { useState } from "react";
import "./VehicleDropdown.css";

export default function VehicleDropdown({ vehicle }) {
  const [isShown, setShown] = useState(false);

  const toggleContent = () => {
    setShown(!isShown);
  };

  return (
    <div className="dropdown-container">
      <button type="button" className="dropdown" onClick={toggleContent}>
        {vehicle.name}
      </button>
      <div className={isShown ? "content active" : "content"}>
        <img src={vehicle.url} alt={vehicle.altImg} />
        <ul>
          <li>Type: {vehicle.type}</li>
          <li>Price: {vehicle.price}</li>
        </ul>
      </div>
    </div>
  );
}
