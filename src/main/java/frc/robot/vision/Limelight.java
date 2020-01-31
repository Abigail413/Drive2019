/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Configures limelight settings
 */
public class Limelight extends SubsystemBase {

    private static final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        
    private NetworkTableEntry tx = table.getEntry("tx");
    private NetworkTableEntry ty = table.getEntry("ty");
    private NetworkTableEntry ta = table.getEntry("ta");
    private NetworkTableEntry tv = table.getEntry("tv");
    private NetworkTableEntry ts = table.getEntry("ts");
    private NetworkTableEntry tl = table.getEntry("tl");
    private NetworkTableEntry camtran = table.getEntry("camtran");

    private NetworkTableEntry ledMode = table.getEntry("ledMode");
    private NetworkTableEntry camMode = table.getEntry("camMode");
    private NetworkTableEntry pipeline = table.getEntry("pipeline");
    private NetworkTableEntry stream = table.getEntry("stream");
    private NetworkTableEntry snapshot = table.getEntry("snapshot");

    /**
     * Sets limelight to vision processing mode
     */
    public void visionMode() {
        camMode.setNumber(0); // sets camera to vision processing mode
    }

    /**
     * Disables vision processing mode
     */
    public void driverMode() {
        camMode.setNumber(1); // sets camera to driving mode
    }

    /**
     * Sets vision pipeline, 0 through 9. 
     */
    public void setPipeline(int number) {
        pipeline.setNumber(number);
    }

    /**
     * Side-by-side limelight and webcam streams 
     */
    public void standardStream() {
        stream.setNumber(0);
    }

    /**
     * Secondary camera stream placed on lower-right corner of primary camera (limelight) stream
     */
    public void PiPMainStream() {
        stream.setNumber(1);
    }

    /**
     * Primary camera (limelight) stream placed on lower-right corner of secondary camera stream
     */
    public void PiPSecondaryStream() {
        stream.setNumber(2);
    }

    /**
     * Changes light settings according to how vision pipeline is set
     */
    public void lightAuto() {
        ledMode.setNumber(0);
    }

    /**
     * Forces off light
     */
    public void lightOff() {
        ledMode.setNumber(1);
    }

    /**
     * Forces blink mode
     */
    public void lightBlink() {
        ledMode.setNumber(2);
    }

    /**
     * Forces on light
     */
    public void lightOn() {
        ledMode.setNumber(3);
    }

    /**
     * Snapshot mode is set to off
     */
    public void snapshotOff() {
        snapshot.setNumber(0);
    }

    /**
     * Snapshot mode is set to on
     */
    public void snapshotOn() {
        snapshot.setNumber(1);
    }

    /**
     * Angle error in x axis (left or right)
     */
    public double getXError() {
        return tx.getDouble(0.0);
    }

    /**
     * Angle error in y axis (up or down)
     */
    public double getYError() {
        return ty.getDouble(0.0);
    }

    /**
     * Detects if there is a valid target
     */
    public boolean validTarget() {
        if (tv.getDouble(1.0) == 1.0) {
            return true;
        } else {
            return false;
        }
    }

    public double getSkew() {
        return ts.getDouble(0.0);
    }

    /**
     * Add at least 11ms for image capture latency.
     * @return The pipeline’s latency contribution (ms) 
     */
    public double getLatency() {
        return tl.getDouble(0.0);
    }

    public NetworkTableEntry get3DTranslation() {
        return camtran;
    }

    /** 
     * Finds limelight mounting angle given a measured distance and the height difference
     * of the camera to the target
    */
    public double findMountingAngle(double measuredDistance, double cameraToTargetHeight) {
        double angle = Math.atan(cameraToTargetHeight / measuredDistance);
        return angle - ty.getDouble(0.0); 
    }

    /** 
     * Finds limelight mounting angle given a measured distance, the height of the camera, 
     * and the height of the target
    */
    public double findMountingAngle(double measuredDistance, double cameraHeight, double targetHeight) {
        double cameraToTargetHeight = targetHeight - cameraHeight;
        double angle = Math.atan(cameraToTargetHeight / measuredDistance);
        return angle - ty.getDouble(0.0); 
    }

    /** 
     * Gets an accurate distance based off of robot and field measurements
     * @param cameraToTargetHeight The difference in height between the top of the vision target
     * and the top of the limelight
     * @param cameraAngle The mounting angle in degrees of the limelight. If needed, 
     * use findMountingAngle() with a measured distance to find this.
    */
    public double getTargetDistanceMeasured(double cameraToTargetHeight, double cameraAngle) {
        double distance = cameraToTargetHeight / Math.tan(Math.toRadians(cameraAngle + ty.getDouble(0.0)));
        return distance;
    }

    /** 
     * Gets an accurate distance based off of robot and field measurements. This method
     * finds the height difference for you, mainly to cure math headaches
     * @param cameraHeight The height of the limelight from the top
     * @param targetHeight The height of the vision tape from the top
     * @param cameraAngle The mounting angle in degrees of the limelight. If needed, 
     * use findMountingAngle() with a measured distance to find this.
    */
    public double getTargetDistanceMeasured(double cameraHeight, double targetHeight, double cameraAngle) {
        double cameraToTargetHeight = targetHeight - cameraHeight;
        double distance = cameraToTargetHeight / Math.tan(Math.toRadians(cameraAngle + ty.getDouble(0.0)));
        return distance;
    }

    /** 
     * Gets an easy to implement, slightly inaccurate distance method using measured area 
     * from a representation of a vision target
     * @param areaAtTargetDistance The area output by the limelight when in range of 
     * the goal
     * @return The difference of the target area and the current area
    */
    public double getTargetAreaDifference(double areaAtTargetDistance) {
        return areaAtTargetDistance - ta.getDouble(areaAtTargetDistance);
    }

}