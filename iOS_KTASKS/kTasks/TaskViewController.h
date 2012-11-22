//
//  TaskViewController.h
//  kTasks
//
//  Created by Kenny Lo on 11/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TaskViewController : UIViewController{
    NSString *name;
    NSString *note;
    NSNumber *taskId;
}
- (IBAction)updateAction:(id)sender;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *shareAction;
- (IBAction)deleteAction:(id)sender;

@property (nonatomic, retain) NSString *username;
@property (nonatomic) NSNumber *taskId;
@property (nonatomic) NSString *name;
@property (nonatomic, retain) NSString *note;
@property (nonatomic, retain) NSNumber *completed;
@property (weak, nonatomic) IBOutlet UITextField *nameField;
@property (weak, nonatomic) IBOutlet UITextField *noteField;
@property (weak, nonatomic) IBOutlet UIButton *compButton;
- (IBAction)compToggleAction:(id)sender;
- (IBAction)bgclick:(id)sender;


@end
