//
//  ComfirmMessageBox.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-30.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "ConfirmMessageBox.h"

@interface ConfirmMessageBox ()

@property (nonatomic,strong) ConfirmMessageBlock block;
@property (nonatomic,strong) id alertEntity;

@end

@implementation ConfirmMessageBox


- (void)confirmMessage:(NSString*)aMessage
{
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示"
                                                    message:aMessage
                                                   delegate:self
                                          cancelButtonTitle:@"取消"
                                          otherButtonTitles:@"确定", nil];
    [alert show];
}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (buttonIndex==alertView.cancelButtonIndex) {
        self.block(NO);
    }
    else
    {
        self.block(YES);
    }
    self.alertEntity = nil;
}

#pragma mark --------------- 确认提示

+ (void)confirmMessage:(NSString*)aMessage withCallback:(ConfirmMessageBlock)confirmCallback
{
    ConfirmMessageBox *confirmObj = [ConfirmMessageBox createInstance];
    confirmObj.block = confirmCallback;
    confirmObj.alertEntity = confirmObj;
    [confirmObj confirmMessage:aMessage];
    confirmObj = nil;
}

@end
