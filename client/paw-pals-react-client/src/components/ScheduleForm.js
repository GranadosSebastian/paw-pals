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

    //useEffect
    //handle change
    //handle submit
    //add
    //update

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
                    <input id="dateTime" name="dateTime" type="text" className="form-control"></input>
                </div>
                <div className="form-group">
                    <label htmlFor='appUserId'>User Id</label>
                    <input id="appUserId" name="appUserId" type="text" className="form-control"></input>
                </div>
                <div className="form-group">
                    <label htmlFor='animalId'>Animal Id</label>
                    <input id="animalId" name="animalId" type="text" className="form-control"></input>
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