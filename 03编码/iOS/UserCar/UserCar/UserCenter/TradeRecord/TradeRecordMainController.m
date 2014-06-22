//
//  TradeRecordMainController.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-29.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "TradeRecordMainController.h"

@interface TradeRecordMainController ()

@end

@implementation TradeRecordMainController

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    [self supportedRefresh];
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (UITableViewCell*)cellAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *OrderHeaderCell = @"OrderHeaderCell";
    static NSString *OrderNumberCell = @"OrderNumberCell";
    static NSString *OrderPriceCell = @"OrderPriceCell";
    static NSString *OrderDatetimeCell = @"OrderDatetimeCell";
    static NSString *OrderCompleteCell = @"OrderCompleteCell";
    static NSString *OrderNoCompleteCell = @"OrderNoCompleteCell";
    
    
    NSString *identifierName = nil;
    
    switch (indexPath.row) {
        case 0:
            identifierName = OrderHeaderCell;
            break;
        case 1:
            identifierName = OrderNumberCell;
            break;
        case 2:
            identifierName = OrderPriceCell;
            break;
        case 3:
            identifierName = OrderDatetimeCell;
            break;
        case 4:
            identifierName = (indexPath.section%2)? OrderCompleteCell:OrderNoCompleteCell;
            break;
    }
    UITableViewCell *cell = [self.tableView dequeueReusableCellWithIdentifier:identifierName];
    

    // Configure the cell...
    
    return cell;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return 5;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [self cellAtIndexPath:indexPath];
    
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [self cellAtIndexPath:indexPath];
    
    return cell.bounds.size.height;
}


- (UIView*)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section
{
    UIView *footView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 320, 40.f)];
    footView.backgroundColor = [UIColor whiteColor];
    return footView;
}
/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath
{
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

#pragma mark ----------------- 付款
- (IBAction)click_btnPay:(id)sender withEvent:(UIEvent *)event
{
    UITouch *touch = [[event allTouches] anyObject];
    CGPoint viewPoint = [touch locationInView:self.tableView];
    NSIndexPath *indexPath = [self.tableView indexPathForRowAtPoint:viewPoint];
    [MessageBox showMessage:[NSString stringWithFormat:@"section:%ld row:%ld",(long)indexPath.section,(long)indexPath.row]];
    
}


#pragma mark ------------ 刷新
- (void)EndPage
{
    [self endRefreshing];
}

- (void)nextPage
{
    [super nextPage];
    [NSTimer scheduledTimerWithTimeInterval:3 target:self selector:@selector(EndPage) userInfo:nil repeats:NO];
}

- (void)refreshPage
{
    [super refreshPage];
    [NSTimer scheduledTimerWithTimeInterval:3 target:self selector:@selector(EndPage) userInfo:nil repeats:NO];
}

@end
