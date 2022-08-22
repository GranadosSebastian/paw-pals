import { useNavigate } from 'react-router-dom';


function Home() {

    const navigate = useNavigate();
    const navigateToAdopt = () => {
        navigate('/animals');
    }


    return (
        <>
            <h2>Home</h2>
            <p>welcome page with Adopt button and a small list of available pets</p>
            <button type='button' onClick={navigateToAdopt}>Adopt</button>
        </>
    );
}

export default Home;