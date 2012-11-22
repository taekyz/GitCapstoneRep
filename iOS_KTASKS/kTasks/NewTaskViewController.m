//
//  NewTaskViewController.m
//  kTasks
//
//  Created by Kenny Lo on 13/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import "NewTaskViewController.h"

@interface NewTaskViewController ()

@end

@implementation NewTaskViewController
@synthesize nameField, noteField,completeImage, username,completed;

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
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    username = [defaults objectForKey:@"username"];
    completed=@"0";
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload {
    [self setNameField:nil];
    [self setNoteField:nil];
    [self setCompleteImage:nil];
    [super viewDidUnload];
}
- (IBAction)addAction:(id)sender {
    NSString *myURL = @"http://10.1.1.9:8080/KTASKS_Servlets/addTask?";
    
    NSString *ands = @"&";
    NSString *strRR = [NSString stringWithFormat:@"%@%@%@%@%@%@%@%@%@%@%@%@%@%@%@", myURL, @"username=", username, ands, @"name=", nameField.text, ands, @"note=", noteField.text, ands, @"completed=", completed, ands, @"shared=", @"0"];
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
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Add Task" message:@"Add Task Done" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                    [self.navigationController popViewControllerAnimated:YES];
                    //[self performSegueWithIdentifier:@"taskReturnSegue" sender:self];
                } else if ([response isEqualToString:@"ADD_TASK_FAIL"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Add Task" message:@"Add Task Failed, Try Again!" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                }
            }
        });
    });
    

}

- (IBAction)bgClick:(id)sender {
    [nameField resignFirstResponder];
    [noteField resignFirstResponder];
}
@end
