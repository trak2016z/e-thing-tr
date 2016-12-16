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
function sendThingData(idhash)
{
    var result = null;
    $.ajax({
        url: getMainPage() + "/deleteThing",
        type: "POST",
        data: idhash,
        async: false,
        dataType: "json",
        contentType: 'application/json',
        success: function (response) {
            result = response;
        },
        error: function (error)
        {
            result = error;
        }
    });
    return result;
}
//===========================================
function deleteThing()
{
    $('#deleteThingAcceptMessage')
            .modal({
                closable: false,
                onDeny: function () {
                    return false;
                },
                onApprove: function () {
                    
                    
                    data = window.location.href.split('/')[window.location.href.split('/').length - 1];
                    console.log(data);
                    var result = sendThingData(data);

                    if (result.responseText == "error")
                    {
                        
                        $('#thingDataErrorMessage').modal('show');
                    } else
                    {
                        console.log('safas');
                        window.location = getMainPage();
                    }
                }
            }).modal('show');

}