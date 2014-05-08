//
//  TableRefresh.h
//  UserCar
//
//  Created by 舒联勇 on 14-5-4.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

static NSInteger pageSize=15;
@protocol TableRefreshDelegate <NSObject>

/**
 *  @brief 刷新
 */
- (void)refreshPage;

/**
 *  @brief 结束刷新
 */
- (void)endRefreshing;

@end

@protocol TablePagingRefresh <NSObject>

@property (nonatomic) NSInteger pageIndex;
@property (nonatomic) NSInteger *pageSize;

/**
 *  @brief 下一页
 */
- (void)nextPage;

/**
 *  @brief 结束分页刷新
 */
- (void)endPaging;

@end
