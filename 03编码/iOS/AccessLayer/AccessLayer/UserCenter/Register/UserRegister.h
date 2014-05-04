//
//  UserRegister.h
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-23.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "../../AccessBase.h"
#import "../../../../Entity/Entity/UserInfo.h"
#import "../../../../Entity/Entity/EntityBase.h"

@interface UserRegister : AccessBase

/**
 *  @brief 获取验证码
 *
 *  @param aUser     用户信息
 *  @param aCallback 返回值
 */
- (void)obtainCodeWithUserInfo:(UserInfo*)aUser withCallback:(HttpCallback)aCallback;

/**
 *  @brief 个人注册
 *
 *  @param aUser     用户信息
 *  @param aCallback 返回值
 */
- (void)registerIndividualUser:(UserInfo*)aUser withCallback:(HttpCallback)aCallback;

/**
 *  @brief 经销商注册
 *
 *  @param aUser     经销商信息
 *  @param aCallback 返回值
 */
- (void)reselleSignonUser:(UserInfo*)aUser withCallback:(HttpCallback)aCallback;

@end
