//
//  BuyCarMainViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-3-25.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "BuyCarMainViewController.h"
#import "BuyCarMainCell.h"

@interface BuyCarMainViewController ()<UIGestureRecognizerDelegate,UISearchBarDelegate>
@property (weak, nonatomic) IBOutlet UITextField *txtSearch;

@end

@implementation BuyCarMainViewController

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
   
    
    self.txtSearch.leftViewMode = UITextFieldViewModeAlways;
    UIImageView *rightView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"searchbg.png"]];
    rightView.frame = CGRectMake(20, 5, 18, 15);
    self.txtSearch.leftView = rightView;
    
    // Uncomment the following line to preserve selection between presentations.
    self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView
{
    [self.txtSearch resignFirstResponder];
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 10;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    BuyCarMainCell *cell = [self.tableView dequeueReusableCellWithIdentifier:@"BuyCarMainCell"];
    if (!cell) {
        cell = [[NSBundle mainBundle] loadNibNamed:@"BuyCarMainCell" owner:self options:nil][0];
    }
    
    if (cell)
    {
        cell.lblTotalMileage.text = [NSString stringWithFormat:@"%ld万公里",indexPath.row*10];
        cell.lblFirstTime.text = [[NSDate date] descriptionLocalAsFormat:@"yyyy年MM月"];
    }
    
    // Configure the cell...
    
    return cell;
}


- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 85.f;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    UIStoryboard *board = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    UIViewController *filterController = [board instantiateViewControllerWithIdentifier:@"BuyCarFilterViewController"];
    [filterController setHidesBottomBarWhenPushed:YES];
    [self.navigationController pushViewController:filterController animated:YES];
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

#pragma mark ----------- 搜索功能
//- (void)searchBarTextDidBeginEditing:(UISearchBar *)searchBar;
//{
//    [searchBar setShowsCancelButton:YES];
//    self.searchDisplayController.active = NO;
//}
//
//- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar
//{
//    
//    [searchBar setShowsCancelButton:NO];
//    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
//    UIViewController *filtView = [storyboard instantiateViewControllerWithIdentifier:@"BuyCarFilterViewController"];
//    [self.navigationController pushViewController:filtView animated:YES];
//}

@end
