import { useCallback, useEffect, useMemo, useState } from "react";
import Vehicle from "../Vehicle/Vehicle";
import "./VehicleList.css";

export default function VehicleList({ vehiclesProp, actionElement }) {
  const [vehicles, setVehicles] = useState([]);

  const vehicleTypeMap = useMemo(
    () =>
      new Map([
        [1, "BIKE"],
        [2, "SCOOTER"],
        [3, "MOTORBIKE"],
        [4, "CAR"],
      ]),
    [],
  );

  const filterVehicles = useCallback(
    (vehicleType) => {
      setVehicles(
        vehicleType === 0
          ? vehiclesProp
          : vehiclesProp.filter(
              (vehicleProp) =>
                vehicleTypeMap.get(vehicleType) === vehicleProp.type,
            ),
      );
    },
    [vehiclesProp, vehicleTypeMap, setVehicles],
  );

  useEffect(() => {
    filterVehicles(0);
  }, [vehiclesProp, filterVehicles]);

  return (
    <div className="vehicle-list">
      <div className="filter-buttons">
        <button
          type="button"
          className="filter-button"
          onClick={() => filterVehicles(0)}
        >
          All
        </button>
        <button
          type="button"
          className="filter-button"
          onClick={() => filterVehicles(1)}
        >
          Bike
        </button>
        <button
          type="button"
          className="filter-button"
          onClick={() => filterVehicles(2)}
        >
          Scooter
        </button>
        <button
          type="button"
          className="filter-button"
          onClick={() => filterVehicles(3)}
        >
          Motorbike
        </button>
        <button
          type="button"
          className="filter-button"
          onClick={() => filterVehicles(4)}
        >
          Car
        </button>
      </div>
      <div className="vehicle-map">
        {vehicles.map((vehicle) => (
          <Vehicle
            key={vehicle.id}
            vehicleProp={vehicle}
            actionElement={actionElement}
          />
        ))}
      </div>
    </div>
  );
}
