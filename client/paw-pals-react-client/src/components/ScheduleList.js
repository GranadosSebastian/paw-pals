import { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';

import AuthContext from '../AuthContext';


function ScheduleList() {
    const [schedules, setSchedules] = useState([])
    const [fullSchedules, setFullSchedules] = useState([])
    const [animals, setAnimals] = useState([])
    const [appUsers, setAppUsers] = useState([])


    const auth = useContext(AuthContext);

    const rebuildScheduleObject = () => {

        const phaseOneSchedules = schedules.map(schedule => {
            return animals.map(ani => {
                if (schedule.animalId === ani.animalId) {
                    return { ...schedule, animal: ani }
                }
            })
        })
        const phaseTwoSchedules = phaseOneSchedules.flat().filter(sch => sch !== undefined);

        const phaseThreeSchedules = phaseTwoSchedules.map(sch => {
            return appUsers.map(user => {
                if (sch.appUserId === user.appUserId) {
                    return { ...sch, appUser: user }
                }
            })
        })
        const phaseFourSchedules = phaseThreeSchedules.flat().filter(sch => sch !== undefined);

        const phaseFiveSchedules = phaseFourSchedules.map(sch => {
            return appUsers.map(user => {
                if (sch.animal.appUserId === user.appUserId) {
                    return { ...sch, animal: { ...sch.animal, appUser: user } }
                }
            })
        })

        const phaseSixSchedules = phaseFiveSchedules.flat().filter(sch => sch !== undefined);

        console.log(phaseSixSchedules)
        return phaseSixSchedules;
    }

    useEffect(() => {
        if (appUsers.length > 0) {
            setFullSchedules(rebuildScheduleObject());
        }
    }, [appUsers])


    useEffect(() => {
        const init = {
            headers: {
                'Authorization': `Bearer ${auth.user.token}`
            },
        };


        fetch('http://localhost:8080/api/animal/schedule', init)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setSchedules(data))
            .catch(error => console.error(error));

        fetch('http://localhost:8080/api/animal')
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then((data) => setAnimals(data))
            .catch(error => console.error(error));

        fetch('http://localhost:8080/api/animal/appuser')
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setAppUsers(data))
            .catch(error => console.error(error));


    }, []);

    const handleDeleteSchedule = (scheduleId) => {
        const schedule = schedules.find(schedule => schedule.id === scheduleId);

        if (window.confirm(`Delete meeting appointment with ${fullSchedules.animal.animalName}?`)) {
            const init = {
                method: 'DELETE'
                ,
                headers: {
                    'Authorization': `Bearer ${auth.user.token}`
                },
            };

            fetch(`http://localhost:8080/api/schedule/${scheduleId}`, init)
                .then(response => {
                    if (response.status === 204) {
                        const newSchedules = schedules.filter(schedule => schedule.id !== scheduleId);

                        setSchedules(newSchedules);
                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`);
                    }
                })
                .catch(console.log);
        }
    };



    return (
        <>
            <h2 className="mb-4">Appointments</h2>


            {/* FETCH ARRAY OF ANIMAL OBJECTS, SET TO STATE */}
            {/* FETCH ARRAY OF USER OBJECTS, SET TO STATE */}

            {/* FILTER STATE TO GET ANIMALS AND USERS BY THEIR IDS */}

            <table className="table table-striped table-sm table-hover">
                <thead className="thead-dark">
                    <tr>
                        <th>Pet Name</th>
                        <th>Appointment</th>
                        <th>Adopter Name</th>
                        <th>Adopter Phone</th>
                        <th>Foster Name</th>
                        <th>Foster Phone</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    {fullSchedules.map((fullSchedule) => (
                        <tr key={fullSchedule.scheduleId}>
                            <td>{fullSchedule.animal.animalName}</td>
                            <td>{fullSchedule.dateTime}</td>
                            <td>{fullSchedule.appUser.firstName} {fullSchedule.appUser.lastName}</td>
                            <td>{fullSchedule.appUser.phone}</td>
                            <td>{fullSchedule.animal.appUser.firstName} {fullSchedule.animal.appUser.lastName}</td>
                            <td>{fullSchedule.animal.appUser.phone}</td>
                            <td>
                                <div className="float-right mr-2">
                                    {auth.user && (auth.user.appUserId === fullSchedule.appUser.appUserId || auth.user.hasRole('ROLE_staff')) && (
                                        <Link className="btn btn-primary btn-sm mr-2" to={`/schedule/edit/${fullSchedule.scheduleId}`}>
                                            <i className="bi bi-pencil-square"></i> Edit
                                        </Link>
                                    )}
                                    {/* {auth.user && auth.user.hasRole('ROLE_staff') && (
                                        <button className="btn btn-danger btn-sm" onClick={() => handleDeleteSchedule(fullSchedule.scheduleId)}>
                                            <i className="bi bi-trash"></i> Delete
                                        </button>
                                    )} */}
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}

export default ScheduleList;