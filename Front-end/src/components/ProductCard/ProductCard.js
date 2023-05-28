import React from "react";
import "./ProductCard.css";

const ProductCard = ({ id, sku, name, price }) => {
  return (
    <div className="product_card" id={id}>
      <div className="checkbox_delete">
        <input
          className="delete-checkbox"
          type="checkbox"
          name=""
          id={id}
        />
      </div>
      <div className="product_info">
        <p>{sku}</p>
        <p>{name}</p>
        <p>{price} $</p>
      </div>
    </div>
  );
};

export default ProductCard;
