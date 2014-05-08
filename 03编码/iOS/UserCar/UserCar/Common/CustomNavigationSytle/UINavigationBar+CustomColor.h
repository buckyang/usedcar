//
//  UINavigationBar+CustomColor.h
//  UserCar
//
//  Created by 舒联勇 on 14-4-16.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <UIKit/UIKit.h>

//自定义NavigationBar颜色
@interface UINavigationBar (CustomColor)

/**
 *  @brief 设置Title颜色
 *
 *  @param aColor 颜色值
 */
- (void)setTitleColor:(UIColor*)aColor;

/**
 *  @brief 设置默认的Title颜色
 */
- (void)configDefaultTitleColor;

@end
