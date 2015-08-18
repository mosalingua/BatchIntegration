#import <Cordova/CDV.h>

@interface BatchIntegration : CDVPlugin

- (void)enablePushNotifications:(CDVInvokedUrlCommand*)command;
- (void)changeLanguage:(CDVInvokedUrlCommand*)command;

@end