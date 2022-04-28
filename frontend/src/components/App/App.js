import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Artists from "../Artists/artists";
import Paintings from '../Paintings/PaintingsList/paintings'
import PaintingAdd from "../Paintings/PaintingAdd/paintingAdd";
import PaintingEdit from "../Paintings/PaintingEdit/paintingEdit";
import heartShopService from "../../repository/heartshopRepository";
import Categories from '../Categories/categories';
import Header from '../Header/header';
import Login from "../Login/login";


class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            artists: [],
            paintings: [],
            categories: [],
            selectedPainting: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route path={"/artists"} exact render={() =>
                            <Artists artists={this.state.artists}/>}/>
                        <Route path={"/categories"} exact render={() =>
                            <Categories categories={this.state.categories}/>}/>
                        <Route path={"/paintings/add"} exact render={() =>
                            <PaintingAdd categories={this.state.categories}
                                        artists={this.state.artists}
                                        onAddPainting={this.addPainting}/>}/>
                        <Route path={"/paintings/edit/:id"} exact render={() =>
                            <PaintingEdit categories={this.state.categories}
                                          artists={this.state.artists}
                                         onEditPainting={this.editPainting}
                                         painting={this.state.selectedPainting}/>}/>
                        <Route path={"/paintings"} exact render={() =>
                            <Paintings paintings={this.state.paintings}
                                      onDelete={this.deletePainting}
                                      onEdit={this.getPainting}/>}/>
                        <Route path={"/login"} exact render={() => <Login onLogin={this.fetchData}/>}/>
                        <Redirect to={"/paintings"}/>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadArtists();
        this.loadCategories();
        this.loadPaintings();
    }

    loadArtists = () => {
        heartShopService.fetchArtists()
            .then((data) => {
                this.setState({
                    artists: data.data
                })
            });
    }

    loadPaintings = () => {
        heartShopService.fetchPaintings()
            .then((data) => {
                this.setState({
                    paintings: data.data
                })
            });
    }

    loadCategories = () => {
        heartShopService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    deletePainting = (id) => {
        heartShopService.deletePaintings(id)
            .then(() => {
                this.loadPaintings();
            });
    }

    addPainting = (name, price, quantity, category, artist) => {
        heartShopService.addPainting(name, price, quantity, category, artist)
            .then(() => {
                this.loadPaintings();
            });
    }

    getPainting = (id) => {
        heartShopService.getPainting(id)
            .then((data) => {
                this.setState({
                    selectedPainting: data.data
                })
            })
    }

    editPainting = (id, name, price, quantity, category, artist) => {
        heartShopService.editPainting(id, name, price, quantity, category, artist)
            .then(() => {
                this.loadPaintings();
            });
    }
}

export default App;


