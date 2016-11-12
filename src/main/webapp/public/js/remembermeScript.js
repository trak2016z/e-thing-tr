
function sendNewPassword(userEmail)
{
    var result = null;
    $.ajax({
        url: "http://localhost:8080/rememberPassword",
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
        if (result=="message")
        {
            $('#email').val('');
            $('#remembermeSuccessMessage').modal('show');
            
        } else
            $('#remembermeErrorMessage').modal('show');
    }
    //alert(email);
}
