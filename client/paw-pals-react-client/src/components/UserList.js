import { useNavigate } from 'react-router-dom';

function UserList() {

    const navigate = useNavigate();
    const navigateToAddUser = () => {
        navigate(`/users/add`);
    }

    return (
        <>
            <h2> A list of Users </h2>
            <p>will have buttons for edit and delete, also an add button that
                will display add button to link to Userform with extra functions for user permissions/roles</p>
            <p>*Users link will only be visible to users with authorized roles</p>
            <button type='button' onClick={navigateToAddUser}>Add User</button>
        </>

    );
}

export default UserList;