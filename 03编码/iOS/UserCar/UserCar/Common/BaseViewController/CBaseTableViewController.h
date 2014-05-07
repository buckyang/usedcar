//
//  CBaseTableViewController.h
//  UserCar
//
//  Created by 舒联勇 on 14-4-22.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TableRefresh.h"


/**
 *  @brief 自定义的TableViewController,可以统一样式
 */
@interface CBaseTableViewController : UITableViewController<TablePagingRefresh,TableRefreshDelegate>

/**
 *  @brief 支持刷新
 */
- (void)supportedRefresh;

/**
 *  @brief 支持分页刷新
 */
- (void)supportedPaging;

@end
