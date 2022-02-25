import { React, useState, useEffect, useRef } from 'react';

const randomColor = (currentColor) => {
   const randomColor = ['red','green','blue','yellow','orange']
   const currentIdx = randomColor.indexOf(currentColor)
   let newIdx = currentIdx ;
   while (newIdx == currentIdx){
      newIdx = Math.trunc(Math.random()*3);
   }
   console.log("randomColor",randomColor[newIdx]);

   return randomColor[newIdx];
}

function useMagicColor() {
   const [color, setColor] = useState("transparent");
const colorRef = useRef("transparent")
   useEffect(() => {
      const colorInterval = setInterval(() => {
         const newColor = randomColor(colorRef.current);
         setColor(newColor)

         colorRef.current = newColor;
      }, 1000)

      return () => {
         clearInterval(colorInterval)
      }
   }, [])
   return color;
}

export default useMagicColor;