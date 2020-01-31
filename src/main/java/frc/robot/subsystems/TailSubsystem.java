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
public class TailSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Compressor Airow = new Compressor(21);

  Solenoid TailPusher = new Solenoid(21 , 1);

  boolean tailOut = false;

  public TailSubsystem() {

  }

  public void out() {
   TailPusher.set(true);
   tailOut = true;
  }

  public void in() {
   TailPusher.set(false);
   tailOut = false;
  }
  
  public void switchState() {
   if (tailOut = false) {
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
