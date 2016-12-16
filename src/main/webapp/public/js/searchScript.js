/**
 *
 * @author prographer
 */
//===========================================
function search()
{

    $("#searchButton").click(function () {
        if ($("#searchInput").val() != '')
            document.location.href = getMainPage() + "/search/" + $("#searchInput").val();
    });
}
//===========================================
