import { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';
import AuthContext from '../AuthContext';

function UserList() {
    const [appUsers, setAppUsers] = useState([])

    const auth = useContext(AuthContext);


    useEffect(() => {
        // const init = {
        //     headers: {
        //         'Authorization': `Bearer ${auth.user.token}`
        //     },
        // };

        fetch('http://localhost:8080/api/animal/appuser')
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => console.log("onFetch", data))
            // .then(data => setAppUsers(data))
            .catch(console.log);
    }, [auth.user.token]);


    const handleDeleteUser = (appUserId) => {
        const appUser = appUsers.find(appUser => appUser.appUserId === appUserId);

        if (window.confirm(`Delete user ${appUser.firtName} ${appUser.lastName}?`)) {
            // const init = {
            //     method: 'DELETE',
            //     headers: {
            //         'Authorization': `Bearer ${auth.user.token}`
            //     },
            // };

            const init = {
                method: 'DELETE'

            };

            fetch(`http://localhost:8080/api/animal/appuser/${appUserId}`, init)
                .then(response => {
                    if (response.status === 204) {
                        const newAppUsers = appUsers.filter(appUser => appUser.appUserId !== appUserId);

                        setAppUsers(newAppUsers);


                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`);
                    }
                })
                .catch(console.log);
        }
    };


    return (
        <>
            <h2 className="mb-4">Users</h2>
            <Link className="btn btn-primary my-4" to="/register">
                <i className="bi bi-plus-circle"></i> Add User
            </Link>

            <table className="table table-striped table-sm table-hover">
                <thead className="thead-dark">
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Email</th>
                        {/* <th>Role</th> */}
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    {appUsers.map((appUser) => (
                        <tr key={appUser.appUserId}>
                            <td>{appUser.firstName} {appUser.lastName}</td>
                            <td>{appUser.address}</td>
                            <td>{appUser.phone}</td>
                            <td>{appUser.username}</td>
                            {/* <td>{appUser.roleId}</td> */}
                            <td>
                                <div className="float-right mr-2">
                                    <Link className="btn btn-primary btn-sm mr-2" to={`/users/edit/${appUser.appUserId}`}>
                                        <i className="bi bi-pencil-square"></i> Edit
                                    </Link>
                                    {auth.user && auth.user.hasRole('ROLE_staff') && (
                                        <button className="btn btn-danger btn-sm" onClick={() => handleDeleteUser(appUser.appUserId)}>
                                            <i className="bi bi-trash"></i> Delete
                                        </button>
                                    )}
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>

    );
}

export default UserList;