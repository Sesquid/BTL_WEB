export const isNumeric = (str) => {
  if (typeof str != "string") return false;
  return !isNaN(str) && !isNaN(parseFloat(str)) && (parseFloat(str) > 0.0)
}

export const isPhoneNumber = (phone) => {
  const regex = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
  return regex.test(phone)
}

export const isValidate = (available_area, currentArea) => {
  // console.log((parseFloat(currentArea)))
  if (isNumeric(currentArea) && parseFloat(currentArea) <= parseFloat(available_area)) {
    return true
  }
  return false;
}

export const convertToDateForm = (date = "") => {
  const month = date.substring(0, 2)
  const day = date.substring(3, 5)
  const year = date.substring(6, 10)
  return month + "/" + day + "/" + year
}

export const convertToDateForm2 = (date = "") => {
  const dateArray = new Date(date.split(/[ ,]+/, 3).join(" ")).toLocaleDateString('en-US');
  const [day, month, year] = dateArray.split("/")
  const res = [year, String(month).padStart(2, '0'), String(day).padStart(2, '0')].join("-")
  return res
}

export const convertToDateForm3 = (date = "") => {
  const [month, day, year] = date.split("-")
  const res = [year, String(month).padStart(2, '0'), String(day).padStart(2, '0')].join("-")
  return res
}