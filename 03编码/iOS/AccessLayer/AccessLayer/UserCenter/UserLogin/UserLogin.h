//
//  UserLogin.h
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "AccessBase.h"
#import "../../../../Entity/Entity/UserInfo.h"
#import "../../../../Entity/Entity/EntityBase.h"

@interface UserLogin : AccessBase

- (void)userLoginWithUserInfo:(UserInfo*)aUser withCallback:(HttpCallback)aCallback;

@end
