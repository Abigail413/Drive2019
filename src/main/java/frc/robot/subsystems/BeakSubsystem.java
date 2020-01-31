/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class BeakSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Compressor Airow = new Compressor(21);

  Solenoid BeakPusher = new Solenoid(21 , 2);

  boolean beakOut = false;

  public BeakSubsystem() {

  }

  public void out() {
   BeakPusher.set(true);
   beakOut = true;
  }

  public void in() {
   BeakPusher.set(false);
   beakOut = false;
  }
  
  public void switchState() {
   if (beakOut = false) {
    this.in();
   }
   else {
    this.out();
   }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
