

function validateRegisterForm(){
    let form, userid, firstName, lastName, email, password, errors;
    errors = [];
    form = document.forms["registerForm"];
    userid = form["userid"].value;
    firstName = form["firstName"].value;
    lastName = form["lastName"].value;
    email = form["email"].value;
    password = form["password"].value;

    if (userid.trim() ==="") {
        errors.push("No userid given");
        form["userid"].value = "";
        form["userid"].className = "form-group has-error";
    }
    else form["userid"].className = "form-group has-success";

    if (firstName.trim() === "") {
        errors.push("No first name given");
        form["firstName"].value = "";
        form["firstName"].className = "form-group has-error";
    }
    else form["firstName"].className = "form-group has-success";

    if (lastName.trim() === "") {
        errors.push("No last name given");
        form["lastName"].value = "";
        form["lastName"].className = "form-group has-error";
    }
    else form["lastName"].className = "form-group has-success";

    if (email.trim() === "") {
        errors.push("No email given");
        form["email"].value = "";
        form["email"].className = "form-group has-error";
    }
    else {
        let EMAIL_PATTERN = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!email.match(EMAIL_PATTERN)) {
            errors.push("Email is not valid");
            form["email"].value = "";
            form["email"].className = "form-group has-error";
        }
        else form["email"].className = "form-group has-success";
    }

    if (password.trim() === "") {
        errors.push("No password given");
        form["password"].value = "";
        form["password"].className = "has-error";
    }
    else form["password"].className = "form-group has-success";


    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0], errors);
        return false;
    } else {
        return true;
    }
}

function validateLogInForm(){
    let form, userid, password, errors;
    errors = [];
    form = document.forms["logInForm"];
    userid = form["userid"].value;
    password = form["password"].value;

    if (userid.trim() === "") {
        errors.push("No userid given");
        form["userid"].value = "";
        form["userid"].className = "has-error";
    } 
    else form["userid"].className = "has-success";
    
    if (password.trim() === "") {
        errors.push("No password given");
        form["password"].value = "";
        form["password"].className = "has-error";
    } 
    else form["password"].className = "has-success";

    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0], errors);
        return false;
    } else {
        createSuccesMessage(document.getElementsByTagName("form")[0],succes);
        return true;
    }
}

function validateAdminFilterForm(){
    let form, user, startDate, endDate,errors;
    errors = [];
    form = document.forms["adminFilterForm"];
    user = form["user"].value;
    startDate = form["startDate"].value;
    endDate = form["endDate"].value;

    if (user.trim() === "") {
        errors.push("No user given");
        form["user"].value = "";
        form["user"].className = "has-error";
    } 
    else form["user"].className = "has-success";
    
    if (startDate.trim() === "") {
        errors.push("No start date given");
        form["startDate"].value = "";
        form["startDate"].className = "has-error";
    } 
    else form["startDate"].className = "has-success";

    if (endDate.trim() === "") {
        errors.push("No end date given");
        form["endDate"].value = "";
        form["endDate"].className = "has-error";
    }
    else form["startDate"].className = "has-success";

    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0], errors);
        return false;
    } else {
        return true;
    }
}

function validateContactFilterForm(){
    let form, startDate, endDate,errors;
    errors = [];
    form = document.forms["contactFilterForm"];
    startDate = form["startDate"].value;
    endDate = form["endDate"].value;

    if (startDate.trim() === "") {
        errors.push("No start date given");
        form["startDate"].value = "";
        form["startDate"].className = "has-error";
    }
    else form["startDate"].className = "has-success";

    if (endDate.trim() === "") {
        errors.push("No end date given");
        form["endDate"].value = "";
        form["endDate"].className = "has-error";
    }
    else form["startDate"].className = "has-success";

    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0], errors);
        return false;
    } else {
        return true;
    }
}

function validateContactsForm(){
    let form, firstName, lastName, date,hour, gsm,email,errors;
    errors = [];
    form = document.forms["contactsForm"];
    firstName = form["firstName"].value;
    lastName = form["lastName"].value;
    date = form["date"].value;
    hour = form["hour"].value;
    gsm = form["gsm"].value;
    email = form["email"].value;

    if (firstName.trim() === "") {
        errors.push("No first name given");
        form["firstName"].value = "";
        form["firstName"].className = "form-group has-error";
    }
    else form["firstName"].className = "form-group has-success";

    if (lastName.trim() === "") {
        errors.push("No last name given");
        form["lastName"].value = "";
        form["lastName"].className = "form-group has-error";
    }
    else form["lastName"].className = "form-group has-success";

    if (date.trim() === "") {
        errors.push("No date given");
        form["date"].value = "";
        form["date"].className = "form-group has-error";
    }
    else form["date"].className = "form-group has-success";

    if (hour.trim() === "") {
        errors.push("No hour given");
        form["hour"].value = "";
        form["hour"].className = "form-group has-error";
    }
    else form["hour"].className = "form-group has-success";

    if (gsm.trim() === "") {
        errors.push("No phone number given");
        form["gsm"].value = "";
        form["gsm"].className = "form-group has-error";
    }
    else form["gsm"].className = "form-group has-success";
    
    if (email.trim() === "") {
        errors.push("No email given");
        form["email"].value = "";
        form["email"].className = "form-group has-error";
    }
    else {
        let EMAIL_PATTERN = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!email.match(EMAIL_PATTERN)) {
            errors.push("Email is not valid");
            form["email"].value = "";
            form["email"].className = "form-group has-error";
        }
        else form["email"].className = "form-group has-success";
    }

    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0], errors);
        return false;
    } else {
        return true;
    }
}

function validateRegisterTestForm(){
    let form, date,errors;
    errors = [];
    form = document.forms["registerTestForm"];
    date = form["date"].value;

    if (date.trim() === "") {
        errors.push("No date given");
        form["date"].value = "";
        form["date"].className = "has-error";
    }
    else form["date"].className = "has-success";

    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0], errors);
        return false;
    } else {
        return true;
    }
}

function validateReservationForm(){
    let form, date,hour,errors;
    errors = [];
    form = document.forms["registerTestForm"];
    date = form["date"].value;
    hour = form["hour"].value;

    if (date.trim() === "") {
        errors.push("No date given");
        form["date"].value = "";
        form["date"].className = "has-error";
    }
    else form["date"].className = "has-success";

    if (hour.trim() === "") {
        errors.push("No start hour given");
        form["hour"].value = "";
        form["hour"].className = "has-error";
    }
    else form["hour"].className = "has-success";

    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0], errors);
        return false;
    } else {
        return true;
    }
}

function createErrorMessage(element, errors){
    removeErrors()
    let errorDivs = document.getElementsByClassName("alert-danger"), ul;

    element.insertAdjacentHTML("beforebegin", "<div class=\"alert-danger\"><ul></ul></div>");
    ul = errorDivs[0].getElementsByTagName("ul")[0];
    for (let i = 0; i < errors.length; i++) {
        ul.insertAdjacentHTML("beforeend", "<li>" + errors[i] + "</li>");
    }
}

function removeErrors(){
    let errorDivs = document.getElementsByClassName("alert-danger");

    while(errorDivs[0]) {
        errorDivs[0].parentNode.removeChild(errorDivs[0]);
    }
}