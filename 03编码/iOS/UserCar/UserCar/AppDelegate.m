//
//  AppDelegate.m
//  UserCar
//
//  Created by 舒联勇 on 14-3-19.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "AppDelegate.h"

@implementation AppDelegate

#pragma mark ------------ Style

/**
 *  @brief 配置Navigation的样式
 */
- (void)configNavigationStyle
{
    //设置导航栏字体颜色
    [[UINavigationBar appearance] setTitleTextAttributes:@{NSForegroundColorAttributeName: [UIColor whiteColor]}];

    //设置导航的背景色
    [[UINavigationBar appearance] setBarTintColor:RGBColor(56, 104, 187)];
    
    [UINavigationBar appearance].backItem.backBarButtonItem.tintColor =[UIColor whiteColor];
    
}

/**
 *  @brief 设置Tabbar的样式
 */
- (void)configTabbarStyle
{
    
//    UITabBarController *tabController = (UITabBarController*)self.window.rootViewController;
//    tabController.tabBar.tintColor = [UIColor blueColor];
    
    //
    //    NSDictionary *dicTitle = @{NSForegroundColorAttributeName: [UIColor blackColor]};
    //    [[UITabBarItem appearance] setTitleTextAttributes:dicTitle forState:UIControlStateNormal];
    
    //设置选中时的背景色
    [[UITabBarItem appearance] setTitleTextAttributes:@{NSForegroundColorAttributeName: [UIColor blueColor]} forState:UIControlStateSelected];
}

#pragma mark  AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    // Override point for customization after application launch.
    [self configTabbarStyle];
    [self configNavigationStyle];
    
    
    return YES;
}
							
- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end
