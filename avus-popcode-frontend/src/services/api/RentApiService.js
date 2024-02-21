import ApiService, { getToken } from "./ApiService";

class RentApiService {
  constructor() {
    this.apiService = new ApiService();
  }

  getAll() {
    return this.apiService.get("/rents", {
      Authorization: `Bearer ${getToken()}`,
      "Content-Type": "application/json",
    });
  }

  updateStatus(ids, status) {
    return this.apiService.put(
      "/rents",
      {
        ids,
        status,
      },
      {
        Authorization: `Bearer ${getToken()}`,
        "Content-Type": "application/json",
      },
    );
  }

  create(vehicleId) {
    return this.apiService.post(
      "/rents",
      {
        id: vehicleId,
      },
      {
        Authorization: `Bearer ${getToken()}`,
        "Content-Type": "application/json",
      },
    );
  }
}

export default new RentApiService();
