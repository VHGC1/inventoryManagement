import React, { useEffect, useState } from "react";
import "./AddProduct.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import ErrorComponent from "../ErrorComponent/ErrorComponent.js";

const AddProduct = () => {
  const [sku, setSku] = useState("");
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [responseCode, setResponseCode] = useState("");
  const [error, setError] = useState(false);
  const [messageError, setMessageError] = useState("");

  const navigate = useNavigate();

  function handleSubmit(event) {
    event.preventDefault();

    axios
      .post("http://localhost:8080/products/", {
        sku: sku,
        name: name,
        unitPrice: price,
      })
      .then((response) => {
        setResponseCode(response.status);
      })
      .catch((e) => {
        setMessageError(e.response.data.message);
        setError(true);
      });
  }

  useEffect(() => {
    if (Number(responseCode) === 200) {
      navigate("/");
    }
  }, [responseCode]);

  return (
    <>
      {error && (
        <ErrorComponent
          message={messageError}
          error={error}
          setError={setError}
        />
      )}
      <main>
        <form onSubmit={handleSubmit} id="product_form">
          <header>
            <h1>Add Product</h1>
            <div>
              <button type="submit" className="button save_button">
                Save
              </button>
              <a href="/" className="button cancel_button">
                Cancel
              </a>
            </div>
          </header>
          <hr />
          <div className="form-container">
            <div className="form-control">
              <label for="sku">SKU</label>
              <input
                name="sku"
                id="sku"
                type="text"
                pattern="^[a-zA-Z0-9]*$"
                value={sku}
                onChange={(e) => setSku(e.target.value)}
                required
              />
              <label for="name">Name</label>
              <input
                name="name"
                id="name"
                type="text"
                pattern="^[a-zA-Z0-9]*$"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
              />
              <label for="price">Price ($)</label>
              <input
                name="price"
                id="price"
                type="text"
                pattern="^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                required
              />
            </div>
          </div>
        </form>
        <hr />
      </main>
    </>
  );
};

export default AddProduct;
