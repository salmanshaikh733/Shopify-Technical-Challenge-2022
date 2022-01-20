import React, {useState,useEffect} from 'react';
import ItemsApi from "../api/ItemsApi";
import {useNavigate} from "react-router";
import {ToastContainer, toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import { useParams } from 'react-router-dom';



function UpdateItemComponent() {

    const params = useParams();
    let navigate = useNavigate();
    const [itemName, setItemName] = useState("")
    const [itemQuantity, setItemQuantity] = useState(0)
    const [itemPrice, setItemPrice] = useState(0)
    const [itemId, setItemId] = useState(params.id)


    //when component mounts
    useEffect(() => {
        ItemsApi.getItemById(itemId).then((res) =>{
            let item = res.data;
            setItemName(item.itemName)
            setItemQuantity(item.quantity)
            setItemPrice(item.price)
        })
    },[])

    const handleItemNameChange = event => {
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
            setItemPrice(0)
        }else {
            setItemPrice(event.target.value);
        }
    }

    const updateItem = async event => {
        event.preventDefault();
        let newItem = {itemName: itemName, quantity: itemQuantity, price: itemPrice}
        if(itemName !=="" && itemQuantity!=="" && itemPrice !=="") {
            await ItemsApi.updateItem(newItem,itemId);
            toast("Success, Item Updated");
        } else {
            toast("ERROR: Do not submit empty boxes");
        }
    }


    return (
        <div>
            <div className="container margin-top">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-center"> Update Item </h3>
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label>Item Name:</label>
                                    <input name = "itemName" placeholder="Item Name" className="form-control"
                                           value={itemName} onChange={handleItemNameChange}/>
                                </div>
                                <div className="form-group">
                                    <label>Item Quantity:</label>
                                    <input type="number" min="0" name = "quantity" placeholder="Item Quantity" className="form-control"
                                           value={itemQuantity} onChange={handleItemQuantityChange}/>
                                </div>
                                <div className="form-group">
                                    <label>Item Price</label>
                                    <input type="number" min="0" name = "price" placeholder="Item Price" className="form-control"
                                           value={itemPrice} onChange={handleItemPriceChange}/>
                                </div>
                                <button type="submit" className="btn btn-primary" onClick={updateItem}>Update Item</button>
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

UpdateItemComponent.propTypes = {

};

export default UpdateItemComponent;