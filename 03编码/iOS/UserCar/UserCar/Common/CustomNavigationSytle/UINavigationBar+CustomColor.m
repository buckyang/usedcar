//
//  UINavigationBar+CustomColor.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-16.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UINavigationBar+CustomColor.h"

@implementation UINavigationBar (CustomColor)

- (void)setTitleColor:(UIColor*)aColor
{
    NSDictionary *dict = [NSDictionary dictionaryWithObjects:@[aColor
                                                               ,aColor
                                                               ,[NSValue valueWithUIOffset:UIOffsetMake(0, 0)]]
                                                     forKeys:@[NSForegroundColorAttributeName
                                                               ,NSShadowAttributeName
                                                               ,NSShadowAttributeName]];
    self.titleTextAttributes = dict;
}


- (void)configDefaultTitleColor
{
    UIColor *textColor = [UIColor whiteColor];
    [self setTitleColor:textColor];
}

@end
