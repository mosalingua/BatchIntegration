#import "BatchIntegration.h"
#import <Cordova/CDV.h>
#import <Batch/Batch.h>

@implementation BatchIntegration

- (void)enablePushNotifications:(CDVInvokedUrlCommand*)command {

    CDVPluginResult* pluginResult = nil;

    @try {

        //avoid doing the setup more than once
        if (![[NSUserDefaults standardUserDefaults] boolForKey:@"HasDoneSetupBash"])
        {
         // Start Batch SDK.
         [BatchPush setupPush];
         [Batch startWithAPIKey:@"BATCH_API_KEY"];//this is replaced at build time
         // Register for push notifications
         [BatchPush registerForRemoteNotifications];

         [[NSUserDefaults standardUserDefaults] setBool:YES forKey:@"HasDoneSetupBash"];
         [[NSUserDefaults standardUserDefaults] synchronize];
        }

        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:(true)];
    }
    @catch (NSException *exception) {
         pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:(false)];
    }
    @finally {
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }

}

@end