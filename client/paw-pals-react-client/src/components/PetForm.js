import { useContext, useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import AuthContext from '../AuthContext';
import Errors from './Errors';

const ANIMAL_DEFAULT = {
    animalName: '',
    breed: '',
    age: 0,
    size: '',
    arrivalDate: '',
    friendlinessLevel: '',
    isAvailable: false
};

function PetForm() {
    const [animal, setAnimal] = useState(ANIMAL_DEFAULT);
    const [errors, setErrors] = useState([]);

    const auth = useContext(AuthContext);

    const navigate = useNavigate();
    const { animalId } = useParams();

    useEffect(() => {
        if (animalId) {
            fetch(`http://localhost:8080/api/animal/${animalId}`)
                .then(response => {
                    if (response.status === 200) {
                        return response.json();
                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`)
                    }
                })
                .then(data => setAnimal(data))
                .catch(console.log)
        }
    }, [animalId]);

    const handleChange = (event) => {
        const newAnimal = { ...animal };

        newAnimal[event.target.name] = event.target.value;

        setAnimal(newAnimal);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (animalId) {
            updateAnimal();
        } else {
            addAnimal();
        }
    };

    const addAnimal = () => {
        const init = {
            method: 'POST',
            header: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${auth.user.token}`
            },
            body: JSON.stringify({ ...animal, appUserId: auth.user.appUserId })
        };
        console.log("animal object being sent: ", { ...animal, appUserId: auth.user.appUserId })
        fetch('http://localhost:8080/api/animal', init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                if (data.animalId) {

                    navigate('/animals');
                } else {

                    setErrors(data);
                }
            })
            .catch(console.log);
    }

    const updateAnimal = () => {
        animal.animalId = animalId;

        const init = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${auth.user.token}`
            },
            body: JSON.stringify(animal)
        };

        fetch(`http://localhost:8080/api/animal/${animalId}`, init)
            .then(response => {
                if (response.status === 204) {
                    return null;
                } else if (response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                if (!data) {
                    navigate('/animals')
                } else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    }


    return (
        <>
            <h2 className="mb-4">{animalId ? 'Update Pet' : 'Add Pet'}</h2>

            <Errors erros={errors} />

            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="animalName">Pet Name:</label>
                    <input id="animalName" name="animalName" type="text" className="form-control"
                        value={animal.animalName} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="breed">Breed:</label>
                    <input id="breed" name="breed" type="text" className="form-control"
                        value={animal.breed} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="age">Age:</label>
                    <input id="age" name="age" type="number" className="form-control"
                        value={animal.age} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="size">Size: </label>
                    <select id="size" name="size" className="form-control"
                        value={animal.size} onChange={handleChange}>
                        <option value="SMALL">Small</option>
                        <option value="MEDIUM">Medium</option>
                        <option value="LARGE">Large</option>

                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="arrivalDate">Arrival Date:</label>
                    <input id="arrivalDate" name="arrivalDate" type="date" className="form-control"
                        value={animal.arrivalDate} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="friendlinessLevel">Friendliness Description:</label>
                    <input id="friendlinessLevel" name="friendlinessLevel" type="text" className="form-control"
                        value={animal.friendlinessLevel} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label className="form-check-label" htmlFor="isAvailable">
                        Available:
                    </label>
                    <input className="form-check-input" type="checkbox" id="isAvailable"
                        value={animal.isAvailable} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="speciesId">Species: </label>
                    <select id="speciesId" name="speciesId" className="form-control"
                        value={animal.speciesId} onChange={handleChange}>
                        <option value="1">Cat</option>
                        <option value="2">Dog</option>
                        <option value="3">Rabbit</option>
                        <option value="4">Hamster</option>
                        <option value="5">Guinea Pig</option>
                        <option value="6">Bird</option>
                        <option value="7">Fish</option>
                        <option value="8">Reptile</option>
                    </select>
                </div>
                <div>
                    <button className="btn btn-success mr-2" type="submit">
                        {animalId ? 'Update Pet' : 'Add Pet'}
                    </button>
                    <Link className="btn btn-warning" to="/animals">
                        Cancel
                    </Link>
                </div>
            </form>
        </>



    );
}

export default PetForm;