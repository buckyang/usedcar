//
//  PasswordTextField.m
//  UserCar
//
//  Created by 舒联勇 on 14-6-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "PasswordTextField.h"

@implementation PasswordTextField

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

- (UIImage*)LeftImage
{
    return [UIImage imageNamed:(self.text.length>0)?@"imgLoginPwdH.png": @"imgLoginPwd.png"];
}

@end
