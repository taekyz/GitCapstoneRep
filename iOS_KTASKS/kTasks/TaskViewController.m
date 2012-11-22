//
//  TaskViewController.m
//  kTasks
//
//  Created by Kenny Lo on 11/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import "TaskViewController.h"

@interface TaskViewController ()

@end

@implementation TaskViewController
@synthesize name, note, taskId, nameField, noteField, completed, compButton, username;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    [super viewDidLoad];
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    username = [defaults objectForKey:@"username"];
    nameField.text = name;
    noteField.text = note;
    
    if (completed.intValue == 0){
        UIImage *img = [UIImage imageNamed:@"no2.png"];
        [compButton setImage:img forState:UIControlStateNormal];    } else if (completed.intValue == 1){
            UIImage *img = [UIImage imageNamed:@"yes2.png"];
            [compButton setImage:img forState:UIControlStateNormal];    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)updateAction:(id)sender {
    NSString *myURL = @"http://10.1.1.9:8080/KTASKS_Servlets/updateTaskDetails?";
    
    NSString *ands = @"&";
    NSString *strRR = [NSString stringWithFormat:@"%@%@%@%@%@%@%@%@%@%@%@%@%@%@%@", myURL, @"username=", username, ands, @"id=", taskId.stringValue, ands, @"name=", nameField.text, ands, @"note=", noteField.text, ands, @"completed=", completed.stringValue];
    //connect
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        //Call servlet to get data
        //NSString* response = [NSString stringWithContentsOfURL:[NSURL URLWithString:strRR] encoding:NSASCIIStringEncoding error:nil];
        NSData* data = [NSData dataWithContentsOfURL:[NSURL URLWithString:strRR]];
        NSString* response = [[NSString alloc] initWithData:data encoding:NSASCIIStringEncoding];
        response = [response stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
        
        NSLog(@"%@",response);
        //disconenct
        dispatch_async(dispatch_get_main_queue(), ^{
            //load data
            if (response) {
                if ([response isEqualToString:@"OK"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Update Task Details" message:@"Update Done" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                } else if ([response isEqualToString:@"UPDATE_FAILED"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Update Task Details" message:@"Update Failed, Try Again!" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                }
            }
        });
    });
    

}
- (void)viewDidUnload {
    [self setCompButton:nil];
    [super viewDidUnload];
}
- (IBAction)compToggleAction:(id)sender {
    if (completed.intValue == 0){
        completed = [NSNumber numberWithInt:1];
        UIImage *img = [UIImage imageNamed:@"yes2.png"];
        [compButton setImage:img forState:UIControlStateNormal];
    } else if (completed.intValue == 1){
        completed = [NSNumber numberWithInt:0];
        UIImage *img = [UIImage imageNamed:@"no2.png"];
        [compButton setImage:img forState:UIControlStateNormal];
    }

}

- (IBAction)bgclick:(id)sender{
    [nameField resignFirstResponder];
    [noteField resignFirstResponder];
}
- (IBAction)deleteAction:(id)sender {
    NSString *myURL = @"http://10.1.1.9:8080/KTASKS_Servlets/deleteTask?";
    
    NSString *ands = @"&";
    NSString *strRR = [NSString stringWithFormat:@"%@%@%@%@%@%@", myURL, @"username=", username, ands, @"id=", taskId.stringValue];
    //connect
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        //Call servlet to get data
        //NSString* response = [NSString stringWithContentsOfURL:[NSURL URLWithString:strRR] encoding:NSASCIIStringEncoding error:nil];
        NSData* data = [NSData dataWithContentsOfURL:[NSURL URLWithString:strRR]];
        NSString* response = [[NSString alloc] initWithData:data encoding:NSASCIIStringEncoding];
        response = [response stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
        
        NSLog(@"%@",response);
        //disconenct
        dispatch_async(dispatch_get_main_queue(), ^{
            //load data
            if (response) {
                if ([response isEqualToString:@"OK"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Task Details" message:@"Task Deleted" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                    [self.navigationController popViewControllerAnimated:YES];
                } else if ([response isEqualToString:@"DELETE_FAIL"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Task Details" message:@"Delete Failed, Try Again!" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                }
            }
        });
    });
    
    

}
@end
