//
//  UIBarButtonItem+ImageTitle.h
//  UserCar
//
//  Created by 舒联勇 on 14-4-16.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <UIKit/UIKit.h>

//自定义BarButtonItem
@interface UIBarButtonItem (ImageTitle)

/**
 *  @brief 设置返回按扭样式
 *
 *  @param target 当前的ViewController
 *
 *  @return 返回按钮
 */
+ (UIBarButtonItem*)barBackWithTarget:(UIViewController*)target;

/**
 *  @brief 自定义BarButtonItem
 *
 *  @param target 当前的ViewController
 *  @param aSelector 响应事件
 *  @param aImage    图片
 *  @param aTitle    title
 *
 *  @return 自定义成功的BarButtonItem
 */
+ (UIBarButtonItem*)customBarWithTarget:(id)target withSelector:(SEL)aSelector withImage:(UIImage*)aImage withTitle:(NSString*)aTitle;

@end
