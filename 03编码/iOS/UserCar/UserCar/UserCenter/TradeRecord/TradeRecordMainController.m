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

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return 3;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *headerIdentifier = @"UserCenterBuyCarRecordHeaderCell";
    static NSString *contentIdentifier = @"UserCenterBuyCarRecordCell";
    static NSString *statusIdentifier = @"UserCenterBuyCarRecordCompleteCell";
    
    NSString *identifierName = nil;
    switch (indexPath.row) {
        case 0:
            identifierName = headerIdentifier;
            break;
        case 1:
            identifierName = contentIdentifier;
            break;
            
        default:
            identifierName = statusIdentifier;
            break;
    }
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifierName forIndexPath:indexPath];
    
    // Configure the cell...
    
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    CGFloat height = indexPath.row==0?85:40;

    switch (indexPath.row) {
        case 0:
            height = 90;
            break;
            case 1:
            height = 85;
            break;
        default:
            height = 40;
            break;
    }
    return height;
    
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
