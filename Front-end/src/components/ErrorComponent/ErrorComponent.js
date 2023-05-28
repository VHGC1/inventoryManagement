import React from "react";
import "./ErrorComponent.css";

const ErrorComponent = ({message, error, setError}) => {
  return (
    <div className="modal" onClick={() => setError(!error)} >
      <div className="message-box" onClick={() => setError(!error)} >
        <button className="close-btn" >X</button>
        <p>{message}</p>
      </div>
    </div>
  );
};

export default ErrorComponent;
