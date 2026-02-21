import { Outlet } from "react-router-dom";

const AppointmentLayout = ()=>{
    return(
        <>
            <h1 class = "text-shadow-amber-300">Appointment</h1>
            <Outlet/>
        </>
    )
}
export default AppointmentLayout;