//
//  NewUserViewController.h
//  kTasks
//
//  Created by Kenny Lo on 12/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface NewUserViewController : UIViewController
@property (weak, nonatomic) IBOutlet UITextField *usernameField;
@property (weak, nonatomic) IBOutlet UITextField *firstNameField;
@property (weak, nonatomic) IBOutlet UITextField *lastNameField;
@property (weak, nonatomic) IBOutlet UITextField *passwordField;
@property (weak, nonatomic) IBOutlet UIButton *registerAction;

- (IBAction)bgClick:(id)sender;
- (IBAction)registerAction:(id)sender;



@end
