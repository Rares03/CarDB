import React, { useEffect, useState } from 'react';
import { useParams} from 'react-router-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default function Model() {
    const { carMake } = useParams();
    const [carDetails, setCarDetails] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');
    const [editMode, setEditMode] = useState(null);
    const [initialCars, setInitialCars] = useState(null);
    const [aspiration, setAspiration] = useState('');
    const [fuel, setFuel] = useState('');
    const [filterOption, setFilterOption] = useState(['model', 'power', 'price']); // Updated filter options


    const [aspirationOptions] = useState([
        'Naturally Aspirated',
        'Turbocharged',
        'Twin Turbocharged',
        'Supercharged',
        'Electric',
        'Quad Turbocharged',
    ]);

    const [fuelOptions] = useState(['Gasoline', 'Diesel', 'Electric']);

    useEffect(() => {
        // Define a function to fetch car details based on the carMake
        const fetchCarDetails = async () => {
            try {
                // Replace the URL with your actual backend endpoint
                const response = await axios.get(`http://localhost:8080/cars/${carMake}`);
                setCarDetails(response.data);
                setInitialCars(response.data);
            } catch (error) {
                console.error('Error fetching car details:', error);
            }
        };

        // Call the fetchCarDetails function
        fetchCarDetails();
    }, [carMake]);

    const handleEdit = (carId) => {
        setEditMode(carId);
        console.log(`Edit button clicked for car with ID: ${carId}`);
    };

    const handleSave = async (carId) => {
        // Assuming you have an endpoint to update car details
        try {
            const updatedCar = carDetails.find((car) => car.model.modelid === carId);

            updatedCar.model.engine.aspirationid = aspirationOptions.indexOf(aspiration) + 1;
            updatedCar.model.engine.fuelid = fuelOptions.indexOf(fuel) + 1;

            await axios.put(`http://localhost:8080/car/${carId}`, updatedCar);
            setEditMode(null);
            setAspiration('');
            setFuel('');
            window.location.reload(true);
        } catch (error) {
            console.error('Error updating car details:', error);
        }
    };

    const handleCancel = () => {
        setCarDetails(initialCars);
        setEditMode(null);
    };

    const handleInputChange = (modelId, fieldName, value) => {

        // Find the car with the given modelId
        const updatedCars = carDetails.map((car) => {
            if (car.model.modelid === modelId) {
                // Update the specific field based on fieldName
                return {
                    ...car,
                    model: {
                        ...car.model,
                        [fieldName]: value,
                    },
                };
            }
            if (car.model.engine.enginename === modelId) {
                // Update the specific field based on fieldName
                return {
                    ...car,
                    model: {
                        ...car.model,
                        engine: {
                            ...car.model.engine,
                            [fieldName]: value,
                        },
                    },
                };
            }
            if (car.model.engine.displacement === modelId) {
                // Update the specific field based on fieldName
                return {
                    ...car,
                    model: {
                        ...car.model,
                        engine: {
                            ...car.model.engine,
                            [fieldName]: isPositiveNumber(value) ? value : car.model.engine[fieldName],
                        },
                    },
                };
            }
            if (car.model.engine.horsepower === modelId) {
                // Update the specific field based on fieldName
                return {
                    ...car,
                    model: {
                        ...car.model,
                        engine: {
                            ...car.model.engine,
                            [fieldName]: isPositiveNumber(value) ? value : car.model.engine[fieldName],
                        },
                    },
                };
            }
            if (car.model.engine.torque === modelId) {
                // Update the specific field based on fieldName
                return {
                    ...car,
                    model: {
                        ...car.model,
                        engine: {
                            ...car.model.engine,
                            [fieldName]: isPositiveNumber(value) ? value : car.model.engine[fieldName],
                        },
                    },
                };
            } if (car.model.configuration.trimlevel === modelId) {
                // Update the specific field based on fieldName
                return {
                    ...car,
                    model: {
                        ...car.model,
                        configuration: {
                            ...car.model.configuration,
                            [fieldName]: value,
                        },
                    },
                };
            }
            if (car.model.configuration.interiorcolor === modelId) {
                // Update the specific field based on fieldName
                return {
                    ...car,
                    model: {
                        ...car.model,
                        configuration: {
                            ...car.model.configuration,
                            [fieldName]: value,
                        },
                    },
                };
            }
            if (car.price === modelId) {
                // Update the specific field based on fieldName
                return {
                    ...car,
                    ...car.price,
                    [fieldName]: isPositiveNumber(value) ? value : car[fieldName],
                };
            }
            return car;
        });

        // Update the state with the modified cars array
        setCarDetails(updatedCars);
    };

    const isPositiveNumber = (value) => {
        const numericValue = Number(value);
        return !isNaN(numericValue) && numericValue > 0;
    };

    const handleDelete = async (modelId, carId) => {
        try {

            const response2 = await axios.get(`http://localhost:8080/car/${carId}`);
            const carData2 = response2.data;
            console.log(carData2.id);
            

            // Delete car
            await axios.delete(`http://localhost:8080/dcar/${carData2.id}`);

            // Delete image
            await axios.delete(`http://localhost:8080/dimage2/${carData2.model.modelname}.png`);
            await axios.delete(`http://localhost:8080/dimages/${carData2.imageid}`);

            modelId = modelId - 1;
            // Fetch the car data to get associated model, engine, configuration, and image information
            const response = await axios.get(`http://localhost:8080/model/${modelId}`);
            const carData = response.data;

            // Delete model
            await axios.delete(`http://localhost:8080/dmodel/${carData.modelid}`);

            // Delete engine
            await axios.delete(`http://localhost:8080/dengine/${carData.engineid}`);

            // Delete configuration
            await axios.delete(`http://localhost:8080/dconfiguration/${carData.configid}`);


            console.log(`Car with ID ${carId} and associated data deleted successfully.`);
            window.location.reload(true);
            

            // Optionally, you can navigate to another page or update the UI
        } catch (error) {
            console.error('Error deleting car:', error);
        }
    };


    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const handleFilterChange = (event) => {
        setFilterOption(event.target.value);
    };
    
    const compareWithOperator = (value, searchTerm) => {
        const operatorRegex = /([<>]=?)(\d+)/;
        const match = searchTerm.match(operatorRegex);
    
        if (match) {
            const operator = match[1];
            const targetValue = parseInt(match[2]);
    
            if (!isNaN(targetValue)) {
                switch (operator) {
                    case '<':
                        return value < targetValue;
                    case '>':
                        return value > targetValue;
                    case '<=':
                        return value <= targetValue;
                    case '>=':
                        return value >= targetValue;
                    default:
                        return value === targetValue;
                }
            }
        }
    
        // If no valid operator or targetValue, perform equality check
        return value === parseInt(searchTerm) || isNaN(parseInt(searchTerm));
    };
    
    const filteredCars = carDetails
    ? carDetails.filter((car) => {
        const filterKey =
            filterOption === 'model'
                ? car.model.modelname.toLowerCase()
                : filterOption === 'power'
                    ? compareWithOperator(car.model.engine.horsepower, searchTerm)
                    : filterOption === 'price'
                        ? compareWithOperator(car.price, searchTerm)
                        : '';

        // If searchTerm is empty or only contains operators, show all cars
        if (!searchTerm.trim() || searchTerm.trim().replace(/[<>]/g, '').length === 0) {
            return true;
        }

        if (filterOption === 'model') {
            return typeof filterKey === 'string' && filterKey.toLowerCase().includes(searchTerm.toLowerCase());
        }

        // For 'power' and 'price', filterKey is a boolean result from compareWithOperator
        return filterKey;
    })
    : [];


    return (
        <div className='container'>
            <div className='py-4'>
                <div className="mb-3">
                    {/* Link to the Add Form page */}
                    <Link to={`/car/${carMake}/addModel`} className="btn btn-success mb-2">Add</Link>
                </div>
                {carDetails ? (
                    <div>
                        <h2>Car Details</h2>
                        <div className="mb-3">
                            <select
                                value={filterOption}
                                onChange={handleFilterChange}
                                className="form-control"
                            >
                                <option value="model">Model</option>
                                <option value="power">Power</option>
                                <option value="price">Price</option>
                            </select>
                            <input
                                type="text"
                                placeholder="Search..."
                                value={searchTerm}
                                onChange={handleSearch}
                                className="form-control mr-2"
                            />
                        </div>
                        {filteredCars.length > 0 ? (
                            <table className="table">
                                <thead>
                                    <tr>
                                        <th>Image</th>
                                        <th>Make</th>
                                        <th>Model</th>
                                        <th>Engine</th>
                                        <th>Displacement</th>
                                        <th>Power</th>
                                        <th>Torque</th>
                                        <th>Aspiration</th>
                                        <th>Fuel</th>
                                        <th>Trim</th>
                                        <th>Interior</th>
                                        <th>Price</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {filteredCars.map((car) => (
                                        <tr key={car.id}>
                                            <td>
                                                <img
                                                    src={require(`${car.images.image_url.replace(/ /g, '_')}`)}
                                                    alt={car.makename}
                                                    style={{ width: '100%', height: 'auto', maxWidth: '150px' }}
                                                />
                                            </td>
                                            <td>{car.make.makename}</td>
                                            <td>
                                                {editMode === car.model.modelid ? (
                                                    <input
                                                        type="text"
                                                        value={car.model.modelname}
                                                        onChange={(e) => handleInputChange(car.model.modelid, 'modelname', e.target.value)}
                                                        style={{ width: '50%' }}
                                                    />
                                                ) : (
                                                    car.model.modelname
                                                )}
                                            </td>
                                            <td>
                                                {editMode === car.model.modelid ? (
                                                    <input
                                                        type="text"
                                                        value={car.model.engine.enginename}
                                                        onChange={(e) => handleInputChange(car.model.engine.enginename, 'enginename', e.target.value)}
                                                        style={{ width: '50%' }}
                                                    />
                                                ) : (
                                                    car.model.engine.enginename
                                                )}
                                            </td>
                                            <td>
                                                {editMode === car.model.modelid ? (
                                                    <input
                                                        type="text"
                                                        value={car.model.engine.displacement}
                                                        onChange={(e) => handleInputChange(car.model.engine.displacement, 'displacement', e.target.value)}
                                                        style={{ width: '50%' }}
                                                    />
                                                ) : (
                                                    car.model.engine.displacement + 'cc'
                                                )}
                                            </td>
                                            <td>
                                                {editMode === car.model.modelid ? (
                                                    <input
                                                        type="text"
                                                        value={car.model.engine.horsepower}
                                                        onChange={(e) => handleInputChange(car.model.engine.horsepower, 'horsepower', e.target.value)}
                                                        style={{ width: '50%' }}
                                                    />
                                                ) : (
                                                    car.model.engine.horsepower + 'hp'
                                                )}
                                            </td>
                                            <td>
                                                {editMode === car.model.modelid ? (
                                                    <input
                                                        type="text"
                                                        value={car.model.engine.torque}
                                                        onChange={(e) => handleInputChange(car.model.engine.torque, 'torque', e.target.value)}
                                                        style={{ width: '50%' }}
                                                    />
                                                ) : (
                                                    car.model.engine.torque + 'nm'
                                                )}
                                            </td>
                                            <td>{editMode === car.model.modelid ? (
                                                <select
                                                    id="aspiration"
                                                    value={aspiration}
                                                    onChange={(e) => setAspiration(e.target.value)}
                                                    className="form-control"
                                                    required
                                                >
                                                    <option value="">Select Aspiration...</option>
                                                    {aspirationOptions.map((option, index) => (
                                                        <option key={index} value={option}>
                                                            {option}
                                                        </option>
                                                    ))}
                                                </select>
                                            ) : (
                                                car.model.engine.aspiration.type
                                            )}
                                            </td>
                                            <td>{editMode === car.model.modelid ? (
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
                                            ) : (
                                                car.model.engine.fuel.type
                                            )}</td>
                                            <td>
                                                {editMode === car.model.modelid ? (
                                                    <input
                                                        type="text"
                                                        value={car.model.configuration.trimlevel}
                                                        onChange={(e) => handleInputChange(car.model.configuration.trimlevel, 'trimlevel', e.target.value)}
                                                        style={{ width: '50%' }}
                                                    />
                                                ) : (
                                                    car.model.configuration.trimlevel
                                                )}
                                            </td>
                                            <td>
                                                {editMode === car.model.modelid ? (
                                                    <input
                                                        type="text"
                                                        value={car.model.configuration.interiorcolor}
                                                        onChange={(e) => handleInputChange(car.model.configuration.interiorcolor, 'interiorcolor', e.target.value)}
                                                        style={{ width: '50%' }}
                                                    />
                                                ) : (
                                                    car.model.configuration.interiorcolor
                                                )}
                                            </td>
                                            <td>
                                                {editMode === car.model.modelid ? (
                                                    <input
                                                        type="text"
                                                        value={car.price}
                                                        onChange={(e) => handleInputChange(car.price, 'price', e.target.value)}
                                                        style={{ width: '50%' }}
                                                    />
                                                ) : (
                                                    car.price + '$'
                                                )}
                                            </td>
                                            <td>
                                                {editMode === car.model.modelid ? (
                                                    <>
                                                        <button
                                                            onClick={() => handleSave(car.model.modelid)}
                                                            className="btn btn-success btn-sm"
                                                        >
                                                            Save
                                                        </button>
                                                        <button
                                                            onClick={() => handleCancel()}
                                                            className="btn btn-secondary btn-sm ml-2"
                                                        >
                                                            Cancel
                                                        </button>
                                                    </>
                                                ) : (
                                                    <>
                                                        <button
                                                            onClick={() => handleEdit(car.model.modelid)}
                                                            className="btn btn-primary btn-sm mr-2"
                                                        >
                                                            Edit
                                                        </button>
                                                        <button
                                                            onClick={() => handleDelete(car.model.modelid, car.id)}
                                                            className="btn btn-danger btn-sm"
                                                        >
                                                            Delete
                                                        </button>
                                                    </>
                                                )}
                                            </td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        ) : (
                            <p>No matching cars found.</p>
                        )}
                    </div>
                ) : (
                    <p>There are no models to show. Please add.</p>
                )}
            </div>
        </div>
    );
};