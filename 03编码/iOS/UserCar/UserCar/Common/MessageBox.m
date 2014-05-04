//
//  MessageBox.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-23.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "MessageBox.h"
#import "MBProgressHUD.h"

@implementation MessageBox


+ (void)showMessage:(NSString*)aMessage
{
    UIAlertView *box = [[UIAlertView alloc] initWithTitle:@"提示"
                                                  message:aMessage
                                                 delegate:nil
                                        cancelButtonTitle:@"确定"
                                        otherButtonTitles:nil];
    [box show];
}

#pragma mark --------------- 加载提示

static MBProgressHUD *loadingView = nil;
+ (void)showLoading
{
    if (loadingView)
    {
        [loadingView removeFromSuperview];
        loadingView = nil;
    }
    
    UIWindow *window = [[UIApplication sharedApplication].delegate window];
    loadingView = [[MBProgressHUD alloc] initWithView:window];
    [window addSubview:loadingView];
    loadingView.labelText = @"请稍等...";
    loadingView.delegate = (id<MBProgressHUDDelegate>)self;
    [loadingView show:YES];
}
+ (void)hiddenLoading
{
    [loadingView hide:YES];
}

+ (void)hiddenLoadingAfterDelay:(NSTimeInterval)delay
{
    loadingView.labelText = @"完成";
    [loadingView hide:YES afterDelay:2];
}

+ (void)hudWasHidden:(MBProgressHUD *)hud
{
    [hud removeFromSuperview];
    loadingView = nil;
}

@end
