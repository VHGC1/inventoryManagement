import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home/Home";
import AddProduct from "./components/AddProduct/AddProduct";

function App() {
  return (
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/add-product" element={<AddProduct/>}/>
      </Routes>
      </BrowserRouter>     
  );
}

export default App;
