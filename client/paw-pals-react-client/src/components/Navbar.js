import { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import AuthContext from '../AuthContext';

function Navbar() {
    const auth = useContext(AuthContext);

    const navigate = useNavigate();

    const handleLogout = () => {
        auth.logout();
        navigate("/");
    }

    return (

        <nav className="navbar navbar-expand-lg bg-light">
            <div className="container-fluid">
                <Link className="navbar-brand" to='/'>Paw Pals</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="nav-link active" to='/'>Home</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to='/animals'>Adopt</Link>
                        </li>
                        {auth.user && (
                            <li className="nav-item">
                                <Link className="nav-link" to='/users'>Users</Link>
                            </li>
                        )}
                        {auth.user && (
                            <li className="nav-item">
                                <Link className="nav-link" to='/schedule'>Schedule</Link>
                            </li>
                        )}
                        {!auth.user && (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" to='/login'>Login</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to='/register'>Register</Link>
                                </li>
                            </>
                        )}
                    </ul>
                    {auth.user && (
                        <div>
                            Welcome, {auth.user.firstName} {auth.user.lastName}
                            <button className="btn btn-success mr-2 ml-3" onClick={() => handleLogout()}>Logout</button>
                        </div>
                    )}
                </div>
            </div>
        </nav>


        // <nav>
        //     <ul>
        //         <li><Link to='/'>Home</Link></li>
        //         <li><Link to='/animals'>Adopt</Link></li>
        //         <li><Link to='/users'>Users</Link></li>
        //         <li><Link to='/login'>Login</Link></li>
        //         <li><Link to='/users/add'>Sign Up</Link></li>
        //         <li><Link to='/schedule'>Schedule</Link></li>
        //     </ul>
        // </nav>
    );
}

export default Navbar;