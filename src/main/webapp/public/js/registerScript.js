function sendUserData(userData)
{
    $.ajax({
        url: "http://localhost:8080/registerUser",
        type: "PUT",
        data: JSON.stringify(userData),
        dataType: "json",
        contentType: 'application/json',
        success: function (response) {
            return 1;
            //console.log(response);
        },
        error: function (error)
        {
            return 0;
            //console.log(error);
        }
    });
}
function getObjectUserFromForm(jquery)
{
    return $(jquery).serializeArray()
            .reduce(function (a, x) {
                a[x.name] = x.value;
                return a;
            }, {});
}
function checkUserDataFromForm(userData)
{
    if (userData["password"] && userData["repassword"] && userData["name"] &&
            userData["login"] && userData["email"])
    {

        if (userData["password"] == userData["repassword"])
        {
            return userData;
        } else
        {
            $('#registerDataErrorMessage').modal('show');
            return 0;
        }

    } else
    {
        $('#registerDataErrorMessage').modal('show');
        return 0;
    }
}
function checkAgreeTermsAndConditions()
{
    if ($("#userForm input[name=agreeTermsAndConditions]")[0].checked == false)
        return 0;
    else
        return 1;
}
function registerUser()
{
    if (checkAgreeTermsAndConditions())
    {
        //console.log('');
        var userData = getObjectUserFromForm('#userForm');
        //console.log(userData);
        if (checkUserDataFromForm(userData))
        {
            $('#registerAcceptMessage').modal('show');
            //delete userData["repassword"];
            //sendUserData(userData);
        }            
    }
    else
        $('#registerAgreeTermsMessage').modal('show');  
    
}
