//
//  CBaseTableViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-22.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "CBaseTableViewController.h"
#import "UINavigationItem+CustomColor.h"
#import "UIViewController+CustomStyle.h"
#import "MJRefresh.h"

@interface CBaseTableViewController ()<MJRefreshBaseViewDelegate>
{
    MJRefreshHeaderView *headerRefresh;
    MJRefreshFooterView *footerRefresh;
}

@end

@implementation CBaseTableViewController
@synthesize pageIndex,pageSize;

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

#pragma mark --------------- 支持下拉与上拉刷新
- (void)supportedRefresh
{
    headerRefresh = [[MJRefreshHeaderView alloc] init];
    headerRefresh.delegate = self;
    headerRefresh.scrollView = self.tableView;
}


- (void)supportedPaging
{
    footerRefresh = [[MJRefreshFooterView alloc] init];
    footerRefresh.delegate = self;
    footerRefresh.scrollView = self.tableView;
}

- (void)refreshViewBeginRefreshing:(MJRefreshBaseView *)refreshView
{
    if (refreshView==footerRefresh) {
        [self nextPage];
    }
    else
    {
        [self refreshPage];
    }
}


- (void)dealloc
{
    [headerRefresh free];
    [footerRefresh free];
}

#pragma mark ------------------  下拉刷新
- (void)refreshPage
{
    NSAssert(![NSStringFromClass(self.class) isEqualToString:@"CBaseTableViewController"], @"请重写下拉刷新方法");
    [footerRefresh endRefreshing];
}

- (void)endRefreshing
{
    [headerRefresh endRefreshing];
}

#pragma mark ------------------ 上拉翻页
- (void)nextPage
{
    NSAssert(![NSStringFromClass(self.class) isEqualToString:@"CBaseTableViewController"], @"请重写翻页方法");
    [headerRefresh endRefreshing];
}

- (void)endPaging
{
    [footerRefresh endRefreshing];
}


@end
