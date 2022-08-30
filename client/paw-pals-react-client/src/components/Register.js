import React, { useState, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import AuthContext from '../AuthContext';
import Errors from './Errors';

function Register() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [address, setAddress] = useState('');
    const [phone, setPhone] = useState('');
    const [roleId, setRoleId] = useState(0);
    const [errors, setErrors] = useState([]);

    const auth = useContext(AuthContext);

    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();

        // Make sure that the user didn't make a mistake in entering their password.
        if (password !== confirmPassword) {
            setErrors(['your passwords don\'t match']);
            return;
        }

        /*
    
        POST http://localhost:8080/api/appuser HTTP/1.1
        Content-Type: application/json
    
        {
          "username": "test@test.com",
          "password": "P@ssw0rd!",
          "phoneNumber": "555-555-5555"
        }
    
        */

        const appUser = {
            username,
            password,
            firstName,
            lastName,
            phone,
            roleId
        };

        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(appUser)
        };

        fetch('http://localhost:8080/api/appuser', init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                if (data.appUserId) {

                    // HAPPY PATH :)

                    // Option 1: Send the user to the Home page... or a "Success" page

                    // Option 2: We can log them in automatically

                    const authAttempt = {
                        username,
                        password
                    };

                    const init = {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(authAttempt)
                    };

                    fetch('http://localhost:8080/api/authenticate', init)
                        .then(response => {
                            if (response.status === 200) {
                                return response.json();
                            } else if (response.status === 403) {
                                return null;
                            } else {
                                return Promise.reject(`Unexpected status code: ${response.status}`);
                            }
                        })
                        .then(data => {
                            if (data) {
                                // {
                                //   "jwt_token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjYWxvcmllLXRyYWNrZXIiLCJzdWIiOiJzbWFzaGRldjUiLCJhdXRob3JpdGllcyI6IlJPTEVfVVNFUiIsImV4cCI6MTYwNTIzNDczNH0.nwWJtPYhD1WlZA9mGo4n5U0UQ3rEW_kulilO2dEg7jo"
                                // }
                                auth.login(data.jwt_token);
                                navigate('/');
                            } else {
                                // we have error messages
                                setErrors(['login failure']);
                            }
                        })
                        .catch(console.log);

                } else {
                    // UNHAPPY PATH :(
                    setErrors(data);
                }
            })
            .catch(console.log);
    };

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    return (
        <>
            <h2>Register</h2>

            <Errors errors={errors} />

            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="username">Email:</label>
                    <input id="username" type="text" className="form-control"
                        onChange={handleUsernameChange} value={username} />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input id="password" type="password" className="form-control"
                        onChange={(event) => setPassword(event.target.value)} value={password} />
                </div>
                <div className="form-group">
                    <label htmlFor="confirmPassword">Confirm Password:</label>
                    <input id="confirmPassword" type="password" className="form-control"
                        onChange={(event) => setConfirmPassword(event.target.value)} value={confirmPassword} />
                </div>
                <div className="form-group">
                    <label htmlFor="firstName">First Name</label>
                    <input id="firstName" name="firstName" type="text" className="form-control"
                        onChange={(event) => setFirstName(event.target.value)} value={firstName} />
                </div>
                <div className="form-group">
                    <label htmlFor="lastName">Last Name:</label>
                    <input id="lastName" name="lastName" type="text" className="form-control"
                        onChange={(event) => setLastName(event.target.value)} value={lastName} />
                </div>
                <div className="form-group">
                    <label htmlFor="address">Address:</label>
                    <input id="address" name="address" type="text" className="form-control"
                        onChange={(event) => setAddress(event.target.value)} value={address} />
                </div>
                <div className="form-group">
                    <label htmlFor="phoneNumber">Phone Number:</label>
                    <input id="phoneNumber" type="text" className="form-control"
                        onChange={(event) => setPhone(event.target.value)} value={phone} />
                </div>
                <div className="form-group">
                    <label htmlFor="roleId">Role: </label>
                    <select id="roleId" name="roleId" className="form-control"
                        onChange={(event) => setRoleId(event.target.value)} value={roleId}>
                        <option value="4">Adopter</option>
                        {/* <option value="1">Staff</option>
                        <option value="2">Volunteer</option>
                        <option value="3">Foster Parent</option> */}
                    </select>
                </div>
                <div className="mt-4">
                    <button className="btn btn-success mr-2" type="submit">Register</button>
                    <Link to="/login">I have an existing account</Link>
                </div>
            </form>
        </>
    );
}

export default Register;