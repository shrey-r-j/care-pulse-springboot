import { Outlet } from "react-router-dom"

export default function PatientLayout(){
  return (
    <div>
        <h1 class = "text-teal-500">Patient</h1>
        <Outlet/>
    </div>
  )
}
