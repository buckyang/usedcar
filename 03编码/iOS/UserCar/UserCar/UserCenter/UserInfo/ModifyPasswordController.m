//
//  ModifyPasswordController.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-30.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "ModifyPasswordController.h"
#import "../../../../AccessLayer/AccessLayer/UserCenter/UserInfo/UserInfoAccess.h"

@interface ModifyPasswordController ()

@property (weak, nonatomic) IBOutlet UIButton *btnModify;
@property (weak, nonatomic) IBOutlet UITextField *txtOldPwd;
@property (weak, nonatomic) IBOutlet UITextField *txtNewPwd;
@property (weak, nonatomic) IBOutlet UITextField *txtVerifyNewPwd;


@end

@implementation ModifyPasswordController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.btnModify.layer.cornerRadius = 4;
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (BOOL)verifyPassword
{
    BOOL ret = NO;
    if ([NSString isEmpty:self.txtOldPwd.text.trim]) {
        [MessageBox showMessage:@"请输入旧密码"];
    }
    else if ([NSString isEmpty:self.txtNewPwd.text.trim]) {
        [MessageBox showMessage:@"请输入新密码"];
    }
    else if (self.txtNewPwd.text.trim.length<6)
    {
        [MessageBox showMessage:@"密码不能少于6位"];
    }
    else if (![self.txtNewPwd.text.trim isEqualToString:self.txtVerifyNewPwd.text.trim])
    {
        [MessageBox showMessage:@"两次密码不一致"];
    }
    else
    {
        ret = YES;
    }
    
    return ret;
}

- (IBAction)click_btnModify:(id)sender
{
    if ([self verifyPassword]) {
        UserInfoAccess *access = [UserInfoAccess createInstance];
        [access modifyOldPassword:self.txtOldPwd.text.trim withNewPassword:self.txtNewPwd.text.trim withCallback:^(id info, HTTPAccessState isSuccess) {
            if (isSuccess==HTTPAccessStateSuccess) {
                [self.navigationController popViewControllerAnimated:YES];
            }
            else
            {
                EntityBase *error = info;
                [MessageBox showMessage:error.message];
            }
        }];
        
    }
}


/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
