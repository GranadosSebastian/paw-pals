
import { useEffect, useState, useContext } from "react";
import { Link, useNavigate, useParams } from 'react-router-dom';

import AuthContext from "../AuthContext";
import Errors from './Errors';

const USER_DEFAULT = {
    firstName: '',
    lastName: '',
    address: '',
    phone: '',
    username: '',
    roleId: 1
};

function UserForm() {
    const [user, setUser] = useState(USER_DEFAULT);
    const [errors, setErrors] = useState([]);

    const auth = useContext(AuthContext);

    const navigate = useNavigate();
    const { userId } = useParams();

    useEffect(() => {
        if (userId) {
            fetch(`http://localhost:8080/api/user/${userId}`)
                .then(response => {
                    if (response.status === 200) {
                        return response.json();
                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`);
                    }
                })
                .then(data => setUser(data))
                .catch(console.log);
        }
    }, [userId]);


    const handleChange = (event) => {
        const newUser = { ...user };

        newUser[event.target.name] = event.target.value;

        setUser(newUser);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (userId) {
            updateUser();
        } else {
            addUser();
        }
    };

    const addUser = () => {
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 'Authorization': `Bearer ${auth.user.token}`
            },
            body: JSON.stringify(user)
        };

        fetch('http://localhost:8080/api/user', init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                if (data.userId) {


                    navigate('/users');
                } else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    }

    const updateUser = () => {
        user.userId = userId;


        const init = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                // 'Authorization': `Bearer ${auth.user.token}`
            },
            body: JSON.stringify(user)
        };

        fetch(`http://localhost:8080/api/user/${userId}`, init)
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
                    navigate('/users')
                } else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    }


    return (
        <>
            <h2 className="mb-4">Update User</h2>

            <Errors errors={errors} />

            <form id="form" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="firstName">First Name</label>
                    <input id="firstName" name="firstName" type="text" className="form-control"
                        value={user.firstName} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="lastName">Last Name:</label>
                    <input id="lastName" name="lastName" type="text" className="form-control"
                        value={user.lastName} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="address">Address:</label>
                    <input id="address" name="address" type="text" className="form-control"
                        value={user.address} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="phone">Phone:</label>
                    <input id="phone" name="phone" type="text" className="form-control"
                        value={user.phone} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="username">Email:</label>
                    <input id="username" name="username" type="text" className="form-control"
                        value={user.email} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor="roleId">Role: </label>
                    <select id="roleId" name="roleId" className="form-control"
                        value={user.roleId} onChange={handleChange}>
                        <option value="1">Staff</option>
                        <option value="2">Volunteer</option>
                        <option value="3">Foster Parent</option>
                        <option value="4">Adopter</option>
                    </select>
                </div>
                <div className="mt-4">
                    <button className="btn btn-success mr-2" type="submit">
                        <i className="bi bi-file-earmark-check"></i> Update User
                    </button>
                    <Link className="btn btn-warning" to="/users">
                        <i className="bi bi-octagon"></i> Cancel
                    </Link>
                </div>
            </form>
        </>
    );
}

export default UserForm;