//
//  LoginViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-3-19.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "LoginViewController.h"
#import "../../../../Entity/Entity/UserInfo.h"
#import "../../../../AccessLayer/AccessLayer/UserCenter/UserLogin/UserLogin.h"

@interface LoginViewController ()

@property (weak, nonatomic) IBOutlet UITextField *txtUserName;
@property (weak, nonatomic) IBOutlet UITextField *txtPassword;
@property (weak, nonatomic) IBOutlet UIButton *btnLogin;

@property (strong, nonatomic) IBOutlet UIView *vOtherFunction;
@property (strong, nonatomic) IBOutlet UIView *vFoot;

@end

@implementation LoginViewController

static NSString *UserName = @"UserName";
static NSString *Password = @"Password";

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

#pragma mark --------------- 设置样式 

/**
 *  @brief 设置UI样式，如文框
 */
- (void)setUIStyle
{
    [self.vFoot addObserver:self forKeyPath:@"frame" options:NSKeyValueObservingOptionNew|NSKeyValueObservingOptionOld context:@"vfoot"];
}

//设置下面找回密码和注册的位置
-(void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context
{
    if([(__bridge NSString*)context isEqualToString:@"vfoot"])
    {
        CGRect vOtherFunctionFrame = self.vOtherFunction.frame;
        vOtherFunctionFrame.origin.y = self.vFoot.frame.size.height-vOtherFunctionFrame.size.height;
        self.vOtherFunction.frame = vOtherFunctionFrame;
    }
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.btnLogin.userInteractionEnabled = YES;
    self.btnLogin.layer.cornerRadius = 4;

    NSString *userName = [[NSUserDefaults standardUserDefaults] stringForKey:UserName];
    NSString *password = [[NSUserDefaults standardUserDefaults] stringForKey:Password];
    if (![NSString isEmpty:UserName]) {
        self.txtUserName.text = userName;
        self.txtPassword.text = password;
    }
    [self setUIStyle];
    
    
//    CGRect vfootFrame = self.vFoot.frame;
//    vfootFrame.size.height = self.view.bounds.size.height-vfootFrame.origin.y;
//    self.vFoot.frame = vfootFrame;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)click_btnCancel:(id)sender {
    [self dismissViewControllerAnimated:YES completion:^{
        
    }];
}

/**
 *  @brief 判断输入是否正确
 *
 *  @return 判断是否正确
 */
- (BOOL)verifyInput
{
    BOOL ret = YES;
    
    if ([NSString isEmpty:self.txtUserName.text.trim]) {
        ret = NO;
        [MessageBox showMessage:@"请输入用户名"];
    }
    else if ([NSString isEmpty:self.txtPassword.text.trim])
    {
        ret = NO;
        [MessageBox showMessage:@"请输入密码"];
    }
    
    return ret;
}

//登录
- (IBAction)click_btnLogin:(id)sender
{
    if (![self verifyInput]) {
        return;
    }
    
    UserInfo *login = [UserInfo createInstance];
    login.userName = self.txtUserName.text.trim;
    login.password = self.txtPassword.text.trim;
    
    
    
    UserLogin *access = [UserLogin createInstance];
    
    [MessageBox showLoading];
    [access userLoginWithUserInfo:login withCallback:^(id info, HTTPAccessState isSuccess) {
        [MessageBox hiddenLoading];
        if (isSuccess==HTTPAccessStateSuccess) {
            [[UserInfo shareInstance] reflectDataFromOtherObject:info];
            [UserInfo shareInstance].logined = YES;
            
            [[NSUserDefaults standardUserDefaults] setObject:login.userName forKey:UserName];
            [[NSUserDefaults standardUserDefaults] setObject:login.password forKey:Password];
            [[NSUserDefaults standardUserDefaults] synchronize];
            
            [self dismissViewControllerAnimated:YES completion:^{
                
            }];
        }
        else
        {
            EntityBase *errorRet = info;
            [MessageBox showMessage:errorRet.message];
        }
        
    }];
    
}

#pragma mark --------------------------------- UITextFieldDelegate
- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    if (textField==self.txtPassword) {
        [self click_btnLogin:nil];
    }
    else
    {
        UITextField *nextField = self.txtPassword;
        [nextField becomeFirstResponder];
    }
    return YES;
}

- (void)dealloc
{
    [self.vFoot removeObserver:self forKeyPath:@"frame" context:@"vfoot"];
}

@end
