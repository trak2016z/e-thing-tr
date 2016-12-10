/**
 *
 * @author prographer
*/
//===========================================
function search()
{
    $("#searchButton").click(function () {
        document.location.href = getMainPage() + "/search/" + $("#searchInput").val();
    });
}
//===========================================
function searchTextInPageData()
{
    
}
