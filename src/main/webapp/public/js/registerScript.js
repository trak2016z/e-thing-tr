
function getMainPage()
{
    pathArray = location.href.split('/');
    protocol = pathArray[0];
    host = pathArray[2];
    url = protocol + '//' + host;
    return url;
}
function sendUserData(userData)
{
    var result = null;
    $.ajax({
        url: getMainPage()+"/registerUser",
        type: "PUT",
        data: JSON.stringify(userData),
        async: false,
        dataType: "json",
        contentType: 'application/json',
        success: function (response) {
            result = response;
        },
        error: function (error)
        {
            result = error;
            //console.log(error);
        }
    });
    return result;
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
            $('#registerAcceptMessage')
                    .modal({
                        closable: false,
                        onDeny: function () {
                            //window.alert('Wait not yet!');
                            return false;
                        },
                        onApprove: function () {
                            delete userData["repassword"];
                            if (sendUserData(userData)=="error")
                                $('#registerDataErrorMessage').modal('show');
                            else 
                            {
                                document.getElementById("userForm").reset();
                                $('#registerSuccessMessage').modal('show');
                            }
                        }
                    }).modal('show');
        }
    } else
        $('#registerAgreeTermsMessage').modal('show');
    

}
