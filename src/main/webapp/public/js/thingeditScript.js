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
function sendThingData(thingData)
{
    console.log(thingData);
    var result = null;
    $.ajax({
        url: getMainPage() + "/editThing",
        type: "POST",
        data: JSON.stringify(thingData),
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
function getObjectThingFromForm(jquery)
{
    return $(jquery).serializeArray()
            .reduce(function (a, x) {
                a[x.name] = x.value;
                return a;
            }, {});
}
//===========================================
function checkThingDataFromForm(thingData)
{
    //console.log(thingData);
    if (thingData.name == "" || thingData.access == "" ||
            thingData.status == "" || thingData.url == "" ||
            thingData.thingtype == "")
    {
        return 0;
    } else
        return 1;
}
//===========================================
function deleteLastPositiveFeature()
{
    $('#positiveContener').children().last().remove();
}
function deleteLastNeutralFeature()
{
    $('#neutralContener').children().last().remove();
}
function deleteLastNegativeFeature()
{
    $('#negativeContener').children().last().remove();
}
//===========================================
function addPositiveFeature()
{
    $('#positiveContener').append('\
    <div class="inline fields"><i class="minus icon"></i><input class="green" type="text" name="nameFeature" placeholder="name"/>\n\
    </div>');

}
function addNeutralFeature()
{
    $('#neutralContener').append('\
    <div class="inline fields"><i class="minus icon"></i><input type="text" name="nameFeature" placeholder="name"/>\n\
    </div>');
}
function addNegativeFeature()
{
    $('#negativeContener').append('\
    <div class="inline fields"><i class="minus icon"></i><input type="text" name="nameFeature" placeholder="name"/>\n\
    </div>');
}
//===========================================
function getFeatures()
{
    var featuresList = new Array();
    var positives = $("#positiveContener :input");
    for (i = 0; i < positives.length; i++) {
        if (positives[i].value != '')
        {
            var positive = {name: positives[i].value, effect: 'POS'};
            featuresList.push(positive);
        }
    }
    var neutrals = $("#neutralContener :input");
    for (i = 0; i < neutrals.length; i++) {
        if (neutrals[i].value != '')
        {
            var neutral = {name: neutrals[i].value, effect: 'NUT'};
            featuresList.push(neutral);
        }
    }
    var negatives = $("#negativeContener :input");
    for (i = 0; i < negatives.length; i++) {
        if (negatives[i].value != '')
        {
            var negative = {name: negatives[i].value, effect: 'NEU'};
            featuresList.push(negative);
        }
    }
    return featuresList;
}
//===========================================
function editThing()
{
    var thing = getObjectThingFromForm('#thingForm');
    var thingType = document.getElementById('thingType');
    var thingTypeText = thingType.options[thingType.selectedIndex].text;
    thing.thingtype = {name: thingTypeText};
    var thingStatus = document.getElementById('thingStatus');
    thing.status = thingStatus.options[thingStatus.selectedIndex].text;
    if (!("access" in thing)) {
        thing.access = 'off';
    }
    thing.idhash = url.split('/').pop();
    if (checkThingDataFromForm(thing))
    {
        data = {ethingFeatures: getFeatures(), ethingThing: thing};
        var result = sendThingData(data);
        if (result.responseText == "error")
        {
            $('#thingDataErrorMessage').modal('show');
        } else
        {
            $('#newThingMessage').modal('show');
            document.getElementById("thingForm").reset();
        }
    } else
    {
        $('#thingDataErrorMessage').modal('show');
    }
}
