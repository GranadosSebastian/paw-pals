import { useContext, useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import AuthContext from "../AuthContext";

function Adopt() {
    const [animals, setAnimals] = useState([]);

    const auth = useContext(AuthContext);

    // TODO uncomment when security added
    // const auth = useContext(AuthContext);

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

        if (window.confirm(`Delete ${animal.Name}?`)) {
            const init = {
                method: 'DELETE',
                // TODO uncomment when security added
                // headers: {
                //     'Authorization': `Bearer ${auth.user.token}`

                // },
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

        <div className="row">
            {animals.map((animal) =>
                <div key={animal.animalId} className="card m-5" style={{ width: "18rem" }}>
                    <img src="https://cdn.pixabay.com/photo/2017/08/07/18/57/dog-2606759_960_720.jpg" className="card-img-top center-cropped rounded-circle" alt="..." />
                    <div className="card-body">
                        <h5 className="card-title">{animal.animalName}</h5>
                        <ul>
                            <li>{animal.age}</li>
                            <li>{animal.breed}</li>
                            <li>{animal.size}</li>
                            <li>{animal.friendliness}</li>
                            <li>{animal.arrivalDate}</li>
                        </ul>

                        <div>
                            <Link className="btn btn-success ml-1" to={`/schedule/add`}>Visit</Link>
                            {auth.user && auth.user.appUserId === animal.appUserId && (
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
            )}
        </div>
    );
}

export default Adopt;