/**
 *
 * @author prographer
*/
//===========================================
function getMainPage()
{
    pathArray = location.href.split('/');
    protocol = pathArray[0];
    host = pathArray[2];
    url = protocol + '//' + host;
    return url;
}
//===========================================
function sendUserLoggedData()
{
    var result = null;
    //console.log(getMainPage()+"/getLogedUser");
    $.ajax({
        url: getMainPage() + "/getLogedUser",
        type: "GET",
        async: false,
        dataType: "json",
        contentType: 'application/json',
        success: function (response) {
            console.log(response);
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
//===========================================
function checkLoggedUser()
{
    var result = isLogged();
    if(result == "1")
    {
       //panel user
    }
    else 
    {
        $("#logoutInfo").hide();
    }
    //console.log(result);
}
function isLogged()
{
    var result = null;
    $.ajax({
        url: getMainPage() + "/isLogedUser",
        type: "GET",
        async: false,
        dataType: "json",
        contentType: 'application/json',
        success: function (response) {
            //console.log(response);
            result = response;
        },
        error: function (error)
        {
            result = error;
        }
    });
    return result;
}
