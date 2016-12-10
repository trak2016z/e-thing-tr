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
function sendUserData(userData)
{
    var result = null;
    $.ajax({
        url: getMainPage() + "/editUser",
        type: "POST",
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
//===========================================
function getObjectUserFromForm(jquery)
{
    return $(jquery).serializeArray()
            .reduce(function (a, x) {
                a[x.name] = x.value;
                return a;
            }, {});
}
//===========================================
function checkUserDataFromForm(userData)
{
    if ((userData["password"] && userData["repassword"]) || userData["name"])
    {

        if (userData["password"] == userData["repassword"])
        {
            return userData;
        } else
        {
            $('#editUserDataErrorMessage').modal('show');
            return 0;
        }

    } else
    {
        $('#editUserDataErrorMessage').modal('show');
        return 0;
    }
}
//===========================================
function editUser()
{

    var userData = getObjectUserFromForm('#userForm');
    if (checkUserDataFromForm(userData))
    {
        $('#editUserAcceptMessage')
                .modal({
                    closable: false,
                    /*onDeny: function () {
                        $('#editUserAcceptMessage').hide();
                        //alert('');
                        return false;
                    },*/
                    onApprove: function () {
                        delete userData["repassword"];
                        var result = sendUserData(userData);
                        if (result.responseText == "error")
                            $('#editUserDataErrorMessage').modal('show');
                        else
                        {
                            document.getElementById("userForm").reset();
                            $('#editUserSuccessMessage').modal('show');
                            location.reload(); 
                        }
                    }
                }).modal('show');
    }


}
