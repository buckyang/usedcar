//
//  UserInfoViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-30.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UserInfoViewController.h"
#import "../../Common/RectTextField.h"
#import "../../../../Entity/Entity/UserInfo.h"
#import "../../../../AccessLayer/AccessLayer/UploadImage.h"

@interface UserInfoViewController ()


@property (strong, nonatomic) IBOutlet UIView *vGender;

@property (weak, nonatomic) IBOutlet RectTextField *txtName;
@property (weak, nonatomic) IBOutlet RectTextField *txtNickName;
@property (nonatomic,weak) IBOutlet UIButton *btnBirthday;
@property (nonatomic,weak) IBOutlet UIDatePicker *dpkBirthday;
@property (weak, nonatomic) IBOutlet RectTextField *txtId;
@property (weak, nonatomic) IBOutlet RectTextField *txtAddress;


@end

@implementation UserInfoViewController

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
    
    [self boundUserInfo];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)setEditing:(BOOL)editing animated:(BOOL)animated
{
    [super setEditing:editing animated:animated];
    if (!editing) {
        [self.txtName setHasRect:NO];
        self.txtNickName.hasRect = NO;
    }
    else
    {
        [self.txtName setHasRect:YES];
        self.txtNickName.hasRect = YES;
    }
}
#pragma mark -------------------------------- 绑定数据

/**
 *  @brief 绑定用户信息
 */
- (void)boundUserInfo
{
    self.txtNickName.text = [UserInfo shareInstance].userName;
}

- (IBAction)click_btnModifyPhone:(id)sender
{
    NSString *imagePath = [[NSBundle mainBundle] pathForResource:@"test" ofType:@"png"];
    NSData *imageData = [NSData dataWithContentsOfFile:imagePath];
    
    [UploadImage uploadImageWithData:imageData withUploadProcess:^(float aProcessValue) {
//        INFO(@"");
    }];
}


#pragma mark - Table view data source


- (UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    return self.vGender;
}


- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath
{
    
}

- (UITableViewCellEditingStyle)tableView:(UITableView *)tableView editingStyleForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return UITableViewCellEditingStyleInsert|UITableViewCellEditingStyleDelete;
}

// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return NO;
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
