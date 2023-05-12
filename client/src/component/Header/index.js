import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import './style.css'
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { tokenSelector } from '../../service/selector';
import { authSlice } from '../../service/authSlice';
const Header = () => {

  const token = useSelector(tokenSelector);
  const dispatch = useDispatch();

  const [logged, setLogged] = useState(token ? true : false)

  const handleLogBtn = () => {
    if (token) dispatch(authSlice.actions.logout())
    setLogged(false)
  }

  useEffect(() => {
    if (token) setLogged(true)
    else setLogged(false)
  }, [token])


  return (
    <nav className="navbar navbar-expand-lg header_nav" style={{
      marginBottom: "20px",
      padding: "0 20px"
    }} >
      <Link className="navbar-brand header_logo" to="/">
        <img className='logo' src="/images/CaTS.png" alt="logo"></img>
      </Link>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item active">
            <Link className="nav-link" to="/">Giới thiệu</Link>
          </li>
          <li className="nav-item dropdown">
            <div className="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Quản lý toà nhà
            </div>
            <div className="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <Link className="dropdown-item" to="/companies">Công ty</Link>
              <Link className="dropdown-item" to="/floors">Mặt bằng</Link>
              <Link className="dropdown-item" to="#">Tiền tháng này</Link>
              <Link className="dropdown-item" to="#">Thống kê</Link>
              <Link className="dropdown-item" to="#">Doanh thu</Link>
            </div>
          </li>
          <li className="nav-item dropdown">
            <Link to="/registration-service/companies" className="nav-link" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Quản lý dịch vụ
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/employees-building">Quản lý nhân viên toà nhà</Link>
          </li>
        </ul>
      </div>
      <div className="nav_log_icon"
        onClick={handleLogBtn}
      >
        {logged ?
          <i className="bi bi-box-arrow-in-right" onClick={setLogged}></i>
          : <i className="bi bi-box-arrow-in-left" onClick={setLogged}></i>}
      </div>
    </nav>
  )
}

export default Header;