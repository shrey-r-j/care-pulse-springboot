import { createBrowserRouter } from "react-router-dom";
import PatientLayout from "./layouts/PatientLayout.jsx";
import AppointmentLayout from "./layouts/AppointmentLayout.jsx";
import PatientForm from "./pages/PatientForm.jsx";

const router = createBrowserRouter([
    {
        path : "/patient",
        element : <PatientLayout/>,
        children :[
            {
                path : "register",
                element : <PatientForm/>
            }
        ]
    },
    {
        path:"/appointment",
        element : <AppointmentLayout/>
    }
])

export default router;