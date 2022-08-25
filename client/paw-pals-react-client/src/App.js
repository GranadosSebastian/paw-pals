import { useState, useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
import jwt_decode from 'jwt-decode';

import Home from "./components/Home";
import Navbar from './components/Navbar';
import Adopt from './components/Adopt';
import NotFound from './components/NotFound';
import PetForm from './components/PetForm';
import UserForm from './components/UserForm';
import UserList from './components/UserList';
import Login from './components/Login';
import ScheduleForm from './components/ScheduleForm';
import Register from './components/Register';

const LOCAL_STORAGE_TOKEN_KEY = 'pawPalsToken';

function App() {
  const [user, setUser] = useState(null);
  const [restoreLoginAttemptCompleted, setRestoreLoginAttemptCompleted] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
    if (token) {
      login(token);
    }
    setRestoreLoginAttemptCompleted(true);
  }, []);

  const login = (token) => {
    localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, token);

    const { sub: username, authorities, appUserId, firstName, lastName, address, phone, roleId } = jwt_decode(token);

    const roles = authorities.split(',');

    // create our user object
    const userToLogin = {
      appUserId,
      username,
      firstName,
      lastName,
      address,
      roleId,
      roles,
      phone,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      }
    };

    console.log(userToLogin);

    // update the global user state variable
    setUser(userToLogin);
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY);
  };

  const auth = {
    user,
    login,
    logout
  };

  if (!restoreLoginAttemptCompleted) {
    return null;
  }

  return (
    <>
      <Navbar />

      <h1>Paw Pals</h1>
      <Routes>
        <Route path='/' element={<Home />} exact />

        <Route path='/login' element={<Login />} exact />

        <Route path='/register' element={<Register />} exact />

        <Route path='/animals/edit/:animalId' element={<PetForm />} />

        <Route path='/animals/add' element={<PetForm />} />

        <Route path='/animals' element={<Adopt />} />

        <Route path='/users/edit/:userId' element={<UserForm />} />

        <Route path='/users' element={<UserList />} />

        <Route path='/schedule' element={<ScheduleForm />} />

        <Route path='*' element={<NotFound />} />

        <Route path='/login' element={<Login />} />
      </Routes>
    </>
  );
}

export default App;
