//
//  ComfirmMessageBox.h
//  UserCar
//
//  Created by 舒联勇 on 14-4-30.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void(^ConfirmMessageBlock)(BOOL confirmed);

@interface ConfirmMessageBox : NSObject

/**
 *  @brief 确定消息提示
 *
 *  @param aMessage        消息提示信息
 *  @param confirmCallback 返回值
 *
 */
+ (void)confirmMessage:(NSString*)aMessage withCallback:(ConfirmMessageBlock)confirmCallback;

@end
