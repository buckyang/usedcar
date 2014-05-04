/**
//  UserLogin.h
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
 */

#import "../../AccessBase.h"
#import "../../../../Entity/Entity/UserInfo.h"
#import "../../../../Entity/Entity/EntityBase.h"

/**
 *  @brief 用户登录功能
 */
@interface UserLogin : AccessBase

/**
 *  @brief 用户登录
 *
 *  @param aUser     用户名
 *  @param aCallback 回调函数
 */
- (void)userLoginWithUserInfo:(UserInfo*)aUser withCallback:(HttpCallback)aCallback;



@end
