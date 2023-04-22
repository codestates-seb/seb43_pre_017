const setCookie = (cookie_name, value, days) => {
  const exdate = new Date();
  exdate.setDate(exdate.getDate() + days);

  const cookie_value =
    escape(value) + (days == null ? "" : "; expires=" + exdate.toUTCString());
  document.cookie = cookie_name + "=" + cookie_value;
};
export default setCookie;
