import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <nav>
            <ul>
                <li><Link to='/'>Home</Link></li>
                <li><Link to='/animals'>Adopt</Link></li>
                <li><Link to='/users'>Users</Link></li>
                <li><Link to='/login'>Login</Link></li>
                <li><Link to='/users/add'>Sign Up</Link></li>
                <li><Link to='/schedule'>Schedule</Link></li>
            </ul>
        </nav>
    );
}

export default Navbar;