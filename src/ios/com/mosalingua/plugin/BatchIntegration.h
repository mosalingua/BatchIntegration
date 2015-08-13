#import <Cordova/CDV.h>

@interface BatchIntegration : CDVPlugin

- (void)enablePushNotifications:(CDVInvokedUrlCommand*)command;

@end