import React, { useState, useRef } from "react";
import Field from "../components/form/Field.jsx";
import axios from "axios";
import { toast } from "react-hot-toast";
import { ImagePlus, Upload, X, Info } from "lucide-react";

const validate = (fields) => {
  const errors = {};

  if (!fields.name.trim()) errors.name = "Name is required";

  if (!fields.email.trim()) {
    errors.email = "Email is required";
  } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(fields.email)) {
    errors.email = "Invalid email format";
  }

  if (!fields.password) {
    errors.password = "Password is required";
  } else if (fields.password.length < 6) {
    errors.password = "Password must be at least 6 characters";
  }

  if (!fields.age && fields.age !== 0) {
    errors.age = "Age is required";
  }

  if (!fields.gender) errors.gender = "Gender is required";

  if (!fields.address.trim()) errors.address = "Address is required";

  if (!fields.phone.trim()) errors.phone = "Phone is required";

  return errors;
};

const PatientForm = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    age: "",
    gender: "",
    birthDate: "",
    address: "",
    phone: "",
    identificationDocumentUrl: "",
    identificationType: "",
    identificationNumber: "",
  });
  const [errors, setErrors] = useState({});
  const [image, setImage] = useState(null);
  const fileInputRef = useRef(null);
  const types = ["AADHAR",
    "PASSPORT",
    "DRIVING_LICENSE",
    "BIRTH_CERTIFICATE"];

  const processFile = (file) => {
    if (file) {
      const validTypes = ["image/jpeg", "image/png", "image/gif", "image/webp"];
      const maxSize = 5 * 1024 * 1024;

      if (!validTypes.includes(file.type)) {
        toast.error("Please upload a valid image type");
        return;
      }
      if (file.size > maxSize) {
        toast.error("Image must be smaller");
        return;
      }

      const reader = new FileReader();
      reader.onloadend = () => {
        setImage({
          base64: reader.result.split(",")[1],
          contentType: file.type,
          preview: reader.result
        })
      }
      reader.readAsDataURL(file);
    }
  }

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    processFile(file);
  }

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validate(formData);
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }
    setErrors({});
    try {
      const payload = {
        ...formData,
        identificationDocumentUrl: image?.base64 ?? "",  // add this
      };
      const response = await axios.post("http://localhost:8080/api/patients", payload);
      if(response.status === 200){
        toast.success("Patient created Successfully!!")
      }
      else toast.success("Patient creation failed")
      console.log(response.data);
    }
    catch (error) {
      console.log(error);
    }
  };



  return (
    <div className="min-h-screen bg-gray-950 flex items-center justify-center px-4 py-12">
      <div className="w-full max-w-2xl">
        {/* Header */}
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-white">Patient Registration</h1>
          <p className="mt-1 text-sm text-gray-400">
            Fill in the details below to register a new patient.
          </p>
        </div>

        <form
          onSubmit={handleSubmit}
          noValidate
          className="bg-gray-900 border border-gray-800 rounded-2xl p-8 shadow-xl flex flex-col gap-6"
        >
          {/* Row: Name + Email */}
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
            <Field label="Full Name" name="name" placeholder="John Doe" handleChange={handleChange} formData={formData} errors={errors} />
            <Field label="Email" name="email" type="email" placeholder="john@example.com" handleChange={handleChange} formData={formData} errors={errors} />
          </div>

          {/* Password */}
          <Field label="Password" name="password" type="password" placeholder="Min. 6 characters" handleChange={handleChange} formData={formData} errors={errors} />

          {/* Row: Age + Gender */}
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
            <Field label="Age" name="age" type="number" placeholder="e.g. 28" handleChange={handleChange} formData={formData} errors={errors} />

            {/* Gender select */}
            <div className="flex flex-col gap-1">
              <label className="text-sm font-medium text-gray-300" htmlFor="gender">
                Gender
              </label>
              <select
                id="gender"
                name="gender"
                value={formData.gender}
                onChange={handleChange}
                className={`rounded-lg bg-gray-800 border px-4 py-2.5 text-sm text-white outline-none transition focus:ring-2 focus:ring-teal-500 ${errors.gender ? "border-red-500" : "border-gray-700"
                  }`}
              >
                <option value="" disabled>
                  Select gender
                </option>
                <option value="MALE">Male</option>
                <option value="FEMALE">Female</option>
                <option value="OTHER">Other</option>
              </select>
              {errors.gender && (
                <p className="text-xs text-red-400">{errors.gender}</p>
              )}
            </div>
          </div>

          {/* Birth Date */}
          <Field label="Birth Date (optional)" name="birthDate" type="date" handleChange={handleChange} formData={formData} errors={errors} />

          {/* Address */}
          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-300" htmlFor="address">
              Address
            </label>
            <textarea
              id="address"
              name="address"
              rows={3}
              placeholder="123 Main St, City, Country"
              value={formData.address}
              onChange={handleChange}
              className={`rounded-lg bg-gray-800 border px-4 py-2.5 text-sm text-white placeholder-gray-500 outline-none resize-none transition focus:ring-2 focus:ring-teal-500 ${errors.address ? "border-red-500" : "border-gray-700"
                }`}
            />
            {errors.address && (
              <p className="text-xs text-red-400">{errors.address}</p>
            )}
          </div>

          {/* Phone */}
          <Field label="Phone" name="phone" type="tel" placeholder="+1 234 567 8900" handleChange={handleChange} formData={formData} errors={errors} />

          <Field label="Identification Number" name="identificationNumber" placeholder="1234567890" handleChange={handleChange} formData={formData} errors={errors} />

          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-300" htmlFor="identificationType">
              Identification Type
            </label>
            <select
              id="identificationType"
              name="identificationType"
              value={formData.identificationType}
              onChange={handleChange}
              className={`rounded-lg bg-gray-800 border px-4 py-2.5 text-sm text-white outline-none transition focus:ring-2 focus:ring-teal-500 ${errors.gender ? "border-red-500" : "border-gray-700"
                }`}
            >
              <option value="">Select</option>
              {
                types.map((fruit, index) => (
                  <option key={index} value={fruit}>
                    {fruit}
                  </option>
                ))
              }
            </select>
          </div>


          <div whileHover={{ scale: 1.02 }} className="space-y-2">
            <label className="block text-sm font-medium text-white">
              Upload Image
            </label>
            <div className="relative group">
              <input
                type="file"
                ref={fileInputRef}
                onChange={handleFileChange}
                accept="image/*"
                className="hidden"
              />
              {image ? (
                <div
                  initial={{ opacity: 0 }}
                  animate={{ opacity: 1 }}
                  className="relative overflow-hidden rounded-xl border-2 border-gray-200"
                >
                  <img
                    src={image.preview}
                    alt="Preview"
                    className="w-full h-72 object-cover transition-all duration-300 group-hover:scale-105"
                  />
                  <div className="absolute inset-0 bg-gradient-to-t from-black/60 to-transparent opacity-0 group-hover:opacity-100 transition-opacity flex items-end p-6">
                    <span className="text-white font-medium">Click to change image</span>
                  </div>
                  <button
                    type="button"
                    onClick={() => setImage(null)}
                    className="absolute top-4 right-4 bg-white/90 p-2 rounded-full shadow-lg hover:bg-red-100 transition-colors"
                  >
                    <X className="text-gray-700 hover:text-red-600" size={18} />
                  </button>
                </div>
              ) : (
                <div
                  whileTap={{ scale: 0.98 }}
                  onClick={() => fileInputRef.current.click()}
                  className="border-2 border-dashed border-gray-300 rounded-xl h-72 flex flex-col items-center justify-center cursor-pointer hover:border-blue-400 bg-gradient transition-all"
                >
                  <div className="text-center space-y-4 p-6">
                    <div className="mx-auto w-14 h-14 bg-blue-100 rounded-full flex items-center justify-center">
                      <ImagePlus className="text-blue-600" size={28} />
                    </div>
                    <div>
                      <p className="text-gray-700 font-medium">Drag & drop your image here</p>
                      <p className="text-sm text-gray-500 mt-1">
                        or click to browse (16:9 ratio recommended)
                      </p>
                    </div>
                    <div className="inline-flex items-center px-3 py-1.5 rounded-full bg-blue-50 text-blue-600 text-xs font-medium">
                      Max 5MB • JPG, PNG, WEBP
                    </div>
                  </div>
                </div>
              )}
          </div>
      </div>


      {/* Divider */}
      <hr className="border-gray-800" />

      {/* Submit */}
      <button
        type="submit"
        className="w-full rounded-lg bg-teal-500 hover:bg-teal-400 active:bg-teal-600 transition-colors px-6 py-3 text-sm font-semibold text-gray-950 shadow-md"
      >
        Register Patient
      </button>
    </form>
      </div >
    </div >
  );
};

export default PatientForm;