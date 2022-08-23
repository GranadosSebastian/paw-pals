import { useEffect, useState } from "react";
import { Link } from 'react-router-dom';

function Adopt() {
    const [animals, setAnimals] = useState([]);

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

        <>
            {animals.map((animal) =>
                <div class="card" style={{ width: "18rem" }}>
                    <img src="https://cdn.pixabay.com/photo/2017/08/07/18/57/dog-2606759_960_720.jpg" className="card-img-top center-cropped rounded-circle" alt="..." />
                    <div className="card-body">
                        <h5 class="card-title">{animal.animalName}</h5>
                        <h5 class="card-title">Name</h5>
                        <ul>
                            <li>{animal.age}</li>
                            <li>{animal.breed}</li>
                            <li>{animal.size}</li>
                            <li>{animal.friendliness}</li>
                            <li>{animal.arrivalDate}</li>
                        </ul>
                        <Link to={`/schedule`}>Visit</Link>
                        <Link to={`/animals/edit/${animal.animalId}`}>Edit</Link>
                        <button onClick={() => handleDeleteAnimal(animal.animalId)}>Delete</button>
                    </div>
                </div>
            )}
        </>
    );
}

export default Adopt;