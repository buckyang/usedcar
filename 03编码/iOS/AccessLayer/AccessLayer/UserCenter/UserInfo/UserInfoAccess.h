//
//  UserInfoAccess.h
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-30.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "../../AccessBase.h"
#import "../../../../Entity/Entity/UserInfo.h"
#import "../../../../Entity/Entity/EntityBase.h"

@interface UserInfoAccess : AccessBase

/**
 *  @brief 修改密码
 *
 *  @param aOldPwd   旧密码
 *  @param aNewPwd   新密码
 *  @param aCallback 返回值
 */
- (void)modifyOldPassword:(NSString*)aOldPwd withNewPassword:(NSString*)aNewPwd withCallback:(HttpCallback)aCallback;

@end
