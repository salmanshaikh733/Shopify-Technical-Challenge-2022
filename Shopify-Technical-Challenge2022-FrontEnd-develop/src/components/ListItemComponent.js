import React, {useState, useEffect, useRef} from 'react';
import ItemsApi from "../api/ItemsApi";
import {useNavigate} from "react-router";
import { CSVLink } from 'react-csv'


function ListItemComponent() {
    let navigate = useNavigate();
    const [dirty,setDirty] = useState(0);
    const [items, setItems] = useState([]);
    const [csv, setCsv] = useState("");
    const csvLink = useRef()

    //on initial mount
    useEffect(() => {
        ItemsApi.getItems().then((res) => {
            setItems(res.data)
            items.sort()
            setDirty(dirty+1)
        })
    }, [])

    useEffect(() => {
        ItemsApi.getItems().then((res) => {
            setItems(res.data)
        })
        ItemsApi.getCSV().then((res)=> setCsv(res.data));
        setCsv("");
    }, [dirty])

    const deleteItem = async event => {
        await ItemsApi.deleteItem(event).then((res)=> {
            setDirty(dirty+1)
        })
    }

    const viewItem = event => {
        navigate(`/view-item/${event}`);
    }

    const updateItem = async event => {
        navigate(`/update-item/${event}`);
    }

    const downloadCSV = async () => {
        csvLink.current.link.click()
    }

    const changeQuantity = (itemId, increment) => {
        ItemsApi.changeQuantity(itemId,increment).then((res) => {
            setDirty(dirty+1)
        })
    }

    return (
        <div>
            <h2 className="text-center offcanvas-title">Inventory List</h2>
            <button className="btn btn-primary button" onClick={() => navigate("/add-item")}> Add Item </button>
            <button className="btn btn-secondary button cancel-button" onClick={() => downloadCSV()}> Export to CSV</button>
            {csv ? <CSVLink
                data={csv}
                filename='inventoryData.csv'
                className='hidden'
                ref={csvLink}
                target='_blank'
            /> : null}
            <div className="row">
            </div>
            <div className="row table-wrapper-scroll-y my-custom-scrollbar">
                <table className="table table-striped table-bordered table-hover table-light " >
                    <thead className="sticky-top">
                    <tr>
                        <th> Item Name</th>
                        <th> Item Quantity</th>
                        <th> Item Price</th>
                        <th> Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        items.map(
                            item => <tr key={item.id}>
                                <td> {item.itemName}</td>
                                <td> {item.quantity === 0 ? "Out of Stock" : item.quantity}</td>
                                <td>$ {item.price.toFixed(2)}</td>
                                <td>
                                    <button className="btn btn-info"
                                            onClick={() => updateItem(item.id)}>Update
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button className="btn btn-danger"
                                            onClick={() => deleteItem(item.id)}>Delete
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button className="btn btn-outline-dark"
                                            onClick={() => viewItem(item.id)}>View
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button className="btn btn-outline-primary"
                                            onClick={() => changeQuantity(item.id,true)}>Increment
                                    </button>
                                    &nbsp;&nbsp;&nbsp;
                                    <button disabled={item.quantity <=0} className="btn btn-outline-danger"
                                            onClick={() => changeQuantity(item.id,false)}>Decrement
                                    </button>
                                </td>
                            </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>

        </div>
    );
}

export default ListItemComponent;


