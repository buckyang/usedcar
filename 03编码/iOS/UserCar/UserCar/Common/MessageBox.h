//
//  MessageBox.h
//  UserCar
//
//  Created by 舒联勇 on 14-4-23.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  @brief 消息提示框
 */
@interface MessageBox : NSObject

/**
 *  @brief 消息提示
 *
 *  @param aMessage 提示内容
 */
+ (void)showMessage:(NSString*)aMessage;

/**
 *  @brief 加载提示
 */
+ (void)showLoading;
/**
 *  @brief 立即完成加载
 */
+ (void)hiddenLoading;

/**
 *  @brief 推迟完成加载
 */
+ (void)hiddenLoadingAfterDelay:(NSTimeInterval)delay;

@end
