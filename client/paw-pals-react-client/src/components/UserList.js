import { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';

function UserList() {
    const [users, setUsers] = useState([])

    // const auth = useContext(AuthContext);


    useEffect(() => {
        fetch('http://localhost:8080/api/user')
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setUsers(data))
            .catch(console.log);
    }, []);


    const handleDeleteUser = (userId) => {
        const user = users.find(user => user.userId === userId);

        if (window.confirm(`Delete user ${user.firtName} ${user.lastName}?`)) {
            const init = {
                method: 'DELETE',
                // headers: {
                //     'Authorization': `Bearer ${auth.user.token}`
                // },
            };

            fetch(`http://localhost:8080/api/user/${userId}`, init)
                .then(response => {
                    if (response.status === 204) {
                        const newUsers = users.filter(user => user.userId !== userId);

                        setUsers(newUsers);


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
            <Link className="btn btn-primary my-4" to="/users/add">
                <i className="bi bi-plus-circle"></i> Add User
            </Link>

            <table className="table table-striped table-sm table-hover">
                <thead className="thead-dark">
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user) => (
                        <tr key={user.userId}>
                            <td>{user.firstname} {user.lastName}</td>
                            <td>{user.address}</td>
                            <td>{user.phone}</td>
                            <td>{user.email}</td>
                            <td>{user.roleId}</td>
                            <td>
                                <div className="float-right mr-2">
                                    <Link className="btn btn-primary btn-sm mr-2" to={`/users/edit/${user.userId}`}>
                                        <i className="bi bi-pencil-square"></i> Edit
                                    </Link>
                                    {/* {auth.user && auth.user.hasRole('ROLE_ADMIN') && (
                                        <button className="btn btn-danger btn-sm" onClick={() => handleDeleteUser(user.userId)}>
                                            <i className="bi bi-trash"></i> Delete
                                        </button>
                                    )} */}
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