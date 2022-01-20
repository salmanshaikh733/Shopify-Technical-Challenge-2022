import React from "react";
import './App.css';
import ListItemComponent from "./components/ListItemComponent";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import {BrowserRouter as Router} from 'react-router-dom';
import {Routes, Route} from 'react-router';
import AddItemComponent from "./components/AddItemComponent";
import UpdateItemComponent from "./components/UpdateItemComponent";
import ViewItemComponent from "./components/ViewItemComponent";

function App() {
    return (
        <div>
            <Router>
                    <HeaderComponent/>
                    <div className="container">
                        <Routes>
                            <Route path="/" exact element={<ListItemComponent/>}/>
                            <Route path="/items" element={<ListItemComponent/>}/>
                            <Route path="/add-item" element={<AddItemComponent/>}/>
                            <Route path="/update-item/:id" element={<UpdateItemComponent/>}/>
                            <Route path="/view-item/:id" element={<ViewItemComponent/>}/>

                        </Routes>
                    </div>
                    <FooterComponent/>
            </Router>
        </div>
    );
}

export default App;
