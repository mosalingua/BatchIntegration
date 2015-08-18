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

- (void)changeLanguage:(CDVInvokedUrlCommand*)command {

    CDVPluginResult* pluginResult = nil;
    @try {
        NSDictionary *langObject = [command.arguments  objectAtIndex:0];
        BatchUserProfile *userProfile = [Batch defaultUserProfile];

        if (userProfile!=nil && langObject!=nil) {

            //parse the arguments
            NSString *languageAndRegion = [langObject valueForKey:@"language"];
            if(languageAndRegion!=nil) {
                NSArray *parts = [languageAndRegion componentsSeparatedByString:@"_"];
                if(parts.count==2) {
                    NSString *language = [parts objectAtIndex:0];
                    NSString *region = [parts objectAtIndex:1];
                    // Use the user profile to set custom language and data
                    userProfile.language = language; // Language must be 2 chars, lowercase, ISO 639 formatted
                    userProfile.region = region; // Region must be 2 chars, uppercase, ISO 3166 formatted

                    //Everything parsed OK
                    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:(true)];
                    NSLog(@"Successfully changed Batch language to %@",language);
                }
                //something is wrong
                else {
                    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:(false)];
                }
            }
            else {
                //something is wrong
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:(false)];
            }

        }
        else {
            //something is wrong
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:(false)];
        }
    }
    @catch (NSException *exception) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:(false)];
        NSLog(@"Unable to change Batch language: %@",exception.description);
    }
    @finally {
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }

}

@end