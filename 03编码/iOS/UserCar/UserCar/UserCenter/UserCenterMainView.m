//
//  UserCenterMainView.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-22.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UserCenterMainView.h"
#import "../../../Entity/Entity/UserInfo.h"
#import "ConfirmMessageBox.h"

@interface UserCenterMainView ()



@end

@implementation UserCenterMainView

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
    

    self.clearsSelectionOnViewWillAppear = YES;
    // Do any additional setup after loading the view.
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    
    if ([UserInfo shareInstance].logined) {
        self.navigationItem.rightBarButtonItem.title = @"注销";
    }
    else
    {
        self.navigationItem.rightBarButtonItem.title = @"登录";
    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)click_btnLogin:(id)sender
{
    if (![UserInfo shareInstance].logined) {
        UIStoryboard *loginBoard = [UIStoryboard storyboardWithName:@"UserCenter" bundle:nil];
        UIViewController *loginController = [loginBoard instantiateViewControllerWithIdentifier:@"LoginController"];
        [self presentViewController:loginController animated:YES completion:^{            
        }];
    }
    else
    {
       [ConfirmMessageBox confirmMessage:@"确定要注销？" withCallback:^(BOOL confirmed) {
            if (confirmed) {
                [UserInfo shareInstance].logined = NO;
                self.navigationItem.rightBarButtonItem.title = @"登录";
            }
        }];
    }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.row==3) {
        
        UIStoryboard *board = [UIStoryboard storyboardWithName:@"UserCenter" bundle:nil];
        UIViewController *view = [board instantiateViewControllerWithIdentifier:@"TradeRecordMainController"];
        [view setHidesBottomBarWhenPushed:YES];
        [self.navigationController pushViewController:view animated:YES];
        

    }
}


@end
