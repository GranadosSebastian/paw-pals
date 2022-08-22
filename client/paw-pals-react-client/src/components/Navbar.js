import { Link } from 'react-router-dom';

function Navbar() {
    return (

        <nav className="navbar navbar-expand-lg bg-primary">
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
                        <li className="nav-item">
                            <Link className="nav-link" to='/users'>Users</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to='/login'>Login</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to='/users/add'>Sign Up</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to='/schedule'>Schedule</Link>
                        </li>
                    </ul>
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