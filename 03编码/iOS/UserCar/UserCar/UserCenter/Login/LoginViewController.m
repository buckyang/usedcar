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
- (IBAction)click_btnLogin:(id)sender
{
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
