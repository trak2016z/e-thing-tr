function getMainPage()
{
    pathArray = location.href.split('/');
    protocol = pathArray[0];
    host = pathArray[2];
    url = protocol + '//' + host;
    return url;
}
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

function checkLoggedUser()
{
    var loggedUser = sendUserLoggedData();

    if (loggedUser.status == 500)
    {
        console.log('no loged');
    } else
    {
        //var myValue = $(".large ui stackable inverted menu .ui container .large right ui stackable inverted menu"); 
        console.log(loggedUser);
        var MyDiv1 = document.getElementById('logedInfo').innerHTML = loggedUser.name;
        console.log(MyDiv1);
    }
}