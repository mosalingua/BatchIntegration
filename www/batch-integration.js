
var batchIntegration = {
    changeLanguage: function(language, successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'BatchIntegration', // mapped to our native Java class
            'changeLanguage', // with this action name
            [{ // and this array of custom arguments to create our entry
                "language": language
            }]
        );
    }
};

module.exports = batchIntegration;
