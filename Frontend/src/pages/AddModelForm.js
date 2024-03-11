import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AddModelForm = () => {
    const { carMake } = useParams();
    const [image, setImage] = useState(null);
    const [model, setModel] = useState('');
    const [engine, setEngine] = useState('');
    const [displacement, setDisplacement] = useState('');
    const [power, setPower] = useState('');
    const [torque, setTorque] = useState('');
    const [aspiration, setAspiration] = useState('');
    const [fuel, setFuel] = useState('');
    const [trim, setTrim] = useState('');
    const [interior, setInterior] = useState('');
    const [price, setPrice] = useState('');

    const navigate = useNavigate();

    const handleImageChange = (event) => {
        const selectedImage = event.target.files[0];
        setImage(selectedImage);
    };

    const uploadImage = async () => {
        const formData = new FormData();
        const renamedFileName = `${model}.png`;
        formData.append('file', image, renamedFileName);
        await axios.post('http://localhost:8080/upload2', formData);
    };

    const uploadPath = async () => {
        const imageData = {
            image_url: `./cars/${model}.png`,
        };
        const response = await axios.post('http://localhost:8080/image', imageData);
        console.log(response.data.imageid);
        return response.data.id;
    }

    const createEngine = async () => {
        const fl = fuelOptions.indexOf(fuel) + 1;
        const as = aspirationOptions.indexOf(aspiration) + 1;
        const engineData = {
            enginename: engine,
            displacement: displacement,
            horsepower: power,
            torque: torque,
            aspirationid: as,
            fuelid: fl,
        };
        const response = await axios.post('http://localhost:8080/engine', engineData);
        return response.data.engineid;
    };

    const createConfiguration = async () => {
        const configurationData = {
            trimlevel: trim,
            interiorcolor: interior,
        };
        const response = await axios.post('http://localhost:8080/configuration', configurationData);
        return response.data.configid;
    };

    const createModel = async (engineId, configId) => {
        const modelData = {
            modelname: model,
            engineid: engineId,
            configid: configId,
        };
        const response = await axios.post('http://localhost:8080/model', modelData);
        return response.data.modelid;
    };

    const createCar = async (modelId, imageUrl) => {
        const carData = {
            makeid: Number(carMake),
            modelid: modelId,
            price: price,
            imageid: imageUrl,
        };
        const response = await axios.post('http://localhost:8080/car', carData);
        return response.data.id;
    };

    const [aspirationOptions] = useState([
        'Naturally Aspirated',
        'Turbocharged',
        'Twin Turbocharged',
        'Supercharged',
        'Electric',
        'Quad Turbocharged',
    ]);

    const [fuelOptions] = useState(['Gasoline', 'Diesel', 'Electric']);


    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            await uploadImage();
            const imageUrl = await uploadPath();
            const engineId = await createEngine();
            const configId = await createConfiguration();
            const modelId = await createModel(engineId, configId);
            await createCar(modelId, imageUrl);

            await new Promise(resolve => setTimeout(resolve, 1000));

            // Redirect to the home page after successful submission
            navigate(`/car/${carMake}`);

        } catch (error) {
            console.error('Error adding model:', error);
        }
    };

    return (
        <div className="container">
            <div className="py-4">
                <h2>Add Model</h2>
                <form onSubmit={handleSubmit} encType="multipart/form-data">
                    <div className="mb-3">
                        <label htmlFor="image" className="form-label">Upload Image:</label>
                        <input
                            type="file"
                            id="image"
                            accept="image/*"
                            onChange={handleImageChange}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="model" className="form-label">Model:</label>
                        <input
                            type="text"
                            id="model"
                            value={model}
                            onChange={(e) => setModel(e.target.value)}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="engine" className="form-label">Engine:</label>
                        <input
                            type="text"
                            id="engine"
                            value={engine}
                            onChange={(e) => setEngine(e.target.value)}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="displacement" className="form-label">Displacement:</label>
                        <input
                            type="text"
                            id="displacement"
                            value={displacement}
                            onChange={(e) => {
                                const inputValue = e.target.value;
                                // Check if the input is a positive number
                                if (/^\d*\.?\d+$/.test(inputValue) || inputValue === '') {
                                    setDisplacement(inputValue);
                                }
                            }}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="power" className="form-label">Power:</label>
                        <input
                            type="text"
                            id="power"
                            value={power}
                            onChange={(e) => {
                                const inputValue = e.target.value;
                                if (/^\d+$/.test(inputValue) || inputValue === '') {
                                    setPower(inputValue);
                                }
                            }}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="torque" className="form-label">Torque:</label>
                        <input
                            type="text"
                            id="torque"
                            value={torque}
                            onChange={(e) => {
                                const inputValue = e.target.value;
                                if (/^\d+$/.test(inputValue) || inputValue === '') {
                                    setTorque(inputValue);
                                }
                            }}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="aspiration" className="form-label">
                            Aspiration:
                        </label>
                        <select
                            id="aspiration"
                            value={aspiration}
                            onChange={(e) => setAspiration(e.target.value)}
                            className="form-control"
                            required
                        >
                            {/* Include an empty option to represent an initial empty state */}
                            <option value="">Select Aspiration...</option>
                            {aspirationOptions.map((option, index) => (
                                <option key={index} value={option}>
                                    {option}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="fuel" className="form-label">
                            Fuel:
                        </label>
                        <select
                            id="fuel"
                            value={fuel}
                            onChange={(e) => setFuel(e.target.value)}
                            className="form-control"
                            required
                        >
                            <option value="">Select Fuel Type...</option>
                            {fuelOptions.map((option, index) => (
                                <option key={index} value={option}>
                                    {option}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="trim" className="form-label">Trim:</label>
                        <input
                            type="text"
                            id="trim"
                            value={trim}
                            onChange={(e) => setTrim(e.target.value)}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="interior" className="form-label">Interior color:</label>
                        <input
                            type="text"
                            id="interior"
                            value={interior}
                            onChange={(e) => setInterior(e.target.value)}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="price" className="form-label">Price:</label>
                        <input
                            type="text"
                            id="price"
                            value={price}
                            onChange={(e) => {
                                const inputValue = e.target.value;
                                if (/^\d+$/.test(inputValue) || inputValue === '') {
                                    setPrice(inputValue);
                                }
                            }}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        {/* Add similar input fields for other attributes */}
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );
};

export default AddModelForm;
