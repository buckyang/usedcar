//
//  UserLogin.m
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UserLogin.h"

@implementation UserLogin

- (void)userLoginWithUserInfo:(UserInfo *)aUser withCallback:(HttpCallback)aCallback
{
    [self.httpClient POST:@"login.json"
               parameters:@{@"phoneOrEmail": aUser.userName,@"password":aUser.password}
                  success:^(AFHTTPRequestOperation *operation, id responseObject) {
                      
                      EntityBase *base = [[EntityBase alloc] initWithDictionary:responseObject];
                      aCallback(base,HTTPAccessStateSuccess);
                      
                  }
                  failure:^(AFHTTPRequestOperation *operation, NSError *error) {
                      INFO(@"登录失败：%@",error);
                  }];
}

@end
