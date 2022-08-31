import { useNavigate } from 'react-router-dom';


function Home() {

    const navigate = useNavigate();
    const navigateToAdopt = () => {
        navigate('/animals');
    }


    return (
        <div className="text-center">
            <h2>Welcome to Paw Pals!</h2>
            <p></p>
            <button className="btn btn-success mr-2 ml-3" type='button' onClick={navigateToAdopt}>Adopt</button>
        </div>
    );
}

export default Home;