
function getMainPage()
{
    pathArray = location.href.split('/');
    protocol = pathArray[0];
    host = pathArray[2];
    url = protocol + '//' + host;
    return url;
}


function sendNewPassword(userEmail)
{
    
    var result = null;
    $.ajax({
        url: getMainPage()+"rememberPassword",
        type: "PUT",
        data: userEmail,
        async: false,
        contentType: 'application/json',
        success: function (response)
        {
            result = response;
        },
        error: function (error)
        {
            result = error;
        }
    });
    return result;
}
function rememberMe()
{
    var email = $('#email').val();
    if (email == "")
        $('#remembermeErrorMessage').modal('show');
    else
    {
        var result = sendNewPassword(email);
        console.log(result);
        if (result == "message")
        {
            $('#email').val('');
            $('#remembermeSuccessMessage').modal('show');

        } else
            $('#remembermeErrorMessage').modal('show');
    }
    //alert(email);
}
