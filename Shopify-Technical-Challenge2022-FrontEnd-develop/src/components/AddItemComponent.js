import React, {useState} from 'react';
import ItemsApi from "../api/ItemsApi";
import {useNavigate} from "react-router";
import {ToastContainer, toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

function AddItemComponent() {

    let navigate = useNavigate();
    const [itemName, setItemName] = useState("")
    const [itemQuantity, setItemQuantity] = useState("")
    const [itemPrice, setItemPrice] = useState("")


    const handItemNameChange = event => {
        if(event.target.value !== null || event.target.value === null) {
            setItemName(event.target.value);
        }
    }

    const handleItemQuantityChange = event => {
        if(event.target.value==="") {
            setItemQuantity("");
        } else {
            if(event.target.value < 0 ) {
                setItemQuantity("");
            } else {
                setItemQuantity(Math.trunc(event.target.value));
            }
        }
    }

    const handleItemPriceChange = event => {
        if(event.target.value < 0) {
            setItemPrice("");
        } else {
            setItemPrice(event.target.value);
        }
    }

    const saveItem = async event => {
        event.preventDefault();
        let newItem = {itemName: itemName, quantity: itemQuantity, price: itemPrice}
        if(itemName !=="" && itemQuantity!=="" && itemPrice !=="") {
            await ItemsApi.addNewItem(newItem);
            toast("Success, Item Added");
        } else {
            toast("ERROR: Do not submit empty boxes");
        }
    }


    return (
        <div>
            <div className="container margin-top">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-center"> Add Item </h3>
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label>Item Name:</label>
                                    <input name = "FirstName" placeholder="Item Name" className="form-control"
                                           value={itemName} onChange={handItemNameChange}/>
                                </div>
                                <div className="form-group">
                                    <label>Item Quantity:</label>
                                    <input  type="number" step="1" min="0" name = "Quantity" placeholder="Item Quantity" className="form-control"
                                           value={itemQuantity} onChange={handleItemQuantityChange}/>
                                </div>
                                <div className="form-group">
                                    <label>Item Price:</label>
                                    <input type= "number" min="0" name = "Price" placeholder="Item Price" className="form-control"
                                           value={itemPrice} onChange={handleItemPriceChange}/>
                                </div>
                                <button type="submit" className="btn btn-primary" onClick={saveItem}>Save Item</button>
                                <button type="submit" className="btn btn-danger cancel-button" onClick={()=>navigate("/items")}>Cancel</button>
                            </form>
                            <ToastContainer
                                position="bottom-center"
                                autoClose={5000}
                                hideProgressBar={false}
                                newestOnTop={false}
                                closeOnClick
                                rtl={false}
                                pauseOnFocusLoss
                                draggable
                                pauseOnHover
                            />
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );

}

export default AddItemComponent;