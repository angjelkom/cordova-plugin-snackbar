var snackbar = function(text, duration, successCallback, errorCallback){

    cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'MaterialSnackbar', // mapped to our native Java class called "CalendarPlugin"
            'materialSnackbar', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": text,
                "duration": duration
            }]
        );
}
module.exports = snackbar;
