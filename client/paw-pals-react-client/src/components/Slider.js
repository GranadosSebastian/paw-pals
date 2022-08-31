import React, {useState} from 'react';
import './Slider.css';
import SliderBtn from './SliderBtn.js';
import SliderData from './SliderData.js';
import img1 from './imgs/img1.jpg'
import img2 from './imgs/img2.jpg'
import img3 from './imgs/img3.jpg'

export default function Slider() {

  const [slideIndex, setSlideIndex] = useState(1)

  const nextSlide = () => {
      if(slideIndex !== SliderData.length) {
        setSlideIndex(slideIndex + 1)
      } else if (slideIndex === SliderData.length) {
        setSlideIndex(1)
      }
  }

  const prevSlide = () => {
      if(slideIndex !== 1) {
        setSlideIndex(slideIndex - 1)
      } else if (slideIndex === 1) {
        setSlideIndex(SliderData.length)
      }
  }

  const moveDot = index => {
    setSlideIndex(index)
  }


  return (
    <div className="container-slider">
        {SliderData.map((obj, index) => {
          return (
            <div key={obj.id} 
            className={slideIndex === index + 1 ? "slide active-anim" : "slide"}>
              <img 
              src={img1} 
              />;
              </div>
          )
        })}
        <SliderBtn moveSlide={nextSlide} direction={"next"} />
        <SliderBtn moveSlide={prevSlide} direction={"prev"} />

        <div className="container-dots">
            {Array.from({length: 5}).map((item, index) => (
                <div 
                onClick={() => moveDot(index + 1)}
                className={slideIndex === index + 1 ? "dot active" : "dot"}
                ></div>
            ))}
        </div>
    </div>
  )
}