// import React from 'react'
// import Carousel from 'react-bootstrap/Carousel';
// import { Link } from 'react-router-dom';
// import home__banner1 from "../../asset/image/home__banner1.webp"
// import home__banner2  from "../../asset/image/home__banner2.webp"
// const Home = () => {
//   return (
//     <>
//       <Carousel>
//         <Carousel.Item>
//           <Link to={`/shop`}>
//             <img style={{ width: "100%" }}
//               src={home__banner1}
//             />
//           </Link>
//         </Carousel.Item>
//         <Carousel.Item>
//           <Link to={`/shop`}>
//             <img style={{ width: "100%" }}
//               src={home__banner2}
//             />
//           </Link>
//         </Carousel.Item>
//       </Carousel>
//     </>
//   );
// }

// export default Home;

import React from 'react'
import LoginModal from '../../component/LoginModal'
import { Toast } from 'bootstrap'
import { ToastContainer } from 'react-bootstrap'

const index = () => {
  return (
    <>
      <h1
        style={{ display: "flex", justifyContent: "center" }}
      >Hệ thống quản lý văn phòng cho thuê</h1>
      <LoginModal></LoginModal>
    </>
  )
}

export default index