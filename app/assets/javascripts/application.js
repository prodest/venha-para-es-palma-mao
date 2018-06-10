//= require jquery
//= require jquery_ujs
// external javascripts
//= require bootstrap/dist/js/bootstrap.min
// --- Select2
//= require select2/dist/js/select2
// --- Sweet Alert
//= require sweetalert/dist/sweetalert.min.js

function hasNumber(string) {
  return /\d/.test(string);
}

function isObjectID(string){
  return string.match(/^[0-9a-fA-F]{24}$/);
}
