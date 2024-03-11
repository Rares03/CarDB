import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AddMakeForm = () => {
    const [image, setImage] = useState(null);
    const [makeName, setMakeName] = useState('');
    const [country, setCountry] = useState('');

    const navigate = useNavigate();

    const handleImageChange = (event) => {
        const selectedImage = event.target.files[0];
        setImage(selectedImage);
    };

    const handleMakeNameChange = (event) => {
        setMakeName(event.target.value);
    };

    const handleCountryChange = (event) => {
        setCountry(event.target.value);
    };

    const handleImageSubmit = async () => {
      try {
          const formData = new FormData();
          const renamedFileName = `${makeName}.png`;
  
          // Append the file directly with the desired filename
          formData.append('file', image, renamedFileName);
  
          // Send a separate request to upload the image
          await axios.post('http://localhost:8080/upload', formData);
      } catch (error) {
          console.error('Error uploading image:', error);
      }
  };
    
      const handleFormDataSubmit = async () => {
        try {
          // Send a separate request with JSON data
          const makeData = {
            makename: makeName,
            country: country,
            image: `./img/${makeName}.png`,
          };
    
          await axios.post('http://localhost:8080/make', makeData);
    
          // Redirect to the home page after successful submission
          navigate('/');
        } catch (error) {
          console.error('Error adding make:', error);
        }
      };
    
      const handleSubmit = async (event) => {
        event.preventDefault();
    
        try {
            // Handle image upload first and wait for it to complete
            await handleImageSubmit();
    
            // After successful image upload, wait for 2 seconds and then proceed
            await new Promise(resolve => setTimeout(resolve, 1000));
    
            // Handle JSON data submission
            await handleFormDataSubmit();
    
            // Redirect to the home page after 2 seconds
            navigate('/');
        } catch (error) {
            console.error('Error during form submission:', error);
        }
    };
    

    return (
        <div className="container">
            <div className="py-4">
                <h2>Add Make</h2>
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
                        <label htmlFor="makename" className="form-label">Make Name:</label>
                        <input
                            type="text"
                            id="makename"
                            value={makeName}
                            onChange={handleMakeNameChange}
                            className="form-control"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="country" className="form-label">Country:</label>
                        <input
                            type="text"
                            id="country"
                            value={country}
                            onChange={handleCountryChange}
                            className="form-control"
                            required
                        />
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );
};

export default AddMakeForm;
