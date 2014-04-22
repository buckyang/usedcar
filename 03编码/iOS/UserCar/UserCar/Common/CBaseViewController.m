//
//  CBaseViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-22.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "CBaseViewController.h"
#import "UINavigationItem+CustomColor.h"

@interface CBaseViewController ()

@end

@implementation CBaseViewController

/**
 *  @brief 配置返回按钮
 */
- (void)configBackButton
{
    if (self.navigationController&&self.navigationController.viewControllers.count>0) {
        UIViewController *firstController = self.navigationController.viewControllers[0];
        if (firstController!=self) {
            self.navigationItem.leftBarButtonItems = @[[UIBarButtonItem barBackWithTarget:self]];
        }
    }
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
