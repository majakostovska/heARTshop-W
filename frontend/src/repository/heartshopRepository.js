import axios from '../custom-axios/axios';

const heartShopService = {
    fetchArtists: () => {
        return axios.get("/artists");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },
    fetchPaintings: () => {
        return axios.get("/paintings");
    },
    deletePaintings: (id) => {
        return axios.delete(`/paintings/delete/${id}`);
    },
    addPainting: (name, price, quantity, category, artist) => {
        return axios.post("/paintings/add", {
            "name": name,
            "price": price,
            "quantity": quantity,
            "category": category,
            "artist": artist
        });
    },
    editPainting: (id, name, price, quantity, category, artist) => {
        return axios.put(`/paintings/edit/${id}`, {
            "name": name,
            "price": price,
            "quantity": quantity,
            "category": category,
            "artist": artist
        });
    },
    getPainting: (id) => {
        return axios.get(`/paintings/${id}`);
    },
    login: (username, password) => {
        return axios.post("/login", {
            "username": username,
            "password": password
        });
    },
}

export default heartShopService;
