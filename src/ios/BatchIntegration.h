#import <Cordova/CDV.h>

@interface BatchIntegration : CDVPlugin

- (void)enablePushNotifications:(CDVInvokedUrlCommand*)command;
- (void)togglePushNotifications:(CDVInvokedUrlCommand*)command;
- (void)changeLanguage:(CDVInvokedUrlCommand*)command;
- (void)getPushInstallationId:(CDVInvokedUrlCommand*)command;

@end