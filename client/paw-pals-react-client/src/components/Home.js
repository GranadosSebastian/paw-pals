import { useNavigate } from 'react-router-dom';
import { useAsync } from "ax-react-lib";
import { useState } from "react";
import { Slideshow, SlideshowItem } from "./slideshow";


function Home() {

    const navigate = useNavigate();
    const navigateToAdopt = () => {
        navigate('/animals');
    }

    function Photo() {
        const [data, setData] = useState({});
        useAsync(async () => {
          const data = await fetch(
            `https://jsonplaceholder.typicode.com/photos/${Math.round(
              Math.random() * 500
            )}`
          ).then((response) => response.json());
          setData(data);
        });
        return <img src={data.url}></img>;
      }


    return (
        <>
            <h2>Welcome to Paw Pals!</h2>
            <p></p>
            <button className="btn btn-success mr-2 ml-3" type='button' onClick={navigateToAdopt}>Adopt</button>

            
        </>
    );
}

export default Home;