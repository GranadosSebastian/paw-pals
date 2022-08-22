import { Routes, Route } from 'react-router-dom';
import Home from "./components/Home";
import Navbar from './components/Navbar';
import Adopt from './components/Adopt';
import NotFound from './components/NotFound';
import PetForm from './components/PetForm';
import UserForm from './components/UserForm';
import UserList from './components/UserList';
import Login from './components/Login';
import ScheduleForm from './components/ScheduleForm';

function App() {
  return (
    <>
      <Navbar />

      <h1>Paw Pals</h1>
      <Routes>
        <Route path='/' element={<Home />} exact />

        <Route path='/animals/edit/:animalId' element={<PetForm />} />

        <Route path='/animals/add' element={<PetForm />} />

        <Route path='/animals' element={<Adopt />} />

        <Route path='/users/edit/:userId' element={<UserForm />} />

        <Route path='/users/add' element={<UserForm />} />

        <Route path='/users' element={<UserList />} />

        <Route path='/schedule' element={<ScheduleForm />} />

        <Route path='*' element={<NotFound />} />

        <Route path='/login' element={<Login />} />
      </Routes>
    </>
  );
}

export default App;
