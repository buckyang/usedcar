//
//  ReselleSignonViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-29.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "ReselleSignonViewController.h"
#import "CheckButton.h"
#import "../../../../AccessLayer/AccessLayer/UserCenter/Register/UserRegister.h"

@interface ReselleSignonViewController ()<UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *txtPhone;
@property (weak, nonatomic) IBOutlet UITextField *txtPhoneCode;
@property (weak, nonatomic) IBOutlet UITextField *txtResellName;
@property (weak, nonatomic) IBOutlet UITextField *txtResellAddress;
@property (weak, nonatomic) IBOutlet CheckButton *btnOlderCarResell;
@property (weak, nonatomic) IBOutlet CheckButton *btn4SResell;
@property (weak, nonatomic) IBOutlet UITextField *txtPassword;
@property (weak, nonatomic) IBOutlet UITextField *txtRepassword;


@property (weak, nonatomic) IBOutlet CheckButton *btnAgree;
@property (weak, nonatomic) IBOutlet UIButton *btnRegister;

@end

@implementation ReselleSignonViewController

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
    self.btnRegister.layer.cornerRadius = 4;
    // Do any additional setup after loading the view.
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (IBAction)click_btnOlderCarResell:(id)sender
{
    self.btn4SResell.selected = !self.btnOlderCarResell.selected;
}
- (IBAction)click_btn4SResell:(id)sender {
    
    self.btnOlderCarResell.selected = !self.btn4SResell.selected;
}

#pragma mark --------------------- 获取验证码

/**
 *  @brief 验证电话号码是否正确
 *
 *  @return 是否正确
 */
- (BOOL)verifyPhone
{
    BOOL ret = YES;
    if ([NSString isEmpty:self.txtPhone.text.trim]) {
        ret = NO;
        [MessageBox showMessage:@"电话号码不能为空"];
    }
    else
    {
        static NSString *phoneFormat = @"(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}";
        ret = [NSString regexWithFormat:phoneFormat ValueString:self.txtPhone.text.trim];
        ret?nil:[MessageBox showMessage:@"电话号码格式错误"];
    }
    
    return ret;
}

/**
 *  @brief 获取验证码
 *
 *  @param sender 验证码按钮
 */
- (IBAction)click_btnPhoneCode:(id)sender
{
    if ([self verifyPhone])
    {
        UserInfo *user = [UserInfo createInstance];
        user.phone = self.txtPhone.text.trim;
        UserRegister *access = [UserRegister createInstance];
        
        [access obtainCodeWithUserInfo:user withCallback:^(id info, HTTPAccessState isSuccess) {
            if (isSuccess==HTTPAccessStateSuccess) {
                self.txtPhoneCode.text = user.phoneVerifyCode;
            }
            else
            {
                EntityBase *error = info;
                [MessageBox showMessage:error.message];
            }
        }];
    }
    
}

#pragma mark ------------------ 注册

/**
 *  @brief 输入验证
 *
 *  @return 验证是否正确
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
    
    //验证经销商名
    if ([NSString isEmpty:self.txtResellName.text.trim]) {
        ret = NO;
        [MessageBox showMessage:@"经销商名称不能为空"];
        return ret;
    }
    
    //验证经销商地址
    if ([NSString isEmpty:self.txtResellAddress.text.trim]) {
        ret = NO;
        [MessageBox showMessage:@"经销商地址不能为空"];
        return ret;
    }
    
    //验证经销商类型
    if (!self.btnOlderCarResell.selected && !self.btn4SResell.selected) {
        ret = NO;
        [MessageBox showMessage:@"请选择经销商类型"];
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
    else if (![self.txtPassword.text.trim isEqualToString:self.txtRepassword.text.trim])
    {
        ret = NO;
        [MessageBox showMessage:@"两次密码不一致"];
    }
    
    return ret;
}

/**
 *  @brief 注册
 *
 *  @param sender 注册按钮
 */
- (IBAction)click_btnRegister:(id)sender
{
    if (![self verifyInput])
    {
        return;
    }
    if (!self.btnAgree.isSelected) {
        [MessageBox showMessage:@"请同意条款"];
        return;
    }
    
    UserInfo *user = [UserInfo createInstance];
    user.phone = self.txtPhone.text.trim;
    user.phoneVerifyCode = self.txtPhoneCode.text.trim;
    user.resellerName = self.txtResellName.text.trim;
    user.adress = self.txtResellAddress.text.trim;
    user.resellerType = self.btnOlderCarResell.selected?RESELLEROLDCARTYPE:RESELLER4STYPE;
    user.password = self.txtPassword.text.trim;
    user.repassword = self.txtRepassword.text.trim;
    
    
    UserRegister *access = [UserRegister createInstance];
    [MessageBox showLoading];
    [access reselleSignonUser:user withCallback:^(id info, HTTPAccessState isSuccess) {
        [MessageBox hiddenLoading];
        if (isSuccess==HTTPAccessStateSuccess) {
            [self dismissViewControllerAnimated:YES completion:^{
                
            }];
        }
        else
        {
            EntityBase *error = info;
            [MessageBox showMessage:error.message];
        }
    }];
    
}

#pragma mark --------------------------------- UITextFieldDelegate
- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    if (textField==self.txtRepassword) {
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
