import { useContext, useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import AuthContext from '../AuthContext';
import Errors from './Errors';

const SCHEDULE_DEFAULT = {
    dateTime: '',
    appUserId: 0,
    animalId: 0
};

function ScheduleForm() {

    const [schedule, setSchedule] = useState(SCHEDULE_DEFAULT);
    const [errors, setErrors] = useState([]);
    const auth = useContext(AuthContext);
    const navigate = useNavigate();
    const { scheduleId } = useParams();

    //add
    //update

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
            header: {
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

                    navigate('/animal/schedule');
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
                    navigate('/animal/schedule')
                } else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    }

    return (
        <>
            <h2>Schedule</h2>
            <p>Schedule link will only display for foster pet parents that are logged in to
                select available date/times
            </p>
            <h2 className="mb-4">{scheduleId ? 'Update Schedule' : 'Add Schedule'}</h2>

            <Errors erros={errors} />

            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor='dateTime'>Date and Time</label>
                    <input id="dateTime" name="dateTime" type="text" className="form-control"
                        value={schedule.dateTime} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor='appUserId'>User Id</label>
                    <input id="appUserId" name="appUserId" type="text" className="form-control"
                        value={schedule.dateTime} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label htmlFor='animalId'>Animal Id</label>
                    <input id="animalId" name="animalId" type="text" className="form-control"
                        value={schedule.dateTime} onChange={handleChange} />
                </div>
                <div>
                    <button className="btn btn-success mr-2" type="submit">
                        {scheduleId ? 'Update Schedule' : 'Add Schedule'}
                    </button>
                    <Link className="btn btn-warning" to="/animal/schedule">
                        Cancel
                    </Link>
                </div>
            </form>
        </>
    );
}

export default ScheduleForm;