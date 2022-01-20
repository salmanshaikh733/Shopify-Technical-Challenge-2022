import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import ItemsApi from "../api/ItemsApi";
import {useNavigate} from "react-router";


function ViewItemComponent() {
    let navigate = useNavigate();

    const params = useParams();
    const [itemId, setItemId] = useState(params.id);
    let [item, setItem] = useState();
    const [itemName, setItemName] = useState("")
    const [itemQuantity, setItemQuantity] = useState("")
    const [itemPrice, setItemPrice] = useState("")


    //component did mount
    useEffect(() => {
        ItemsApi.getItemById(itemId).then((res) => {
            item = res.data;
            setItemName(item.itemName)
            setItemQuantity(item.quantity)
            setItemPrice(item.price)
        })
    }, [])

    const goBack = () => {
        navigate("/items");
    }

    const updateItem = event => {
        navigate(`/update-item/${event}`);
    }

    return (
        <div>
            <div className="card col-md-6 offset-md-3 margin-top">
                <h3 className="text-center"> View Item Details </h3>
                <div className="card-body">
                    <div className="row">
                        <label> <b>Item Name:</b> {itemName}</label>
                    </div>
                    &nbsp;
                    <div className="row">
                        <label> <b>Item Quantity: </b> {itemQuantity}</label>
                    </div>
                    &nbsp;
                    <div className="row">
                        <label><b>Item Price:</b> {itemPrice}</label>
                    </div>
                </div>
                <div >
                    <button type="submit" className="btn btn-primary go-back-button" onClick={()=>updateItem(itemId)}>Update</button>
                    <button type="submit" className="btn btn-danger go-back-button" onClick={goBack}>Go Back</button>
                </div>
            </div>
        </div>
    );
}

export default ViewItemComponent;