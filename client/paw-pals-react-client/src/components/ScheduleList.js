import { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';

import AuthContext from '../AuthContext';


function ScheduleList() {
    const [schedules, setSchedules] = useState([])

    const auth = useContext(AuthContext);


    useEffect(() => {
        const init = {
            headers: {
                'Authorization': `Bearer ${auth.user.token}`
            },
        };

        fetch('http://localhost:8080/api/schedule/user', init)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setSchedules(data))
            .catch(console.log);
    }, [auth.user.token]);

    const handleDeleteSchedule = (scheduleId) => {
        const schedule = schedules.find(schedule => schedule.id === scheduleId);

        if (window.confirm(`Delete meeting appointment with ${schedule.animalName}?`)) {
            const init = {
                method: 'DELETE',
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

            <table className="table table-striped table-sm table-hover">
                <thead className="thead-dark">
                    <tr>
                        <th>Pet Name</th>
                        <th>Appointment</th>
                        <th>Foster Name</th>
                        <th>Foster Phone</th>
                        <th>Adopter Name</th>
                        <th>Foster Phone</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    {schedules.map((schedule) => (
                        <tr key={schedule.scheduleId}>
                            <td>{schedule.animalName}</td>
                            <td>{schedule.dateTime}</td>
                            <td>{schedule.fosterFirstName} {schedule.fosterLastName}</td>
                            <td>{schedule.fosterPhone}</td>
                            <td>{schedule.adopterFirstName} {schedule.adopterLastName}</td>
                            <td>{schedule.adopterPhone}</td>
                            <td>
                                <div className="float-right mr-2">
                                    {auth.user && auth.user.appUserId === schedule.appUser.appUserId && (
                                        <Link className="btn btn-primary btn-sm mr-2" to={`/schedule/edit/${schedule.scheduleId}`}>
                                            <i className="bi bi-pencil-square"></i> Edit
                                        </Link>
                                    )}
                                    {auth.user && auth.user.hasRole('ROLE_staff') && (
                                        <button className="btn btn-danger btn-sm" onClick={() => handleDeleteSchedule(schedule.scheduleId)}>
                                            <i className="bi bi-trash"></i> Delete
                                        </button>
                                    )}
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