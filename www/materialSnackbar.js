var snackbar = function(text, duration, button, successCallback){

    cordova.exec(
            successCallback, // success callback function
            null, // error callback function
            'MaterialSnackbar', // mapped to our native Java class called "CalendarPlugin"
            'materialSnackbar', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": text,
                "duration": duration,
                "button": button
            }]
        );
}
module.exports = snackbar;
