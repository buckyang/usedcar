//
//  RegiestIndividualUserViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-3-21.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "RegiestIndividualUserViewController.h"
#import "../../../../AccessLayer/AccessLayer/UserCenter/Register/UserRegister.h"
#import "CheckButton.h"

@interface RegiestIndividualUserViewController ()

@property (strong, nonatomic) IBOutlet UIButton *btnPhoneNumber;
@property (strong, nonatomic) IBOutlet UITextField *txtPhoneNumber;

/**
 *  @brief 验证码
 */
@property (weak, nonatomic) IBOutlet UITextField *txtPhoneCode;
@property (weak, nonatomic) IBOutlet UITextField *txtPassword;
@property (weak, nonatomic) IBOutlet UITextField *txtRePassword;

@property (weak, nonatomic) IBOutlet CheckButton *btnChecked;

@end

@implementation RegiestIndividualUserViewController

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
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark -------------------- 功能实现


#pragma mark --------------------- 获取验证码

/**
 *  @brief 验证电话号码是否正确
 *
 *  @return 是否正确
 */
- (BOOL)verifyPhone
{
    BOOL ret = YES;
    if ([NSString isEmpty:self.txtPhoneNumber.text.trim]) {
        ret = NO;
        [MessageBox showMessage:@"电话号码不能为空"];
    }
    else
    {
        static NSString *phoneFormat = @"(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}";
        ret = [NSString regexWithFormat:phoneFormat ValueString:self.txtPhoneNumber.text.trim];
        ret?nil:[MessageBox showMessage:@"电话号码格式错误"];
    }
    
    return ret;
}

/**
 *  @brief 获取验证码按扭
 *
 *  @param sender 按扭
 */
- (IBAction)click_btnPhoneCode:(id)sender
{
    UserRegister *access = [UserRegister createInstance];
    UserInfo *user = [UserInfo createInstance];
    user.phone = self.txtPhoneNumber.text.trim;
    if ([self verifyPhone]) {
         [access obtainCodeWithUserInfo:user
                           withCallback:^(id info, HTTPAccessState isSuccess) {
             self.txtPhoneCode.text = user.phoneVerifyCode;
         }];
    }  
}

#pragma mark --------------- 注册
/**
 *  @brief 输入验证
 *
 *  @return 输入验证
 */
- (BOOL)verifyInput
{
    BOOL ret = YES;
    
    //验证手机号
    ret = [self verifyPhone];
    if (ret==NO) {
        return ret;
    }
    
    //验证验证码
    if([NSString isEmpty:self.txtPhoneCode.text.trim])
    {
        ret = NO;
        [MessageBox showMessage:@"手机验证码不能为空"];
    }
    else if (self.txtPhoneCode.text.trim.length!=6)
    {
        ret = NO;
        [MessageBox showMessage:@"请输入正确的验证码"];
    }
    if (ret==NO) {
        return ret;
    }
    
    //验证密码
    if ([NSString isEmpty:self.txtPassword.text.trim]) {
        ret = NO;
        [MessageBox showMessage:@"密码不能为空"];
    }
    else if (self.txtPassword.text.trim.length<6)
    {
        ret = NO;
        [MessageBox showMessage:@"密码不能少于6位"];
    }
    else if (![self.txtPassword.text.trim isEqualToString:self.txtRePassword.text.trim])
    {
        ret = NO;
        [MessageBox showMessage:@"两次密码不一致"];
    }
    
    return ret;
}

/**
 *  @brief 注册事件
 *
 *  @param sender 按钮
 */
- (IBAction)click_btnRegister:(id)sender
{
    if (![self verifyInput]) {
        return;
    }
    if (!self.btnChecked.isSelected) {
        [MessageBox showMessage:@"请同意条款"];
        return;
    }
    
    UserInfo *user = [UserInfo createInstance];
    user.phone = self.txtPhoneNumber.text.trim;
    user.phoneVerifyCode = self.txtPhoneCode.text.trim;
    user.password = self.txtPassword.text.trim;
    user.repassword = self.txtRePassword.text.trim;
    
    UserRegister *access = [UserRegister createInstance];
    
    [MessageBox showLoading];
    [access registerIndividualUser:user withCallback:^(id info, HTTPAccessState isSuccess) {
        [MessageBox hiddenLoading];
        if (isSuccess==HTTPAccessStateSuccess) {
            [[UserInfo shareInstance] reflectDataFromOtherObject:user];
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

- (UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    UIView *header = nil;
    if (section==1) {
        header = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 320, 20)];
        header.backgroundColor = [UIColor whiteColor];
    }
    return header;
}

#pragma mark --------------------------------- UITextFieldDelegate
- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    if (textField==self.txtRePassword) {
        [textField resignFirstResponder];
    }
    else
    {
        UITextField *nextField = (UITextField*)[self.view viewWithTag:textField.tag+1];
        [nextField becomeFirstResponder];
    }
    return YES;
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
