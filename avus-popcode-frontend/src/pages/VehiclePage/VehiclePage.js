import React, { useEffect } from "react";
import VehicleList from "../../components/VehicleList/VehicleList";
import { useRent } from "../../hooks/useRent";
import "./VehiclePage.css";

function RentAction({ vehicleProp: vehicle }) {
  const { rentVehicle, rentedVehicles } = useRent();

  const isPending = rentedVehicles.some(
    (rentedVehicle) => rentedVehicle.id === vehicle.id,
  );

  return (
    <button
      type="button"
      disabled={isPending}
      className="rent-btn"
      onClick={() => rentVehicle(vehicle.id)}
    >
      {isPending ? "Pending" : "Rent"}
    </button>
  );
}

export default function VehiclePage() {
  const { vehicles, fetchVehicles, fetchRents } = useRent();

  useEffect(() => {
    fetchVehicles().then(() => fetchRents());
  }, []);

  return <VehicleList vehiclesProp={vehicles} actionElement={<RentAction />} />;
}
