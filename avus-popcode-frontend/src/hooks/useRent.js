import {
  createContext,
  useCallback,
  useContext,
  useMemo,
  useState,
} from "react";
import VehicleApiService from "../services/api/VehicleApiService";
import RentApiService from "../services/api/RentApiService";

const RentContext = createContext();

export function RentProvider({ children }) {
  const [vehicles, setVehicles] = useState([]);
  const [rents, setRents] = useState([]);
  const [rentedVehicles, setRentedVehicles] = useState([]);

  const fetchVehicles = () =>
    VehicleApiService.get().json((json) => setVehicles(json));

  const fetchRents = useCallback(() => {
    RentApiService.getAll().json((json) => {
      setRents(json);
      setRentedVehicles(json.map((rent) => rent.vehicle));
    });
  }, [setRents, setRentedVehicles]);

  const rentVehicle = useCallback(
    async (id) => {
      await RentApiService.create(id)
        .json()
        .then(() => fetchRents());
    },
    [fetchRents],
  );

  const updateStatus = async (ids, status) => {
    await RentApiService.updateStatus(ids, status).json();
  };

  const value = useMemo(
    () => ({
      vehicles,
      rents,
      rentedVehicles,
      fetchVehicles,
      fetchRents,
      rentVehicle,
      updateStatus,
    }),
    [vehicles, rents, rentedVehicles, rentVehicle, fetchRents],
  );

  return <RentContext.Provider value={value}>{children}</RentContext.Provider>;
}

export const useRent = () => useContext(RentContext);
