import { useContext, useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import AuthContext from "../AuthContext";

function Adopt() {
    const [animals, setAnimals] = useState([]);

    const auth = useContext(AuthContext);

    // TODO uncomment when security added


    useEffect(() => {
        fetch('http://localhost:8080/api/animal')
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setAnimals(data))
            .catch(console.log);
    }, []);

    const handleDeleteAnimal = (animalId) => {
        const animal = animals.find(animal => animal.animalId === animalId);

        if (window.confirm(`Delete ${animal.animalName}?`)) {
            const init = {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${auth.user.token}`

                },
            };

            fetch(`http://localhost:8080/api/animal/${animalId}`, init)
                .then(response => {
                    if (response.status === 204) {
                        const newAnimals = animals.filter(animal => animal.animalId !== animalId);

                        setAnimals(newAnimals);

                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`);
                    }
                })
                .catch(console.log);
        }
    };



    return (
        <>
            {/* {auth.user && auth.user.hasRole('ROLE_staff') && (
                <div className="text-center">
                    <Link className="btn btn-success ml-1 mb-4" to={`/animals/add/${auth.user.appUserId}`}>
                        <i className="bi bi-pencil-square"></i> Add New Animal
                    </Link>
                </div>

            )} */}
            <div className="row">
                {animals.map((animal) =>
                    <div className="col-4">
                        <div key={animal.animalId} className="shadow card mb-4">
                            <img src={process.env.PUBLIC_URL + `./images/${animal.animalId}.jpg`} className="card-img-top center-cropped rounded-circle mx-auto" alt="..." />
                            <div className="card-body">
                                <h5 className="card-title">{animal.animalName}</h5>

                                <p><strong>Age: </strong>{animal.age}</p>
                                <p><strong>Species: </strong>{animal.speciesString}</p>
                                <p><strong>Breed: </strong>{animal.breed}</p>
                                <p><strong>Size: </strong>{animal.size}</p>
                                <p><strong>Friendliness: </strong>{animal.friendliness}</p>
                                <p><strong>Arrival Date: </strong>{animal.arrivalDate}</p>

                                <div>
                                    {auth.user && (
                                        <Link className="btn btn-success ml-1" to={`/schedule/add/${animal.animalId}`}>Visit</Link>
                                    )}
                                    {auth.user && auth.user.hasRole('ROLE_staff') && (
                                        <Link className="btn btn-success ml-1" to={`/animals/edit/${animal.animalId}`}>
                                            <i className="bi bi-pencil-square"></i> Edit
                                        </Link>
                                    )}
                                    {auth.user && auth.user.hasRole('ROLE_staff') && (
                                        <button className="btn btn-success ml-1" onClick={() => handleDeleteAnimal(animal.animalId)}>
                                            <i className="bi bi-trash"></i> Delete
                                        </button>
                                    )}
                                </div>

                            </div>
                        </div>
                    </div>
                )}
            </div>

        </>
    );
}

export default Adopt;