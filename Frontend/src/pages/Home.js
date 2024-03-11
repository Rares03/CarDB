import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';


export default function Home() {
    const [cars, setCars] = useState([]);
    const [error, setError] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        loadCars();
    }, []);

    const loadCars = async () => {
        try {
            const result = await axios.get("http://localhost:8080/makes");
            setCars(result.data);
        } catch (error) {
            console.error('Error loading cars:', error);

            // Set the error state for display
            setError(error.message || 'An error occurred while loading cars.');
        }
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredCars = cars
        ? cars.filter((car) => car.makename.toLowerCase().startsWith(searchTerm.toLowerCase()))
        : [];

    return (
        <div className='container'>
            <div className='py-4'>
                {error && (
                    <div className="alert alert-danger" role="alert">
                        {error}
                    </div>
                )}
                <div className="mb-3">
                <div className="mb-3">
                    {/* Link to the Add Form page */}
                    <Link to="/addMake" className="btn btn-success mb-2">Add</Link>
                </div>
                    <input
                        type="text"
                        placeholder="Search makes..."
                        value={searchTerm}
                        onChange={handleSearch}
                        className="form-control mr-2"
                    />
                </div>
                <div className="row row-cols-1 row-cols-md-6 g-4">
                    {filteredCars.map((car, index) => (
                        <div key={index} className="col">
                            <Link to={`/car/${car.makeid}`} className="card h-100">
                                <img 
                                    src={require(`${car.image}`)} 
                                    className="card-img-top" 
                                    alt={car.makename}
                                    style={{ width: '100%', height: '150px', objectFit: 'contain' }}
                                />
                                <div className="card-body" style={{ height: '100px' }}>
                                    <h5 className="card-title" style={{ margin: 0 }}>{car.makename}</h5>
                                    <p className="card-text" style={{ margin: 0 }}>{car.country}</p>
                                </div>
                            </Link>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}