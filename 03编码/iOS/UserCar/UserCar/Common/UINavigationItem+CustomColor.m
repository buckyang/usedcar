//
//  UINavigationItem+CustomColor.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-22.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UINavigationItem+CustomColor.h"

@implementation UINavigationItem (CustomColor)

- (void)setButtonColor:(UIColor*)aColor
{
    self.leftBarButtonItem.tintColor = aColor;
    self.rightBarButtonItem.tintColor = aColor;
}

@end
