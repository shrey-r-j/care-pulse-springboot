import { createBrowserRouter } from "react-router-dom";
import PatientLayout from "./layouts/PatientLayout.jsx";
import AppointmentLayout from "./layouts/AppointmentLayout.jsx";

const router = createBrowserRouter([
    {
        path : "/patient",
        element : <PatientLayout/>
    },
    {
        path:"/appointment",
        element : <AppointmentLayout/>
    }
])

export default router;