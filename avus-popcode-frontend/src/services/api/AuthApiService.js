import ApiService from "./ApiService";

class AuthApiService {
  apiService = new ApiService("/auth");

  register(user) {
    return this.apiService.post("/register", {
      name: user.name,
      email: user.email,
      password: user.password,
    });
  }

  login(user) {
    return this.apiService.post("/login", {
      email: user.email,
      password: user.password,
    });
  }
}

export default new AuthApiService();
