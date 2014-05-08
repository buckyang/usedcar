//
//  UserInfoAccess.m
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-30.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UserInfoAccess.h"

@implementation UserInfoAccess

- (void)modifyOldPassword:(NSString*)aOldPwd withNewPassword:(NSString*)aNewPwd withCallback:(HttpCallback)aCallback
{
    NSMutableDictionary *parameters = [NSMutableDictionary dictionary];
    parameters[@"oldPWD"] = aOldPwd;
    parameters[@"newPWD"] = aNewPwd;
    parameters[@"confirmPWD"]=aNewPwd;
    
    [self accessURL:@"account/updatePassword.json" withParameters:parameters withCallback:^(id info, HTTPAccessState isSuccess) {
        aCallback(info,isSuccess);
    }];
}


@end
