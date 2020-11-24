cordova.define("com.mosa.plugin.BatchIntegration.BatchIntegration", function(require, exports, module) {

    var BatchIntegration = {
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
        },

        enablePushNotifications: function (successCallback,errorCallback) {

            cordova.exec(
                successCallback,
                errorCallback,
                'BatchIntegration',
                'enablePushNotifications',
                []
            );
        },

        togglePushNotifications: function(isEnabled, successCallback, errorCallback) {
            cordova.exec(
                successCallback,
                errorCallback,
                'BatchIntegration',
                'togglePushNotifications',
                [{
                    "isEnabled": isEnabled
                }]
            );
        },

        getPushInstallationId: function(successCallback, errorCallback) {
            cordova.exec(
                successCallback,
                errorCallback,
                'BatchIntegration',
                'getPushInstallationId',
                []
            );
        }
    };

    module.exports = BatchIntegration;

});
