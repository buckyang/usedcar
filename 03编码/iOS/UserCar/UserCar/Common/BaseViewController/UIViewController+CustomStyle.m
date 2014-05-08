//
//  UIViewController+CustomStyle.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-29.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UIViewController+CustomStyle.h"

@implementation UIViewController (CustomStyle)

/**
 *  @brief 配置返回按钮
 */
- (void)configBackButton
{
    UIBarButtonItem *backItem = [[UIBarButtonItem alloc] initWithTitle:@"返回" style:UIBarButtonItemStylePlain target:nil action:nil];
    [backItem setBackButtonBackgroundImage:[UIImage imageNamed:@"img_Back.png"] forState:UIControlStateNormal barMetrics:UIBarMetricsDefault];
    [backItem setTitleTextAttributes:@{NSForegroundColorAttributeName: [UIColor whiteColor]} forState:UIControlStateNormal];
    [backItem setBackButtonTitlePositionAdjustment:UIOffsetMake(5, 0) forBarMetrics:UIBarMetricsDefault];
    self.navigationItem.backBarButtonItem = backItem;
}

@end
