//
//  AppDelegate.m
//  UserCar
//
//  Created by 舒联勇 on 14-3-19.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "AppDelegate.h"

@interface AppDelegate ()<UITabBarControllerDelegate,UIViewControllerAnimatedTransitioning>
{
    BOOL isLeft;
}

@end

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
    
    UITabBarController *tabController = (UITabBarController*)self.window.rootViewController;
//    tabController.tabBar.tintColor = [UIColor blueColor];
//    tabController.tabBar.backgroundColor = [UIColor colorWithWhite:0.7 alpha:1];
//    tabController.tabBar.backgroundImage = [UIImage imageNamed:@"imgTabBarBG.png"];
    
    //
    //    NSDictionary *dicTitle = @{NSForegroundColorAttributeName: [UIColor blackColor]};
    //    [[UITabBarItem appearance] setTitleTextAttributes:dicTitle forState:UIControlStateNormal];
    
    //设置选中时的背景色
    [[UITabBarItem appearance] setTitleTextAttributes:@{NSForegroundColorAttributeName: RGBColor(58, 107, 189)} forState:UIControlStateSelected];
    tabController.tabBar.selectedImageTintColor = RGBColor(58, 107, 189);
    
    [[UITabBarItem appearance] setTitleTextAttributes:@{NSForegroundColorAttributeName: RGBColor(55, 55, 55)}
                                             forState:UIControlStateNormal];   
    for (UITabBarItem *tbi in tabController.tabBar.items) {
        [tbi setTitlePositionAdjustment:UIOffsetMake(0, -2)];
        tbi.image = [tbi.image imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
    }
}


#pragma mark ---------------- UITabBarControllerDelegate
- (id <UIViewControllerAnimatedTransitioning>)tabBarController:(UITabBarController *)tabBarController
            animationControllerForTransitionFromViewController:(UIViewController *)fromVC
                                              toViewController:(UIViewController *)toVC
{
    NSUInteger oldIndex = [tabBarController.viewControllers indexOfObject:fromVC];
    NSUInteger newIndex = [tabBarController.viewControllers indexOfObject:toVC];
    
    //从左到右
    if ((oldIndex+1==newIndex)|| (oldIndex==tabBarController.viewControllers.count-1 && newIndex==0)) {
        isLeft = NO;
    }
    else
    {
        isLeft = YES;
    }
    
    return self;
}

#pragma mark --------------- UIViewControllerAnimatedTransitioning
-(void)animateTransition:(id<UIViewControllerContextTransitioning>)transitionContext {
    //Get references to the view hierarchy
    UIView *containerView = [transitionContext containerView];
    UIViewController *fromViewController = [transitionContext viewControllerForKey:UITransitionContextFromViewControllerKey];
    UIViewController *toViewController = [transitionContext viewControllerForKey:UITransitionContextToViewControllerKey];
    
    //Insert 'to' view into the hierarchy
    [containerView insertSubview:toViewController.view belowSubview:fromViewController.view];
    
    
    if (isLeft) {
        [self setAnchorPoint:CGPointMake(0.0, 0.5) forView:toViewController.view];
    }
    else
    {
        [self setAnchorPoint:CGPointMake(1.0, 0.5) forView:toViewController.view];
    }
    
    [containerView bringSubviewToFront:toViewController.view];
    
    //Animate the transition, applying transform to 'from' view and removing it from 'to' view
    [UIView animateWithDuration:[self transitionDuration:transitionContext] animations:^{
        if (isLeft) {
            [self setAnchorPoint:CGPointMake(1.0, 0.5) forView:toViewController.view];
        }
        else
        {
            [self setAnchorPoint:CGPointMake(0.0, 0.5) forView:toViewController.view];
        }
    } completion:^(BOOL finished) {
        //Reset z indexes (otherwise this will affect other transitions)
        [transitionContext completeTransition:YES];
    }];
}

-(NSTimeInterval)transitionDuration:(id<UIViewControllerContextTransitioning>)transitionContext {
    return 0.5;
}

- (void)setAnchorPoint:(CGPoint)anchorPoint forView:(UIView *)view {
    CGPoint oldOrigin = view.frame.origin;
    view.layer.anchorPoint = anchorPoint;
    CGPoint newOrigin = view.frame.origin;
    
    CGPoint transition;
    transition.x = newOrigin.x - oldOrigin.x;
    transition.y = oldOrigin.y - oldOrigin.y;
    
    view.center = CGPointMake (view.center.x - transition.x, view.center.y - transition.y);
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
