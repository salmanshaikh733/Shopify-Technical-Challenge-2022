import React from 'react';
import WareHouseLogo from "./assets/warehouselogo.ico"

function HeaderComponent() {

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-success ">
                <div>
                    <img src={WareHouseLogo} className="warehouse-logo margin-left"/>
                </div>
                <div>
                    <a href="/" className="navbar-brand margin-left"> Warehouse Inventory Management App</a>
                </div>
            </nav>
        </div>
    );

}

export default HeaderComponent;