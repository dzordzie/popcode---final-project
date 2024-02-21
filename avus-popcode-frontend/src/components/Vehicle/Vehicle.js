import React from "react";
import "./Vehicle.css";

export default function Vehicle({ vehicleProp, actionElement }) {
  return (
    <div className="vehicle">
      <img
        src={vehicleProp.url}
        alt={vehicleProp.altImg}
        className="vehicle-img"
      />
      <div className="vehicle-information">
        <p>{vehicleProp.name}</p>
        <p>{vehicleProp.description}</p>
        <p>Price: {vehicleProp.price}</p>
      </div>
      {React.cloneElement(actionElement, { vehicleProp })}
    </div>
  );
}
