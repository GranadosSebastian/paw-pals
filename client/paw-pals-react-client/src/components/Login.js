import React, { useState, useContext } from "react";
import { Link, useNavigate } from "react-router-dom";

import AuthContext from "../AuthContext";
import Errors from "./Errors";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState([]);

    const auth = useContext(AuthContext);
    console.log("Top", auth);
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        /*

        POST http://localhost:8080/api/authenticate HTTP/1.1
        Content-Type: application/json

        {
        "username": "john@smith.com",
        "password": "P@ssw0rd!"
        }

        */

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
                    //     {
                    //     "jwt_token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzb2xhci1mYXJtLWFwaSIsInN1YiI6ImpvaG5Ac21pdGguY29tIiwiYXV0aG9yaXRpZXMiOiJST0xFX0FETUlOIiwiZXhwIjoxNjYwNzkyMTU2fQ.HXIuh8CoyZCHD2xZtyp2Ad5AI4u-eRw90jc4AH4OSkY"
                    //     }


                    auth.login(data.jwt_token);
                    navigate('/');
                } else {
                    // we have error messages
                    setErrors(['login failure']);
                }
            })
            .catch(console.log);


    };

    return (
        <>
            <h2>Login</h2>
            <Errors errors={errors} />
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    {/* Includes for/id attributes for basic HTML accessibility ♿. */}
                    <label htmlFor="username">Username: fitchyfetch@gmail.com   mozippeezagg@yahoo.com</label>
                    <input id="username" type="text" className="form-control" onChange={(event) => setUsername(event.target.value)} value={username} />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password: P@ssw0rd!</label>
                    <input id="password" type="password" className="form-control" onChange={(event) => setPassword(event.target.value)} value={password} />
                </div>
                <div className="mt-4">
                    <button className="btn btn-success mr-2" type="submit">Login</button>
                    <Link to="/register">I don't have an account</Link>
                </div>
            </form>
        </>
    );
}

export default Login;
