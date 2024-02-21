import { useEffect, useState } from "react";
import CartComponent from "../../components/CartComponent/CartComponent";
import { useRent } from "../../hooks/useRent";

export default function Cart() {
  const [refresh, setRefresh] = useState(false);
  const { fetchRents } = useRent();

  useEffect(() => {
    fetchRents().then(() => setRefresh(false));
  }, [refresh, fetchRents]);

  return <CartComponent setRefresh={setRefresh} />;
}
