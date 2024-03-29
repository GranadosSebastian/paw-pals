import { useContext, useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import AuthContext from '../AuthContext';
import Errors from './Errors';

const SCHEDULE_DEFAULT = {
    dateTime: '',
    animalId: 0,
    appUserId: 0
};

function ScheduleForm() {

    const [schedule, setSchedule] = useState(SCHEDULE_DEFAULT);
    const [errors, setErrors] = useState([]);
    const auth = useContext(AuthContext);
    const navigate = useNavigate();
    const { scheduleId } = useParams();
    const { animalId } = useParams();

    useEffect(() => {

        if (scheduleId) {
            fetch(`http://localhost:8080/api/animal/schedule/${scheduleId}`)
                .then(response => {
                    if (response.status === 200) {
                        return response.json();
                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`)
                    }
                })
                .then(data => setSchedule(data))
                .catch(console.log)
        } else {
            setSchedule({ ...schedule, appUserId: auth.user.appUserId, animalId: parseInt(animalId) })
        }

    }, [scheduleId]);

    const handleChange = (event) => {
        const newSchedule = { ...schedule };

        newSchedule[event.target.name] = event.target.value;

        setSchedule(newSchedule);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (scheduleId) {
            updateSchedule();
        } else {
            addSchedule();
        }
    };

    const addSchedule = () => {
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${auth.user.token}`
            },
            body: JSON.stringify(schedule)
        };
        fetch('http://localhost:8080/api/animal/schedule', init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                if (data.scheduleId) {

                    navigate('/schedule');
                } else {

                    setErrors(data);
                }
            })
            .catch(console.log);
    }

    const updateSchedule = () => {
        schedule.scheduleId = scheduleId;

        const init = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${auth.user.token}`
            },
            body: JSON.stringify(schedule)
        };

        fetch(`http://localhost:8080/api/animal/schedule/${scheduleId}`, init)
            .then(response => {
                if (response.status === 204) {
                    return null;
                } else if (response.status === 400) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                if (!data) {
                    navigate('/schedule')
                } else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    }

    return (
        <>
            <h2>Schedule</h2>

            <h2 className="mb-4">{scheduleId ? 'Update Appointment' : 'Add Appointment'}</h2>

            <Errors erros={errors} />

            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor='dateTime'>Date and Time</label>
                    <input id="dateTime" name="dateTime" type="datetime-local" className="form-control"
                        value={schedule.dateTime} onChange={handleChange} />
                </div>
                <div>
                    <button className="btn btn-success mr-2" type="submit">
                        {scheduleId ? 'Update Schedule' : 'Add Schedule'}
                    </button>
                    <Link className="btn btn-danger" to="/schedule">
                        Cancel
                    </Link>
                </div>
                <div className="form-group">
                    <label htmlFor='appUserId'></label>
                    <input id="appUserId" name="appUserId" type="text" className="form-control invisible"
                        value={auth.user.appUserId} onChange={handleChange} disabled readonly />
                </div>
                <div className="form-group">
                    <label htmlFor='animalId'></label>
                    <input id="animalId" name="animalId" type="text" className="form-control invisible"
                        value={animalId} onChange={handleChange} disabled readonly />
                </div>
            </form>
        </>
    );
}

export default ScheduleForm;