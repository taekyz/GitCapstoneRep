//
//  FirstTabViewController.h
//  kTasks
//
//  Created by Kenny Lo on 11/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FirstTabViewController : UIViewController<UITableViewDelegate, UITableViewDataSource>{
}
@property (weak, nonatomic) IBOutlet UITableView *tableView;
- (IBAction)refreshTable:(id)sender;
- (IBAction)addTask:(id)sender;
@property (nonatomic, retain) NSMutableArray *responseData;
@property (nonatomic, retain) NSString *username;
@property NSInteger temp;

@end
