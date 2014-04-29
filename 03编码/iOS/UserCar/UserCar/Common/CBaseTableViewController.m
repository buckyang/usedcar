//
//  CBaseTableViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-22.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "CBaseTableViewController.h"
#import "UINavigationItem+CustomColor.h"

@interface CBaseTableViewController ()

@end

@implementation CBaseTableViewController

/**
 *  @brief 配置返回按钮
 */
- (void)configBackButton
{
//    if (self.navigationController&&self.navigationController.viewControllers.count>0) {
//        UIViewController *firstController = self.navigationController.viewControllers[0];
//        if (firstController!=self) {
//            self.navigationItem.leftBarButtonItems = @[[UIBarButtonItem barBackWithTarget:self]];
//        }
//    }    
    UIBarButtonItem *backItem = [[UIBarButtonItem alloc] initWithTitle:@"返回" style:UIBarButtonItemStylePlain target:nil action:nil];
    [backItem setBackButtonBackgroundImage:[UIImage imageNamed:@"img_Back.png"] forState:UIControlStateNormal barMetrics:UIBarMetricsDefault];
    [backItem setTitleTextAttributes:@{NSForegroundColorAttributeName: [UIColor whiteColor]} forState:UIControlStateNormal];
    self.navigationItem.backBarButtonItem = backItem;
}


- (id)initWithCoder:(NSCoder *)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self) {
        self.editButtonItem.title = @"编辑";
    }
    return self;
}

- (void)setEditing:(BOOL)editing animated:(BOOL)animated
{
    [super setEditing:editing animated:animated];
    self.editButtonItem.title = editing?@"完成":@"编辑";
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self configBackButton];
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    [self.navigationItem setButtonColor:[UIColor whiteColor]];
}

@end
