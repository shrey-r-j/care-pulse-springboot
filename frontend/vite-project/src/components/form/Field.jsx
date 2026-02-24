const Field = ({
  label,
  name,
  type = "text",
  placeholder,
  children,
  handleChange,
  formData,
  errors
}) => {
  return (
    <div className="flex flex-col gap-1">
      <label className="text-sm font-medium text-gray-300" htmlFor={name}>
        {label}
      </label>

      {children ?? (
        <input
          id={name}
          name={name}
          type={type}
          placeholder={placeholder}
          value={formData[name]}
          onChange={handleChange}
          className={`rounded-lg bg-gray-800 border px-4 py-2.5 text-sm text-white placeholder-gray-500 outline-none transition focus:ring-2 focus:ring-teal-500 ${
            errors?.[name] ? "border-red-500" : "border-gray-700"
          }`}
        />
      )}

      {errors?.[name] && (
        <p className="text-xs text-red-400">{errors[name]}</p>
      )}
    </div>
  );
};

export default Field;