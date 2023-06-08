import React, { useEffect, useState } from "react";
import axios from "axios";
import "./Home.css";
import ProductCard from "../ProductCard/ProductCard";

const Home = () => {
  const [products, setProducts] = useState([]);
  const [response, setResponse] = useState('');

  const deleteAll = () => {
    const productsToDelete = document.getElementsByClassName("product_card");

    const selectedProducts = [];
    for (let i = 0; i < productsToDelete.length; i++) {
      if (productsToDelete[i].getElementsByTagName("input")[0].checked) {
        selectedProducts.push(productsToDelete[i].id);
      }
    }

    axios.delete(`http://localhost:8080/products/delete?toDelete=${selectedProducts.join("-")}`).then((response) => {
      setResponse(response);
    }).catch((e) => console.log(e.response.data.message));
  };

  useEffect(() => {
    axios.get("http://localhost:8080/products/").then((response) => {
      setProducts(response.data);
    }).catch((e) => console.log(e.response.data.message));
  }, [response]);

  return (
    <main>
      <header>
        <h1>Product List</h1>
        <div>
          <a className="button add_button" href="/add-product">
            ADD
          </a>
          <button
            className="button delete_button"
            value="MASS DELETE"
            onClick={() => deleteAll()}
          >
            MASS DELETE
          </button>
        </div>
      </header>
      <hr></hr>

      <div className="product_container">
        {products.map(({ id, sku, name, unitPrice, quantity }) => (
          <ProductCard
            key={id}
            id={id}
            sku={sku}
            name={name}
            price={unitPrice}
            quantity={quantity}
          />
        ))}
      </div>
      <hr></hr>
    </main>
  );
};

export default Home;
