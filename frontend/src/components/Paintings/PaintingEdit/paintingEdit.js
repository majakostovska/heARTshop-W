import React from 'react';
import {useHistory} from 'react-router-dom';

const PaintingEdit = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        price: 0,
        quantity: 0,
        category: 0,
        artist: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.painting.name;
        const price = formData.price !== 0 ? formData.price : props.painting.price;
        const quantity = formData.quantity !== 0 ? formData.quantity : props.painting.quantity;
        const category = formData.category !== 0 ? formData.category : props.painting.category.id;
        const artist = formData.artist !== 0 ? formData.artist : props.painting.artist.id;

        props.onEditPainting(props.painting.id, name, price, quantity, category, artist);
        history.push("/paintings");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Painting name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               placeholder={props.painting.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Price</label>
                        <input type="text"
                               className="form-control"
                               id="price"
                               name="price"
                               placeholder={props.painting.price}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Quantity</label>
                        <input type="text"
                               className="form-control"
                               id="quantity"
                               name="quantity"
                               placeholder={props.painting.quantity}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) => {
                                if(props.painting.category !== undefined &&
                                    props.painting.category.id === term.id)
                                    return <option selected={props.painting.category.id} value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Artist</label>
                        <select name="artist" className="form-control" onChange={handleChange}>
                            {props.artists.map((term) => {
                                if(props.painting.artist !== undefined &&
                                    props.painting.artist.id === term.id)
                                    return <option selected={props.painting.artist.id} value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>
                            })}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default PaintingEdit;
